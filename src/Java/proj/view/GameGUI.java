package proj.view;

import proj.view.panes.GameGUIOutPane;
import proj.view.CardgamePanel;
import proj.view.StorytimePanel;
import proj.view.Writeable;
import proj.view.GUIPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.util.HashMap;

public class GameGUI extends JFrame
{
  private HashMap<String, GUIPanel> panels;
  private JPanel panelholder;
  private String currpanel;

  public GameGUI()
  {
    super("Game");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setSize(800, 800);

    panels = new HashMap<String, GUIPanel>();
    panelholder = new JPanel();
    panelholder.setLayout(new CardLayout());
    panelholder.setPreferredSize(new Dimension(800, 800));
    panelholder.setBackground(getColorScheme(BG));

    CardgamePanel cgt = new CardgamePanel();
    StorytimePanel storytimepanel = new StorytimePanel();
    panelholder.add(storytimepanel, "Storytime");
    panelholder.add(cgt, "Terminal");
    currpanel = "Storytime";
    panels.put("Terminal", cgt);
    panels.put("Storytime", storytimepanel);
    panels.get(currpanel).resume();

    getContentPane().add(panelholder, BorderLayout.CENTER);
    setVisible(true);
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

  public Writeable getTextout(String panel)
  {
    GUIPanel temp = panels.get(panel);
    if(temp != null) return temp.getTextOut();
    return null;
  }
  public Writeable getTextout() {return panels.get(currpanel).getTextOut();}

  public synchronized void setActivePane(String newactive)
  {
    if(!newactive.equals(currpanel))
    {
      panels.get(currpanel).pause();
      currpanel = newactive;
      panels.get(newactive).resume();
    }

    ((CardLayout)panelholder.getLayout()).show(panelholder, newactive);
  }
}
