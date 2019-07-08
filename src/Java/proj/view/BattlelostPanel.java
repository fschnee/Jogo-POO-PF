package proj.view;

import proj.Global;
import proj.view.GUIPanel;
import proj.view.panes.BattleOutPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class BattlelostPanel extends JPanel implements GUIPanel
{
  private BattleOutPane textout;

  public BattlelostPanel()
  {
    super();
    setBackground(Global.getColorScheme(Global.BG));

    textout = new BattleOutPane();
    textout.appendText("You lost, press ENTER to quit", "default-bold");

    add(textout.getTextPane(), BorderLayout.CENTER);
  }

  public synchronized void pause() {textout.pause();}
  public synchronized void resume() {textout.resume();}
  public Writable getTextOut() {return textout;}
  public void setInputEnabled() {}
  public boolean isEnabled() {return false;}
  public String getInput() {return null;}
  public void inputChannel(int c)
  {
    if(c == '\n') System.exit(1);
  }
}
