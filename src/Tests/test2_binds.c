#include <stdlib.h>

// Coisas do Java
#include "JavaHaskellTest.h"
#include <jni.h>

// Coisas do Haskell
#include <HsFFI.h>
#ifdef __GLASGOW_HASKELL__
#include "HaskellTestFunctions_stub.h"
extern void __stginit_HaskellTestFunctions(void);
#endif

JNIEXPORT void JNICALL Java_JavaHaskellTest_callHaskellFunction (JNIEnv *env, jobject obj)
{
  hs_init(0, NULL);
#ifdef __GLASGOW_HASKELL__
  hs_add_root(__stginit_HaskellTestFunctions);
#endif

  testHaskellOutput();

  hs_exit();
}
