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

data GameOptions = Debug | Verbose | UseSeed
  deriving (Eq, Ord, Show, Enum)

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
run n seed jenv jobj = do
  if isDebug
    then do
      sendToOut jenv jobj $ "----------------"
      sendToOut jenv jobj $ "Seed    = " ++ show seed
      sendToOut jenv jobj $ "Debug   = " ++ show isDebug
      sendToOut jenv jobj $ "Verbose = " ++ show isVerbose
      sendToOut jenv jobj $ "UseSeed = " ++ show useSeed
      sendToOut jenv jobj $ "----------------"
    else return ()

  if useSeed
    then setStdGen $ mkStdGen (fromIntegral seed :: Int)
    else return ()

  g <- getStdGen
  let deck = makeDecks 6 Hidden
  let decklen = length deck
  let shuffled = shuffle deck (decklen * 8) (randomRs (0, decklen - 1) g :: [Int])

  if isDebug
    then do
      sendToOut jenv jobj $ "Baralho original    = " ++ show (map (unhideCard) deck)
      sendToOut jenv jobj $ "Baralho embaralhado = " ++ show (map (unhideCard) shuffled)
    else return ()

  if isVerbose
    then do
      sendToOut jenv jobj $ "Welcome to Blackjack"
    else return ()

  return (fromIntegral (1) :: CFloat)

  where
    x = fromIntegral n :: Int
    isDebug   = x `has` Debug
    isVerbose = x `has` Verbose
    useSeed   = x `has` UseSeed

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
