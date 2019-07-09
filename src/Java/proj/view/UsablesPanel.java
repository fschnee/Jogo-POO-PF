package proj.view;

import proj.Global;
import proj.jogo.items.Item;
import proj.jogo.items.Usable;
import proj.jogo.mobs.Character;
import proj.view.panes.HintsOutPane;
import proj.view.panes.DefaultOutPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsablesPanel extends JPanel implements GUIPanel
{
  private GridBagConstraints c;
  private Character selectedCharacter;
  private Item selectedUsable;
  private HintsOutPane hintout;
  private DefaultOutPane textout;
  private JPanel itemspanel;
  private JPanel characterselectpanel;

  public UsablesPanel()
  {
    super(new GridBagLayout());
    setBackground(Global.getColorScheme(Global.BG));
    c = new GridBagConstraints();

    hintout = new HintsOutPane();
    c.gridwidth = 4;
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    add(hintout.getTextPane(), c);

    textout = new DefaultOutPane();
    c.gridx = 1;
    c.gridy = 1;
    c.gridheight = 2;
    c.gridwidth = 2;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    add(textout.getContent(), c);

    itemspanel = new JPanel(new GridBagLayout());
    itemspanel.setBackground(Global.getColorScheme(Global.BG));
    itemspanel.setBorder(new TitledBorder(new LineBorder(Global.getColorScheme(Global.TEXT), 5), "Usables"));
    ((TitledBorder)itemspanel.getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
    ((TitledBorder)itemspanel.getBorder()).setTitleFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 18));
    c.gridx = 0;
    c.gridwidth = 1;
    c.fill = GridBagConstraints.VERTICAL;
    add(itemspanel, c);

    JButton cancelbutton = new JButton("Cancel Selection");
    cancelbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        selectedCharacter = null;
        selectedUsable = null;
        hintout.appendText("Canceled action", "default");
      }
    });
    JButton backbutton = new JButton("Back to inventory");
    backbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Global.getGlobal().getGUI().setActivePane("Inventory");
      }
    });

    characterselectpanel = new JPanel(new GridBagLayout());
    characterselectpanel.setBackground(Global.getColorScheme(Global.BG));
    characterselectpanel.setBorder(new TitledBorder(new LineBorder(Global.getColorScheme(Global.TEXT), 5), "Use on"));
    ((TitledBorder)characterselectpanel.getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
    ((TitledBorder)characterselectpanel.getBorder()).setTitleFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 18));
    c.gridx = 3;
    c.fill = GridBagConstraints.BOTH;
    add(characterselectpanel, c);
    c.gridx = 1;
    c.gridy = 3;
    c.gridwidth = 2;
    c.gridheight = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.NONE;
    add(cancelbutton, c);
    c.gridy = 4;
    add(backbutton, c);
  }

  public void update()
  {
    itemspanel.removeAll();
    characterselectpanel.removeAll();

    GridBagConstraints j = new GridBagConstraints();
    j.weightx = 1;
    j.weighty = 1;
    j.fill = GridBagConstraints.NONE;
    for(Item i : Global.getGlobal().getPlayer().getBackpack().getItems())
    {
      if(i instanceof Usable)
      {
        JPanel itempanel = new JPanel(new GridBagLayout());
        itempanel.setBackground(Global.getColorScheme(Global.BG));
        itempanel.setBorder(new TitledBorder(new LineBorder(Global.getColorScheme(Global.TEXT), 5), i.getName()));
        ((TitledBorder)itempanel.getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
        ((TitledBorder)itempanel.getBorder()).setTitleFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 12));
        GridBagConstraints k = new GridBagConstraints();
        k.fill = GridBagConstraints.BOTH;
        k.weightx = 1;
        k.weighty = 1;

        JButton examine = new JButton("Examine");
        examine.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            textout.appendText(i.getName(), "default-bold");
            textout.appendText(": " + i.getDescription() + "\n", "default");
            hintout.appendText("weight: "+ i.getWeight() + ", volume: " + i.getVolume() +
                               ", value: " + i.getValue(), "default");
          }
        });
        JButton select = new JButton("Select");
        select.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            selectedUsable = i;
            if(selectedCharacter != null)
            {
              useItem();
              selectedCharacter = null;
              selectedUsable = null;
            }
            else hintout.appendText("You mush select someone to use " + i.getName() + " on", "default");
          }
        });
        itempanel.add(examine, k);
        k.gridy += 1;
        itempanel.add(select, k);
        itemspanel.add(itempanel, j);
        j.gridy += 1;
      }
    }

    GridBagConstraints k = new GridBagConstraints();
    k.weightx = 1;
    k.weighty = 1;
    k.fill = GridBagConstraints.BOTH;
    for(Character ch : Global.getGlobal().getPlayer().getParty().get())
    {
      JButton targetbutton = new JButton(ch.getName());
      targetbutton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          selectedCharacter = ch;
          if(selectedUsable != null)
          {
            useItem();
            selectedCharacter = null;
            selectedUsable = null;
          }
          else hintout.appendText("You must select an item to use on " + ch.getName(), "default");
        }
      });
      characterselectpanel.add(targetbutton, k);
      k.gridx += 1;
    }
  }

  private void useItem()
  {
    hintout.appendText("Used " + selectedUsable.getName() + " on " + selectedCharacter.getName(), "default");
    ((Usable)selectedUsable).use(selectedCharacter);
    if(selectedUsable.isSingleUse())
    {
      Global.getGlobal().getPlayer().getBackpack().remove(selectedUsable);
      update();
    }
  }

  public synchronized void pause()
  {
    hintout.appendText(" ", "default");
    hintout.pause();
    textout.clear();
    textout.pause();
  }
  public synchronized void resume()
  {
    hintout.resume();
    textout.resume();
    update();
  }
  public Writable getTextOut() {return null;}
  public void setInputEnabled() {}
  public boolean isEnabled() {return false;}
  public String getInput() {return null;}
  public void inputChannel(int c) {}
}
