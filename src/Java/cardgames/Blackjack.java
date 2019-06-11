package cardgames;

import cardgames.CardGame;
import cardgames.GameOptions;

public class Blackjack implements CardGame
{
  static
  {
    System.loadLibrary("Blackjack");
  }
  private native float callhaskell(int options, int seed);

  public static void main(String[] args)
  {
    new Blackjack().play(0);
  }

  public int play(int bet)
  {
    int x = Math.round(bet * begin_with(GameOptions.VERBOSE | GameOptions.DEBUG));
    return x;
  }

  private float begin_with(int options, int seed) {return callhaskell(options, seed);}
  private float begin_with(int options) {return callhaskell(options, 0);}
}
