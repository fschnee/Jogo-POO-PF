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
    output.appendText(str + '\n', outfont);
  }

  public void getFromGUI(String str) {}

  protected void setupFont()
  {
    outfont = Font.decode("VCR OSD Mono").deriveFont(16f);
    // TODO: se não funcionar, carregar "Perfect DOS VGA 437"
  }
}
