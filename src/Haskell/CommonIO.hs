{-# LANGUAGE CPP, ForeignFunctionInterface #-}

module CommonIO where

import Foreign.Ptr
#ifdef USE_JAVA_BACKEND
import Foreign.C.String
#endif

sendToOut :: Ptr () -> String -> IO ()
getFromIn :: Ptr () -> IO String

#ifdef USE_JAVA_BACKEND
foreign import ccall "sendToJava"
  c_SendToJava :: Ptr () -> CString -> IO ()
foreign import ccall "getFromJava"
  c_GetFromJava :: Ptr () -> CString
sendToOut jenv x = withCString x $ \cstr -> c_SendToJava jenv cstr
getFromIn jenv = peekCString $ c_GetFromJava jenv
#else
-- Se não é para usar o java o ponteiro é simplemente ignorado
sendToOut jenv x = putStrLn x
getFromIn jenv = getLine
#endif
