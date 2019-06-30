package proj.cardgames;

import proj.view.Writable;
import proj.Tools;
import java.awt.Font;

public abstract class CardGame
{
  protected Font outfont;
  protected Writable output;

  // Returns the ammount to be added to the player
  public abstract int play(int bet);

  public void sendToGUI(String str)
  {
    if(str.startsWith("--")) System.out.println(str);
    else
    {
      if(str.length() > 24)
        for(String s : Tools.splitStringEvery(str, 24))
           output.appendText(s + '\n', "terminal");
      else output.appendText(str + '\n', "terminal");
    }
  }

  public String getFromGUI() {return "PLACEHOLDER";}
}
