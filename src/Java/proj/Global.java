package proj;

import proj.view.GameGUI;
import proj.jogo.spaces.Space;
import java.util.HashMap;

public class Global
{
  private static Global instance = null;
  private GameGUI g;
  private HashMap<String, Space> spaces;

  public static void init(GameGUI g)
  {
    if(instance == null) instance = new Global(g);
    instance.spaces = ResourceLoader.loadMap("assets/text/spaces/", "StartingCamp");
  }

  public static Global getGlobal()
  {
    return instance;
  }

  public GameGUI getGUI() {return g;}
  public Space getSpace(String s) {return spaces.get(s);}

  private Global(GameGUI g)
  {
    this.g = g;
  }
}
