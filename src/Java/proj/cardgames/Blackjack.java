package proj.cardgames;

import proj.cardgames.CardGame;
import proj.cardgames.GameOptions;
import proj.view.Writable;
import proj.ResourceLoader;

public class Blackjack extends CardGame
{
  static
  {
    ResourceLoader.loadLibrary("libBlackjack.so");
  }
  private native float callhaskell(int options, int seed);

  public Blackjack(Writable out)
  {
    super();

    this.output = out;
  }

  public int play(int bet)
  {
    int x = Math.round(bet * callhaskell(GameOptions.VERBOSE | GameOptions.DEBUG, 0));
    return x;
  }
}
