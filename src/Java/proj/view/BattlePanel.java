package proj.view;

import proj.Global;
import proj.view.GUIPanel;
import proj.view.panes.BattleOutPane;
import proj.view.panes.HintsOutPane;
import proj.jogo.Battle;
import proj.jogo.mobs.Attack;
import proj.jogo.mobs.Character;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class BattlePanel extends JPanel implements GUIPanel
{
  private GridBagConstraints c;
  private Battle currentbattle;
  private JPanel enemiespanel;
  private BattleOutPane textout;
  private HintsOutPane hintout;
  private JPanel alliespanel;
  private Character selectedtarget;
  private Character turnowner;
  private HashMap<String, JPanel> allypanels;
  private HashMap<String, JLabel> allylabels;
  private HashMap<String, JPanel> enemypanels;
  private HashMap<String, JLabel> enemylabels;

  public BattlePanel()
  {
    super(new GridBagLayout());
    c = new GridBagConstraints();
    allypanels = new HashMap<String, JPanel>();
    allylabels = new HashMap<String, JLabel>();
    enemypanels = new HashMap<String, JPanel>();
    enemylabels = new HashMap<String, JLabel>();

    enemiespanel = new JPanel(new GridBagLayout());
    enemiespanel.setBackground(Global.getColorScheme(Global.BG));
    enemiespanel.setBorder(new TitledBorder(new LineBorder(Global.getColorScheme(Global.TEXT), 5), "Enemies"));
    ((TitledBorder)enemiespanel.getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
    ((TitledBorder)enemiespanel.getBorder()).setTitleFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 18));
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1;
    c.weighty = 1;
    c.gridx = 0;
    c.gridy = 0;
    add(enemiespanel, c);

    textout = new BattleOutPane();
    textout.resume();
    c.gridy += 1;
    add(textout.getContent(), c);

    hintout = new HintsOutPane();
    hintout.resume();
    c.gridy += 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    add(hintout.getTextPane(), c);

    alliespanel = new JPanel(new GridBagLayout());
    alliespanel.setBackground(Global.getColorScheme(Global.BG));
    alliespanel.setBorder(new TitledBorder(new LineBorder(Global.getColorScheme(Global.TEXT), 5), "Allies"));
    ((TitledBorder)alliespanel.getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
    ((TitledBorder)alliespanel.getBorder()).setTitleFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 18));
    c.gridy += 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    add(alliespanel, c);
  }

  public void remakeGUI()
  {
    currentbattle = Global.getGlobal().getActiveBattle();
    turnowner = currentbattle.getTurnOwner();

    // limpando as coisas
    enemiespanel.removeAll();
    alliespanel.removeAll();
    allypanels.clear();
    allylabels.clear();
    enemypanels.clear();
    enemylabels.clear();
    selectedtarget = null;
    try
    {
      StyledDocument doc = textout.getTextPane().getStyledDocument();
      doc.remove(0, doc.getLength());
    }
    catch (BadLocationException e) {e.printStackTrace();}

    textout.appendText("A battle begins, as a team made of ", "default");
    for(int i = 0; i < currentbattle.getEnemies().get().size(); ++i)
    {
      if(i == currentbattle.getEnemies().get().size() - 1)
        textout.appendText(currentbattle.getEnemies().get(i).getName() + " ", "default-bold");
      else
      {
        textout.appendText(currentbattle.getEnemies().get(i).getName(), "default-bold");
        textout.appendText(", ", "default");
      }
    }
    textout.appendText("approaches\n", "default");

    GridBagConstraints w = new GridBagConstraints();
    w.fill = GridBagConstraints.BOTH;
    w.weightx = 1;
    w.weighty = 1;
    for(Character ch : currentbattle.getAllies().get())
    {
      JPanel characterpanel = new JPanel(new GridBagLayout());
      characterpanel.setBackground(Global.getColorScheme(Global.BG));
      characterpanel.setBorder(new TitledBorder(new LineBorder(Global.getColorScheme(Global.TEXT), 5), ch.getName()));
      if(ch.getName().equals(turnowner.getName()))
        ((TitledBorder)characterpanel.getBorder()).setTitleColor(Global.getColorScheme(Global.P3));
      else ((TitledBorder)characterpanel.getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
      ((TitledBorder)characterpanel.getBorder()).setTitleFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 18));
      allypanels.put(ch.getName(), characterpanel);

      GridBagConstraints k = new GridBagConstraints();
      k.fill = GridBagConstraints.BOTH;
      k.weightx = 1;
      k.weighty = 0;
      k.gridx = 0;
      k.gridy = 0;
      JLabel characterlabel = new JLabel();
      characterlabel.setFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 22));
      characterlabel.setForeground(Global.getColorScheme(Global.TEXT));
      characterpanel.add(characterlabel, k);
      allylabels.put(ch.getName(), characterlabel);
      k.gridy += 1;
      k.weighty = 1;

      for(Attack a : ch.getMoves())
      {
        JButton attackbutton = new JButton(a.getName());
        attackbutton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            if(ch.getHealth() > 0)
            {
              if(ch.getName().equals(turnowner.getName()))
              {
                if(ch.canUse(a) == false) hintout.appendText(ch.getName() + " is too tired for that move", "default");
                else if(selectedtarget != null) currentbattle.attack(selectedtarget, a);
                else hintout.appendText(ch.getName() + " is not targeting any enemy", "default");
              }
              else hintout.appendText("It's not " + ch.getName() + "'s turn, it's " + turnowner.getName() + "'s", "default");
            }
            else hintout.appendText(ch.getName() + " already fainted", "default");
          }
        });
        characterpanel.add(attackbutton, k);
        k.gridy += 1;
      }

      alliespanel.add(characterpanel, w);
      w.gridy += 1;
    }

    w = new GridBagConstraints();
    w.fill = GridBagConstraints.BOTH;
    w.weightx = 1;
    w.weighty = 1;
    for(Character en : currentbattle.getEnemies().get())
    {
      JPanel enemypanel = new JPanel(new GridBagLayout());
      enemypanel.setBackground(Global.getColorScheme(Global.BG));
      enemypanel.setBorder(new TitledBorder(new LineBorder(Global.getColorScheme(Global.TEXT), 5), en.getName()));
      ((TitledBorder)enemypanel.getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
      ((TitledBorder)enemypanel.getBorder()).setTitleFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 18));
      enemypanels.put(en.getName(), enemypanel);

      GridBagConstraints k = new GridBagConstraints();
      k.fill = GridBagConstraints.BOTH;
      k.weightx = 1;
      k.weighty = 0;
      k.gridx = 0;
      k.gridy = 0;
      JLabel enemylabel = new JLabel();
      enemylabel.setFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 22));
      enemylabel.setForeground(Global.getColorScheme(Global.TEXT));
      enemypanel.add(enemylabel, k);
      enemylabels.put(en.getName(), enemylabel);
      k.gridy += 1;
      k.weighty = 1;

      JButton targetbutton = new JButton("Target this enemy");
      targetbutton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          if(en.getHealth() > 0)
          {
            if(selectedtarget != null)
            {
              ((TitledBorder)enemypanels.get(selectedtarget.getName()).getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
              enemypanels.get(selectedtarget.getName()).repaint();
            }
            ((TitledBorder)enemypanel.getBorder()).setTitleColor(Global.getColorScheme(Global.P2));
            enemypanel.repaint();
            selectedtarget = en;
          }
          else hintout.appendText(en.getName() + " already fainted", "default");
        }
      });
      enemypanel.add(targetbutton, k);
      k.gridy += 1;

      JButton examinebutton = new JButton("Examine this enemy");
      examinebutton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          textout.appendText("-----Examining enemy-----\n", "default");
          textout.appendText("Name: " + en.getName() + "\nDescription: " + en.getDescription(), "default");
          textout.appendText("\n--Moves--\n", "default");
          for(Attack a : en.getMoves())
            textout.appendText(a.getName() + ": " + a.getDescription() + ". Does " + a.getDamage() +
                               " damage and consumes " + a.getEnergyConsumption() + " energy\n", "default");
        }
      });
      enemypanel.add(examinebutton, k);

      enemiespanel.add(enemypanel, w);
      w.gridy += 1;
    }

    updateCharacterStatus();
    textout.appendText("It is ", "default");
    textout.appendText(turnowner.getName() + "'s", "default-bold");
    textout.appendText(" turn now\n", "default");
  }

  public void signalAttack(Character attacker, Character target, Attack atk)
  {
    textout.appendText(attacker.getName() + " used ", "default");
    textout.appendText(atk.getName(), "default-bold");
    textout.appendText(" against " + target.getName() + ", dealing ", "default");
    textout.appendText(atk.getDamage() + " damage", "default-bold");
    textout.appendText(". ", "default");
    boolean enemyturnowner = currentbattle.getEnemies().get().contains(turnowner);
    JPanel turnownerpanel;
    if(selectedtarget != null && selectedtarget.getHealth() <= 0) selectedtarget = null;
    if(enemyturnowner == false)
      turnownerpanel = allypanels.get(turnowner.getName());
    else turnownerpanel = enemypanels.get(turnowner.getName());
    ((TitledBorder)turnownerpanel.getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
    turnownerpanel.repaint();

    turnowner = currentbattle.getTurnOwner();
    enemyturnowner = currentbattle.getEnemies().get().contains(turnowner);
    if(enemyturnowner == false)
    {
      ((TitledBorder)allypanels.get(turnowner.getName()).getBorder()).setTitleColor(Global.getColorScheme(Global.P3));
      allypanels.get(turnowner.getName()).repaint();
    }
    else
    {
      ((TitledBorder)enemypanels.get(turnowner.getName()).getBorder()).setTitleColor(Global.getColorScheme(Global.HIGHLIGHT));
      enemypanels.get(turnowner.getName()).repaint();
    }
    textout.appendText("It is ", "default");
    textout.appendText(turnowner.getName() + "'s", "default-bold");
    textout.appendText(" turn now\n", "default");

    updateCharacterStatus();
    if(enemyturnowner == true) currentbattle.advanceTurn();
  }

  private void updateCharacterStatus()
  {
    for(Character ally : currentbattle.getAllies().get())
    {
      allylabels.get(ally.getName()).setText(ally.getHealth() + "/" + ally.getMaxHealth() + "HP " +
                                             ally.getEnergy() + "/" + ally.getMaxEnergy() + "EN");
      if(ally.getHealth() <= 0)
      {
        ((TitledBorder)allypanels.get(ally.getName()).getBorder()).setTitleColor(Global.getColorScheme(Global.DEAD));
        allypanels.get(ally.getName()).repaint();
      }
    }
    for(Character enemy : currentbattle.getEnemies().get())
    {
      enemylabels.get(enemy.getName()).setText(enemy.getHealth() + "/" + enemy.getMaxHealth() + "HP " +
                                               enemy.getEnergy() + "/" + enemy.getMaxEnergy() + "EN");
      if(selectedtarget != null && selectedtarget.getName().equals(enemy.getName()))
        ((TitledBorder)enemypanels.get(enemy.getName()).getBorder()).setTitleColor(Global.getColorScheme(Global.P2));
      if(enemy.getHealth() <= 0)
      {
        ((TitledBorder)enemypanels.get(enemy.getName()).getBorder()).setTitleColor(Global.getColorScheme(Global.DEAD));
        enemypanels.get(enemy.getName()).repaint();
      }
    }
  }

  public synchronized void pause()
  {
    textout.clear();
    textout.pause();
    hintout.appendText(" ","default");
    hintout.pause();
  }
  public synchronized void resume() {textout.resume();hintout.resume();}
  public Writable getTextOut() {return textout;}
  public void setInputEnabled() {}
  public boolean isEnabled() {return false;}
  public String getInput() {return null;}
  public void inputChannel(int c) {}
}
