package proj.view;

import proj.view.panes.GameGUIOutPane;
import javax.swing.JPanel;

public class StorytimePanel extends JPanel implements GUIPanel
{
  private GameGUIOutPane textout;

  public StorytimePanel()
  {
    super();

    textout = new GameGUIOutPane();
    add(textout.getContent());
  }

  public synchronized void pause() {}
  public synchronized void resume() {}
  public Writeable getTextOut() {return textout;}
}
