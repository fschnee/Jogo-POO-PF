package proj.view;

import proj.Global;
import proj.ResourceLoader;
import proj.view.GameGUI;
import proj.view.GUIPanel;
import proj.view.panes.TraversalHintsPane;
import proj.view.panes.TraversalOutPane;
import proj.jogo.spaces.Space;
import proj.jogo.common.Interactable;
import proj.jogo.mobs.Character;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TraversalPanel extends JPanel implements GUIPanel
{
  private JPanel traversaloptions;
  private JPanel interactablesholder;
  private JPanel menusholder;
  private TraversalOutPane textout;
  private Space currentlocation;
  private GridBagConstraints c;
  private TraversalHintsPane hinttextout;
  private int lookedaroundcount;
  private Character selectedchar;

  public TraversalPanel()
  {
    super();

    c = new GridBagConstraints();
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    setLayout(new GridBagLayout());

    textout = new TraversalOutPane();
    textout.resume();
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 2;
    c.gridheight = 2;
    add(textout.getTextPane(), c);


    hinttextout = new TraversalHintsPane();
    hinttextout.resume();
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 2;
    c.gridheight = 1;
    c.weightx = 1;
    c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    add(hinttextout.getTextPane(), c);

    traversaloptions = new JPanel();
    traversaloptions.setLayout(new GridBagLayout());
    traversaloptions.setBackground(Global.getColorScheme(Global.BG));
    traversaloptions.setBorder(new TitledBorder(new LineBorder(Global.getColorScheme(Global.TEXT), 5), "Traversal menu"));
    ((TitledBorder)traversaloptions.getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
    ((TitledBorder)traversaloptions.getBorder()).setTitleFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 18));
    JButton northbutton = new JButton("Go north");
    northbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent a) {Global.getGlobal().getTraversal().goNorth();}
    });
    JButton southbutton = new JButton("Go south");
    southbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent a) {Global.getGlobal().getTraversal().goSouth();}
    });
    JButton upbutton = new JButton("Go up");
    upbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {Global.getGlobal().getTraversal().goUp();}
    });
    JButton eastbutton = new JButton("Go east");
    eastbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent a) {Global.getGlobal().getTraversal().goEast();}
    });
    JButton westbutton = new JButton("Go west");
    westbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent a) {Global.getGlobal().getTraversal().goWest();}
    });
    JButton downbutton = new JButton("Go down");
    downbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent a) {Global.getGlobal().getTraversal().goDown();}
    });
    JButton lookaroundbutton = new JButton("Look around");
    lookaroundbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {displayInfo();}
    });
    JButton mapbutton = new JButton("Map");
    mapbutton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {Global.getGlobal().getTraversal().mapRequest();}
    });

    c.fill = GridBagConstraints.BOTH;
    c.weighty = 1;
    c.weightx = 1;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.gridx = 0;
    c.gridy = 0;
    traversaloptions.add(northbutton, c);
    c.gridy = 1;
    traversaloptions.add(southbutton, c);
    c.gridy = 2;
    traversaloptions.add(upbutton, c);
    c.gridy = 3;
    traversaloptions.add(lookaroundbutton, c);
    c.gridy = 0;
    c.gridx = 1;
    traversaloptions.add(eastbutton, c);
    c.gridy = 1;
    traversaloptions.add(westbutton, c);
    c.gridy = 2;
    traversaloptions.add(downbutton, c);
    c.gridy = 3;
    traversaloptions.add(mapbutton, c);

    c.gridx = 0;
    c.gridy = 3;
    c.gridwidth = 1;
    c.gridheight = 2;
    add(traversaloptions, c);

    interactablesholder = new JPanel();
    interactablesholder.setLayout(new GridBagLayout());
    interactablesholder.setBackground(Global.getColorScheme(Global.BG));
    interactablesholder.setBorder(new TitledBorder(new LineBorder(Global.getColorScheme(Global.TEXT), 5), "Interact with"));
    ((TitledBorder)interactablesholder.getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
    ((TitledBorder)interactablesholder.getBorder()).setTitleFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 18));

    menusholder = new JPanel();
    menusholder.setLayout(new GridBagLayout());
    menusholder.setBackground(Global.getColorScheme(Global.BG));
    menusholder.setBorder(new TitledBorder(new LineBorder(Global.getColorScheme(Global.TEXT), 5), "Menus"));
    ((TitledBorder)menusholder.getBorder()).setTitleColor(Global.getColorScheme(Global.TEXT));
    ((TitledBorder)menusholder.getBorder()).setTitleFont(new Font("Pixel Operator Mono Bold", Font.PLAIN, 18));
    c.gridy = 5;
    c.gridx = 0;
    c.gridwidth = 2;
    c.gridheight = 1;
    c.weighty = 0;
    add(menusholder, c);

    JButton inventorybtn = new JButton("Inventory");
    inventorybtn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {Global.getGlobal().getGUI().setActivePane("Inventory");}
    });
    c.gridy = 0;
    c.gridwidth = 1;
    c.fill = GridBagConstraints.VERTICAL;
    menusholder.add(inventorybtn, c);

    switchLocationTo(Global.getGlobal().getSpace("Starting Camp"));
  }

  public synchronized void switchLocationTo(Space s)
  {
    currentlocation = s;
    lookedaroundcount = 0;

    ArrayList<Integer> temp = new ArrayList<Integer>();
    temp.add(Integer.valueOf(20));
    textout.appendText("You are currently in " + s.getName() + "\n", "default", temp);

    GridBagConstraints w = new GridBagConstraints();
    interactablesholder.removeAll();
    if(currentlocation.getInteractables().size() == 0)
    {
      remove(interactablesholder);
    }
    else
    {
      w.gridx = 1;
      w.gridy = 3;
      w.gridheight = 2;
      w.weightx = 1;
      w.weighty = 1;
      w.fill = GridBagConstraints.BOTH;
      add(interactablesholder, w);

      w.weighty = 1;
      w.weightx = 1;
      w.gridheight = 1;
      for(Interactable i : currentlocation.getInteractables())
      {
        JButton interactablebutton = new JButton(i.getName());
        interactablebutton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent a)
          {
            if(selectedchar == null) hinttextout.appendText("You need to select someone " +
                                                            "to interact with " + i.getName(), "default");
            else i.interactUsing(selectedchar);
          }
        });
        interactablesholder.add(interactablebutton, w);
        w.gridy += 1;
      }
    }
  }

  public void displayInfo()
  {
    lookedaroundcount += 1;
    if(lookedaroundcount> 1)
    {
      if(lookedaroundcount > 3) hinttextout.appendText("STOP LOOKING AROUND AND PLAY THE GAME DUDE", "default-bold");
      else hinttextout.appendText("You have already looked around", "default");
    }
    else
    {
      ArrayList<Integer> temp = new ArrayList<Integer>();
      temp.add(Integer.valueOf(20));
      textout.appendTextNoClear("A paper on the wall says \"" + currentlocation.getDescription() + "\"", "default", temp);
      hinttextout.appendText("You look around to see if you find anything of interest", "default");
    }
  }

  public synchronized void pause() {hinttextout.pause();}
  public synchronized void resume() {hinttextout.resume();}
  public Writable getTextOut() {return hinttextout;}
  public void setInputEnabled() {}
  public void inputChannel(int c) {}
  public boolean isEnabled() {return false;}
  public String getInput() {return null;}
}
