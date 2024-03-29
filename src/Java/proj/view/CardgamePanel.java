package proj.view;

import proj.Global;
import proj.view.Writable;
import proj.view.Pausable;
import proj.view.panes.CardGameTermOutPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CardgamePanel extends JPanel implements GUIPanel
{
  private CardGameTermOutPane textout;
  private GridBagConstraints c;

  public CardgamePanel()
  {
    super();

    c = new GridBagConstraints();
    setLayout(new GridBagLayout());

    textout = new CardGameTermOutPane();
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 3;
    c.gridheight = 2;
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.VERTICAL;
    add(textout.getContent(), c);

    JPanel buttonholder = new JPanel(new FlowLayout());
    JButton jbt1 = new JButton("Hit");
    JButton jbt2 = new JButton("Stand");
    JButton jbt3 = new JButton("Quit");
    jbt3.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        Global.getGlobal().getGUI().setActivePane("Traversal");
      }
    });
    c.gridx = 0;
    c.gridy = 3;
    c.gridwidth = 3;
    c.gridheight = 1;
    c.weightx = 0;
    c.weighty = 0;
    c.fill = GridBagConstraints.BOTH;
    add(buttonholder, c);
    buttonholder.add(jbt1);
    buttonholder.add(jbt2);
    buttonholder.add(jbt3);
  }

  public synchronized void pause() {textout.pause();}
  public synchronized void resume() {textout.resume();}
  public synchronized Writable getTextOut() {return textout;}
  public void setInputEnabled() {}
  public boolean isEnabled() {return false;}
  public String getInput()  {return null;}
  public void inputChannel(int c) {}
}
