// HsInt32 <==> StgInt32    <==> signed int <==> jint
// HsFloat <==> StgFloat    <==> float      <==> jfloat
// HsFFI.h <==> stg/Types.h <==> C          <==> jni_md.h

// HsPtr   <==> void*
// HsFFI.h <==> C

#include <stdlib.h>

// Coisas do Java
#include "cardgames_Blackjack.h"
#include <jni.h>

// Coisas do Haskell
#include <HsFFI.h>
#ifdef __GLASGOW_HASKELL__
#include "Blackjack_stub.h" // define "HsFloat run(HsInt32 a1, HsInt32 a2, HsPtr a3, HsPtr a4)"
extern void __stginit_Main(void);
#endif

void sendToJava (void* envptr, void* objptr, const char* stringFromHaskell);
char* getFromJava (void* envptr, void* objptr);

// Código-cola para chamar o jogo de dentro da JVM
JNIEXPORT jfloat JNICALL Java_cardgames_Blackjack_callhaskell
  (JNIEnv* env, jobject obj, jint options, jint seed)
{
  hs_init(0, NULL);
#ifdef __GLASGOW_HASKELL__
  hs_add_root(__stginit_Main);
#endif

  float payout = run(options, seed, env, obj);

  hs_exit();

  return 1.0;
  // return payout;
}

// declarada em CommonIO.hs; Manda uma linha para a JVM (usada para a
// comunicação com a GUI do Swing)
void sendToJava (void* envptr, void* objptr, const char* stringFromHaskell)
{
  JNIEnv* env = envptr;
  jobject obj = objptr;

  jclass objclass = (*env)->GetObjectClass(env, obj);
  jmethodID sendToGUI = (*env)->GetMethodID(env, objclass, "sendToGUI", "(Ljava/lang/String;)V");

  jstring stringToJava = (*env)->NewStringUTF(env, stringFromHaskell);
  (*env)->CallVoidMethod(env, obj, sendToGUI, stringToJava);
  // TODO: descobrir se precisa liberar a string em algum momento
}

// TODO: implementar
// Stub (declarada em CommonIO.hs), pega uma linha da JVM (usada para a
// comunicação com a GUI do Swing)
char* getFromJava (void* envptr, void* objptr)
{
  JNIEnv* env = envptr;
  jobject obj = objptr;

  // (*env)->getStringChars(...)
  // (*env)->ReleaseStringChars(...)
}
