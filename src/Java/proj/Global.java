package proj;

import proj.view.GameGUI;

public class Global
{
  private static Global instance = null;
  private GameGUI g;

  public static void init(GameGUI g)
  {
    if(instance == null) instance = new Global(g);
  }

  public static Global getGlobal()
  {
    return instance;
  }

  public GameGUI getGUI() {return g;}

  private Global(GameGUI g)
  {
    this.g = g;
  }
}
