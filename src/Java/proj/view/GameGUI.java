package proj.view;

import proj.view.panes.GameGUIOutPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.BorderLayout;

public class GameGUI
{
  private JFrame window;
  private GameGUIOutPane textout;

  public GameGUI()
  {
    window = new JFrame("Game");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLayout(new BorderLayout());
    window.setSize(800, 800);

    textout = new GameGUIOutPane();

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
}
