package proj.cardgames;

import proj.view.Writeable;
import java.awt.Font;

public abstract class CardGame
{
  protected Font outfont;
  protected Writeable output;

  // Returns the ammount to be added to the player
  public abstract int play(int bet);

  public void sendToGUI(String str)
  {
    if(str.startsWith("--")) System.out.println(str);
    else output.appendText(str + '\n', "terminal");
  }

  public String getFromGUI() {return "PLACEHOLDER";}
}
