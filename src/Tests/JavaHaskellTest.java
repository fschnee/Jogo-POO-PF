public class JavaHaskellTest
{
  public native void callHaskellFunction();

  static
  {
    System.loadLibrary("HaskellBinds");
  }

  public static void main(String[] args)
  {
    new JavaHaskellTest().callHaskellFunction();
  }
}
