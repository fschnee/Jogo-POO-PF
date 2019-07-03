{-# LANGUAGE CPP, ForeignFunctionInterface #-}

module Main where

import CommonIO
import System.Random
import BitField
import Deck
import Foreign.Ptr
#ifdef USE_JAVA_BACKEND
import Foreign.C.Types
foreign export ccall run :: CInt -> CInt -> Ptr () -> Ptr () -> IO CFloat
#else
type CInt = Int
type CFloat = Float
#endif

data HandFlags = Unknown | Busted | CanSplit
  deriving (Eq, Ord, Show, Enum)
data GameOptions = Debug | Verbose | UseSeed
  deriving (Eq, Ord, Show, Enum)
type HandInfo = (Hand, Int)

main :: IO ()
main = do
  run (fromIntegral (compose [Verbose]) :: CInt) (0::CInt) (nullPtr) (nullPtr)
  return ()

-- retorna o fração que corresponde ao valor ganho, por exemplo:
-- 0   = jogador perde tudo
-- 0.5 = jogador perde metade da aposta (insurance)
-- 1   = jogador não perde nem ganha
-- 2   = jogador ganha (house bust)
-- 2.5 = jogador fez blackjack
run :: CInt -> CInt -> Ptr () -> Ptr ()-> IO CFloat
run cFlags seed jenv jobj = do
  printGreeting isDebug isVerbose jenv jobj
  printFlags isDebug isVerbose useSeed (fromIntegral seed :: Int) jenv jobj

  if useSeed
    then setStdGen $ mkStdGen (fromIntegral seed :: Int)
    else return ()

  g <- getStdGen
  let deck = makeDecks 6 Hidden
  let decklen = length deck
  let shuffled = fastShuffle deck decklen decklen (randoms g :: [Int])
  printDecks isDebug deck shuffled jenv jobj

  let hands = firstdeal shuffled ([], []) True
  finalhands <- loop [(snd hands, compose [Unknown]), (fst hands, compose [Unknown])] isDebug isVerbose jenv jobj 0
  let winnings = calculateWinnings finalhands

  return (realToFrac (winnings) :: CFloat)

  where
    flags = fromIntegral cFlags :: Int
    isDebug   = flags `has` Debug
    isVerbose = flags `has` Verbose
    useSeed   = flags `has` UseSeed


-- shuffled deck -> (PlayerHand, DealerHand) -> giveToDealer -> (PlayerHand, DealerHand)
firstdeal :: Deck -> (Hand, Hand)-> Bool -> (Hand, Hand)
firstdeal xs (c, x) True
  | length x == 1 = firstdeal (snd popped) (c, (unhideCard (fst popped)):x) False
  | length x == 2 = firstdeal xs (c, x) False
  | otherwise     = firstdeal (snd popped) (c, (fst popped):x) False
  where popped = popindex xs [] 1
firstdeal xs (c, x) False
  | length c >= 2 = (map (unhideCard) c, x)
  | otherwise     = firstdeal (snd popped) ((fst popped):c, x) True
  where popped = popindex xs [] 1

printFlags :: Bool -> Bool -> Bool -> Int -> Ptr () -> Ptr () -> IO ()
printFlags isDebug isVerbose useSeed seed jenv jobj
  | isDebug == True = do
      sendToOut jenv jobj $ "-- Seed    = " ++ show seed
      sendToOut jenv jobj $ "-- Debug   = " ++ show isDebug
      sendToOut jenv jobj $ "-- Verbose = " ++ show isVerbose
      sendToOut jenv jobj $ "-- UseSeed = " ++ show useSeed
      return ()
  | otherwise = return ()

printDecks :: Bool -> Deck -> Deck -> Ptr () -> Ptr () -> IO ()
printDecks debug deck shuffled jenv jobj
  | debug == True = do
      sendToOut jenv jobj $ "-- Deck          = " ++ show (map (unhideCard) deck)
      sendToOut jenv jobj $ "-- Shuffled Deck = " ++ show (map (unhideCard) shuffled)
      return ()
  | otherwise = return ()

printGreeting :: Bool -> Bool -> Ptr () -> Ptr () -> IO ()
printGreeting _ verbose jenv jobj
  | verbose == True = sendToOut jenv jobj $ "- Welcome to Blackjack -"
  | otherwise       = sendToOut jenv jobj $ "Blackjack:"

-- printHand isDealer isDebug isVerbose handnum hand jenv jobj
-- Não se preocupa, it just *works*
printHand :: Bool -> Bool -> Bool -> Int -> Hand -> Ptr () -> Ptr () -> IO ()
printHand False False False n hand jenv jobj =
  sendToOut jenv jobj $ (show n) ++ ": " ++ (show hand)
printHand False False True n hand jenv jobj =
  sendToOut jenv jobj $ "Your hand #" ++ (show n) ++ " is:" ++ (show hand)
printHand False True x n hand jenv jobj = printHand False False x n hand jenv jobj
printHand True  False False n hand jenv jobj =
  sendToOut jenv jobj $ "D:" ++ (show hand)
printHand True False True n hand jenv jobj =
  sendToOut jenv jobj $ "The dealer's hand is:" ++ (show hand)
printHand True True x n hand jenv jobj = do
  sendToOut jenv jobj $ "-- Dealer's hand:" ++ (show (map (unhideCard) hand))
  printHand True False x n hand jenv jobj
  return ()

printOptions :: [HandInfo] -> Int -> Ptr() -> Ptr () -> IO ()
printOptions playerhands currhand jenv jobj = return ()

printHelp :: Ptr () -> Ptr () -> IO ()
printHelp jenv jobj = sendToOut jenv jobj $ "-> You can use one of these options [\"Hit\", \"Stand\", \"Split\"]"

getInput :: Ptr () -> Ptr () -> IO String
getInput jenv jobj = do
  let possibleinputs = ["Hit", "Stand", "Split"]
  input <- getFromIn jenv jobj
  -- Se o input está compreendido nos possibleinputs
  case (foldr (||) False (map (== input) possibleinputs)) of
    True -> return input
    False -> do
      printHelp jenv jobj
      x <- getInput jenv jobj
      return x

cardValue :: Card -> [Int]
cardValue (FrenchCard v _ _)
  | v >= Ten && v <= King = [10]
  | v == Ace              = [1, 11]
  | otherwise             = [(fromEnum v) + 1]

-- Retorna verdadeiro se todas as possibilidades de valores da mão são maiores que 21
isBusted :: Hand -> Bool
isBusted xs = foldr (&&) True $ map (> 21) (splitSum xs 0)

-- Retorna uma lista contendo todos os valores possiveis da mão
splitSum :: Hand -> Int -> [Int]
splitSum [] currval = [currval]
splitSum (c:x) currval
  | length cardval == 2 = splitSum x (currval + cardval!!0) ++ splitSum x (currval + cardval!!1)
  | otherwise           = splitSum x (currval + cardval!!0)

  where
    cardval = cardValue c

loop :: [HandInfo] -> Bool -> Bool -> Ptr () -> Ptr () -> Int -> IO [HandInfo]
loop hands isDebug isVerbose jenv jobj currhand = do
  let playerhands = tail hands
  let dealerhand = head hands

  printHand False isDebug isVerbose 1 (fst (hands!!1)) jenv jobj
  printHand True isDebug isVerbose 0 (fst dealerhand) jenv jobj

  input <- getInput jenv jobj
  newplayerhands <- if input == "Hit"
                      then return "5"
                    else if input == "Stand"
                      then return "6"
                    else return "7"
  newplayerhands2 <- case input of
                       "Hit" -> case (1 == 0) of
                         True -> return "1 is == 0"
                         False -> return "1 != 0"
                       "Stand" -> case (1 == 1) of
                         True -> return "Stood"
                         False -> return "expression"
  sendToOut jenv jobj $ newplayerhands
  sendToOut jenv jobj $ newplayerhands2

  let newhands = hands

  return ( newhands)


calculateWinnings :: [HandInfo] -> Float
calculateWinnings hands = 1
