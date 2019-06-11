package cardgames;

import cardgames.CardGame;

public class Blackjack implements CardGame
{
  public static class Options
  {
    public static final int DEBUG = 1;
    public static final int VERBOSE = 2;
    public static final int USESEED = 4;
  }

  static
  {
    System.loadLibrary("Blackjack");
  }
  public native float callhaskell(int options, int seed);

  public static void main(String[] args)
  {
    new Blackjack().play(0);
  }

  public int play(int bet)
  {
    int x = Math.round(bet * begin_with(Options.VERBOSE));
    return x;
  }

  private float begin_with(int options, int seed) {return callhaskell(options, seed);}
  private float begin_with(int options) {return callhaskell(options, 0);}
}
