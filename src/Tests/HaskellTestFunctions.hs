{-# LANGUAGE ForeignFunctionInterface #-}

module HaskellTestFunctions (testHaskellOutput) where

import Foreign.C.Types

testHaskellOutput :: IO ()
testHaskellOutput = putStr("\x1b[33mHello from Haskell\x1b[0m\n")

foreign export ccall testHaskellOutput :: IO ()
