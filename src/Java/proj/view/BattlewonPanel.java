package proj.view;

import proj.Global;
import proj.view.GUIPanel;
import proj.view.panes.BattleOutPane;
import javax.swing.JPanel;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import java.awt.BorderLayout;

public class BattlewonPanel extends JPanel implements GUIPanel
{
  private BattleOutPane textout;

  public BattlewonPanel()
  {
    super();
    setBackground(Global.getColorScheme(Global.BG));

    textout = new BattleOutPane();

    add(textout.getTextPane(), BorderLayout.CENTER);
  }

  public synchronized void pause()
  {
    textout.clear();
    textout.pause();
  }
  public synchronized void resume()
  {
    textout.resume();
    textout.appendText("You won, press ENTER to continue", "default-bold");
    Global.getGlobal().setBattleEnd();
  }
  public Writable getTextOut() {return textout;}
  public void setInputEnabled() {}
  public boolean isEnabled() {return false;}
  public String getInput() {return null;}
  public void inputChannel(int c)
  {
    if(c == '\n')
    {
      try
      {
        StyledDocument doc = textout.getTextPane().getStyledDocument();
        doc.remove(0, doc.getLength());
      }
      catch (BadLocationException e) {e.printStackTrace();}

      Global.getGlobal().getGUI().setActivePane("Traversal");
    }
  }
}
