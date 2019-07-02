package proj.view;

import proj.view.panes.GameGUIOutPane;
import proj.view.CardgamePanel;
import proj.view.StorytimePanel;
import proj.view.Writable;
import proj.view.GUIPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class GameGUI extends JFrame
{
  private HashMap<String, GUIPanel> panels;
  private JPanel panelholder;
  private String currpanel;
  private String prevpanel;

  public GameGUI()
  {
    super("Game");
  }

  public void setupGUI()
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setSize(800, 800);
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new CustomKeyHandler());

    panels = new HashMap<String, GUIPanel>();
    panelholder = new JPanel();
    panelholder.setLayout(new CardLayout());
    panelholder.setPreferredSize(new Dimension(800, 800));
    panelholder.setBackground(getColorScheme(BG));
    panelholder.setBorder(new MatteBorder(12, 12, 12, 12, getColorScheme(BG)));

    PlayerSetupPanel playersetuppanel = new PlayerSetupPanel();
    panelholder.add(playersetuppanel, "Setup");
    currpanel = "Setup";
    prevpanel = "Setup";
    panels.put("Setup", playersetuppanel);
    panels.get(currpanel).resume();

    StorytimePanel storytimepanel = new StorytimePanel();
    panelholder.add(storytimepanel, "Storytime");
    panels.put("Storytime", storytimepanel);

    CardgamePanel cgt = new CardgamePanel();
    panelholder.add(cgt, "Terminal");
    panels.put("Terminal", cgt);

    getContentPane().add(panelholder, BorderLayout.CENTER);
    setVisible(true);
  }

  // Color Info
  public static int BG = 0;
  public static int OTHER = 1;
  public static int TEXT = 2;
  public static int HIGHLIGHT = 3;
  public static Color getColorScheme(int colour)
  {
    int[] colours = {0x000000, 0xBCBBB2, 0xCEDABD, 0xF8F490};
    return new Color(colours[colour]);
  }

  public Writable getTextOut(String panel)
  {
    GUIPanel temp = panels.get(panel);
    if(temp != null) return temp.getTextOut();
    return null;
  }
  public synchronized Writable getTextOut() {return panels.get(currpanel).getTextOut();}

  public synchronized void setActivePane(String newactive)
  {
    if(!newactive.equals(currpanel))
    {
      prevpanel = currpanel;
      panels.get(currpanel).pause();
      currpanel = newactive;
      panels.get(newactive).resume();
    }

    ((CardLayout)panelholder.getLayout()).show(panelholder, newactive);
  }

  public synchronized GUIPanel getPanel(String panel) {return panels.get(panel);}
  public synchronized String getPrevPanel() {return prevpanel;}

  // Estava tendo problemas de foco usando KeyListener então...
  private class CustomKeyHandler implements KeyEventDispatcher {
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED){
            panels.get(currpanel).inputChannel(e.getKeyChar());
        }
        return false;
    }
  }
}
