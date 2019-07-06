package proj.view;

import proj.view.panes.GameGUIOutPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;

public class StorytimePanel extends JPanel implements GUIPanel
{
  private GameGUIOutPane textout;
  private GridBagConstraints c;

  public StorytimePanel()
  {
    super();

    c = new GridBagConstraints();
    setLayout(new GridBagLayout());
    setPreferredSize(new Dimension(800, 800));

    textout = new GameGUIOutPane();
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    add(textout.getContent(), c);
  }

  public synchronized void pause() {textout.pause();}
  public synchronized void resume() {textout.resume();}
  public Writable getTextOut() {return textout;}
  public void setInputEnabled() {}
  public boolean isEnabled() {return false;}
  public String getInput()  {return null;}
  public void inputChannel(int c) {}
}
