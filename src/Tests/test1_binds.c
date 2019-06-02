#include <HsFFI.h>
#ifdef __GLASGOW_HASKELL__
#include "HaskellTestFunctions_stub.h"
extern void __stginit_HaskellTestFunctions(void);
#endif

int main(int argc, char *argv[])
{
  hs_init(&argc, &argv);
#ifdef __GLASGOW_HASKELL__
  hs_add_root(__stginit_HaskellTestFunctions);
#endif

  testHaskellOutput();

  hs_exit();
  return 0;
}
