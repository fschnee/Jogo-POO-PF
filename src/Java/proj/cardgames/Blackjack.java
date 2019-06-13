package proj.cardgames;

import proj.cardgames.CardGame;
import proj.cardgames.GameOptions;
import proj.view.Writeable;

public class Blackjack implements CardGame
{
  private Writeable out;

  public Blackjack(Writeable out)
  {
    this.out = out;
  }

  static
  {
    System.loadLibrary("Blackjack");
  }
  private native float callhaskell(int options, int seed);

  public int play(int bet)
  {
    int x = Math.round(bet * callhaskell(GameOptions.VERBOSE | GameOptions.DEBUG, 0));
    return x;
  }

  public void sendToGUI(String str)
  {
    out.appendText(str + '\n');
  }

  public void getFromGUI(String str)
  {
  }
}
