package proj.view;

import proj.view.Writeable;
import proj.view.panes.CardGameTermOutPane;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

public class CardGameTerm
{
  private JFrame window;
  private CardGameTermOutPane textout;

  public CardGameTerm()
  {
    window = new JFrame("Terminalis");
    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    window.setLayout(new BorderLayout());
    window.setSize(350, 350);

    textout = new CardGameTermOutPane();

    window.add(textout.getContent());
    window.setVisible(true);
  }

  public static int BG = 0;
  public static int OTHER = 1;
  public static int TEXT = 2;
  public static int HIGHLIGHT = 3;
  public static Color getColorScheme(int colour)
  {
    int[] colours = {0x000000, 0xBCBBB2, 0xCEDABD, 0xF8F4C1};
    return new Color(colours[colour]);
  }

  public Writeable getTextOut()
  {
    return textout;
  }
}
