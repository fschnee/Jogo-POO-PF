{-# LANGUAGE CPP, ForeignFunctionInterface #-}

module CommonIO where

#ifdef USE_JAVA_BACKEND
import Foreign.C.String
#endif

sendToOut :: String -> IO ()
getFromIn :: IO String

#ifdef USE_JAVA_BACKEND
foreign import ccall "sendToJava" c_SendToJava :: CString -> IO ()
foreign import ccall "getFromJava" c_GetFromJava :: CString
sendToOut x = withCString x $ \cstr -> c_SendToJava cstr
getFromIn = peekCString c_GetFromJava
#else
sendToOut x = putStrLn x
getFromIn = getLine
#endif
