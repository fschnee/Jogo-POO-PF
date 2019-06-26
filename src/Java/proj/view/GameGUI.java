package proj.view;

import proj.view.panes.GameGUIOutPane;
import proj.view.Writeable;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;
// import java.awt.GridBagLayout;
// import java.awt.GridBagConstraints;

public class GameGUI
{
  private JFrame window;
  private GameGUIOutPane textout;
  private String state;
  // private GridBagConstraints c;

  public GameGUI()
  {
    state = "Storytime";
    // c = new GridBagConstraints();

    window = new JFrame("Game");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLayout(new BorderLayout());
    window.setSize(800, 800);

    textout = new GameGUIOutPane();

    window.add(textout.getContent());
    //setupLayouts();
    //updateLayout();
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

  public void setState(String newstate)
  {
    if(this.state.equals(newstate)) return;

    this.state = newstate;
    updateLayout();
  }

  // TODO: implementar cardLayout contendo varios GridBagLayout
  public void setupLayouts()
  {
  }

  // TODO: implementar cardLayout contendo varios GridBagLayout
  public void updateLayout()
  {
    // Show just textout
    if(state.equals("Storytime"))
    {
    }
    // Show lifebars and menus
    else if(state.equals("Fight"))
    {
    }
    // Show inventory stuff
    else if(state.equals("Inventory"))
    {
    }
  }

  public Writeable getWritable() {return this.textout;}
}
