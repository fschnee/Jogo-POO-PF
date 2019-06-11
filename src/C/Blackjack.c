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
#include "Blackjack_stub.h" // define "HsFloat run(HsInt32 a1, HsInt32 a2, HsPtr a3)"
extern void __stginit_Main(void);
#endif

// Código-cola para chamar o jogo de dentro da JVM
JNIEXPORT jfloat JNICALL Java_cardgames_Blackjack_callhaskell
  (JNIEnv* env, jobject obj, jint options, jint seed)
{
  hs_init(0, NULL);
#ifdef __GLASGOW_HASKELL__
  hs_add_root(__stginit_Main);
#endif

  float payout = run(options, seed, env);

  hs_exit();

  return payout;
}

// TODO: implementar
// Stub (declarada em CommonIO.hs), manda uma linha para a JVM (usada para a
// comunicação com a GUI do Swing)
void sendToJava (void* envptr, const char* stringFromHaskell)
{
  JNIEnv* env = envptr;
  printf("IN C: %s\n", stringFromHaskell);
}

// TODO: implementar
// Stub (declarada em CommonIO.hs), pega uma linha da JVM (usada para a
// comunicação com a GUI do Swing)
char* getFromJava (void* envptr)
{
  JNIEnv* env = envptr;
}
