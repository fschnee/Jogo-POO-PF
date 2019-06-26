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
  loop [(snd hands, compose [Unknown]), (fst hands, compose [Unknown])] jenv jobj

  return (fromIntegral (1) :: CFloat)

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

--printHand isDealer isDebug isVerbose handnum hand jenv jobj
printHand :: Bool -> Bool -> Bool -> Int -> Hand -> Ptr () -> Ptr () -> IO ()
printHand False False False n hand jenv jobj =
  sendToOut jenv jobj $ (show n) ++ ": " ++ (show hand)
printHand False False True n hand jenv jobj =
  sendToOut jenv jobj $ "Your hand #" ++ (show n) ++ " is:" ++ (show hand)
printHand False True x n hand jenv jobj  = printHand False False x n hand jenv jobj
-- fazer para mão do dealer

printHands :: Bool -> Bool -> Ptr () -> Ptr () -> (Hand, Hand)-> IO ()
printHands False False jenv jobj (p, d) =
  sendToOut jenv jobj $ (show p) ++ "\n" ++ (show d)
printHands False True jenv jobj (p, d) = do
  sendToOut jenv jobj $ "Your hand is " ++ show p
  sendToOut jenv jobj $ "The dealer's hand is " ++ show d
  return ()
printHands True x jenv jobj (p, d) = do
  sendToOut jenv jobj $ "-- Your hand is " ++ show p
  sendToOut jenv jobj $ "-- The dealer's hand is " ++ show (map (unhideCard) d)
  printHands False x jenv jobj (p, d)
  return ()

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

-- the first hand is always the dealer's
loop :: [HandInfo] -> Ptr () -> Ptr () -> IO ()
loop hands jenv jobj = do
  printHand False False True 1 (fst (hands!!1)) jenv jobj
  return ()
