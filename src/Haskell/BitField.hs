module BitField (compose, has) where

import Data.Bits ((.|.), (.&.), popCount)

flag :: Enum t => t -> Int
flag a = 2 ^ fromEnum a

-- Cria um BitField a partir das flags passadas
compose :: Enum t => [t] -> Int
compose [] = 0
compose (c:x) = (flag c) .|. (compose x)

-- Verifica se um BitField tem a flag dada
has :: Enum t => Int -> t -> Bool
has a b = popCount(a .&. flag b) == 1
