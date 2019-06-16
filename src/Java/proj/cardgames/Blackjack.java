package proj.cardgames;

import proj.cardgames.CardGame;
import proj.cardgames.GameOptions;
import proj.view.Writeable;

public class Blackjack extends CardGame
{
  static
  {
    System.loadLibrary("Blackjack");
  }
  private native float callhaskell(int options, int seed);

  public Blackjack(Writeable out)
  {
    super();

    this.output = out;
    setupFont();
  }

  public int play(int bet)
  {
    int x = Math.round(bet * callhaskell(GameOptions.VERBOSE | GameOptions.DEBUG, 0));
    return x;
  }
}
