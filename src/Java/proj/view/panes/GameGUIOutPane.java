package proj.view.panes;

import proj.view.GameGUI;

public class GameGUIOutPane extends GenericScrollableOutPane
{
  public GameGUIOutPane()
  {
    super();
    tp.setBackground(GameGUI.getColorScheme(GameGUI.BG));
  }
}
