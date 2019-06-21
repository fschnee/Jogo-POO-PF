package proj.view;

import proj.view.Writeable;
import proj.view.GameTermOutPane;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

public class GameTerm
{
  private JFrame frame;
  private GameTermOutPane textout;

  public GameTerm()
  {
    frame = new JFrame("Terminalis");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.setSize(600, 600);

    textout = new GameTermOutPane();

    frame.add(textout.getContent());
    frame.setVisible(true);
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
