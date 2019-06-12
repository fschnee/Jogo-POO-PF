{-# LANGUAGE CPP, ForeignFunctionInterface #-}

module CommonIO where

import Foreign.Ptr
#ifdef USE_JAVA_BACKEND
import Foreign.C.String
#endif

sendToOut :: Ptr () -> Ptr () -> String -> IO ()
getFromIn :: Ptr () -> Ptr () -> IO String

#ifdef USE_JAVA_BACKEND
foreign import ccall "sendToJava"
  c_SendToJava :: Ptr () -> Ptr () -> CString -> IO ()
foreign import ccall "getFromJava"
  c_GetFromJava :: Ptr () -> Ptr () -> CString
sendToOut jenv jobj x = withCString x $ \cstr -> c_SendToJava jenv jobj cstr
getFromIn jenv jobj = peekCString $ c_GetFromJava jenv jobj
#else
-- Se não é para usar o java o ponteiro é simplemente ignorado
sendToOut jenv jobj x = putStrLn x
getFromIn jenv jobj = getLine
#endif
