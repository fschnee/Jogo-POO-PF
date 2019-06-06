{-# LANGUAGE ForeignFunctionInterface #-}

module Main where

import Foreign.C.Types
import System.Random
import BitField
import Deck

-- Cuidado: usar a flag Debug deixa as instruções
-- de impressão excessivamente lentas (da para ver
-- quando imprime o baralho embaralhado)
data GameOptions = Debug | Verbose | UseSeed
  deriving (Eq, Ord, Show, Enum)
data GameResult = P1Win | P2Win | P_3to2
  deriving (Eq, Ord, Show, Enum)

main :: IO ()
main = do
  run (fromIntegral (compose [Verbose]) :: CInt) (0::CInt)
  return ()

run :: CInt -> CInt -> IO CInt
run n seed = do
  if isDebug
    then do
      putStrLn $ "----------------"
      putStrLn $ "Seed    = " ++ show seed
      putStrLn $ "Debug   = " ++ show isDebug
      putStrLn $ "Verbose = " ++ show isVerbose
      putStrLn $ "UseSeed = " ++ show useSeed
      putStrLn $ "----------------"
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
      putStrLn $ "Baralho original    = " ++ show (map (unhideCard) deck)
      putStrLn $ "Baralho embaralhado = " ++ show (map (unhideCard) shuffled)
    else return ()

  if isVerbose
    then putStrLn "Welcome to Blackjack"
    else return ()

  let result = [P1Win]
  return (fromIntegral (compose result) :: CInt)

  where
    x = fromIntegral n :: Int
    isDebug   = x `has` Debug
    isVerbose = x `has` Verbose
    useSeed   = x `has` UseSeed

foreign export ccall run :: CInt -> CInt -> IO CInt

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
