package proj.view;

import proj.Global;
import proj.jogo.mobs.Character;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InventoryPanel extends JPanel implements GUIPanel
{
  private GridBagConstraints c;
  private Character selectedchar;

  public InventoryPanel()
  {
    super(new GridBagLayout());

    c = new GridBagConstraints();
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;

    JButton openusablesbutton = new JButton("Check Usables");
    openusablesbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Global.getGlobal().getGUI().setActivePane("Usables");
      }
    });
    JButton backbutton = new JButton("Go back");
    backbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if(Global.getGlobal().isInBattle()) Global.getGlobal().getGUI().setActivePane("Battle");
        else Global.getGlobal().getGUI().setActivePane("Traversal");
      }
    });

    add(openusablesbutton, c);
    c.gridy += 1;
    add(backbutton, c);
  }

  public void resume() {}
  public void pause() {}
  public Writable getTextOut() {return null;}
  public void setInputEnabled() {}
  public boolean isEnabled() {return false;}
  public String getInput() {return null;}
  public void inputChannel(int c) {}
}
