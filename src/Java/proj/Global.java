package proj;

import proj.view.GameGUI;
import proj.jogo.spaces.Space;
import proj.jogo.mobs.Player;
import java.util.HashMap;

public class Global
{
  private static Global instance = null;
  private GameGUI g;
  private Player p;
  private HashMap<String, Space> spaces;

  public static void init(GameGUI g)
  {
    if(instance == null) instance = new Global();
    instance.g = g;
  }

  public static void init(Player p)
  {
    if(instance == null) instance = new Global();
    instance.p = p;
  }

  public static Global getGlobal()
  {
    return instance;
  }

  public GameGUI getGUI() {return g;}
  public Player getPlayer() {return p;}
  public Space getSpace(String s) {return spaces.get(s);}

  private Global()
  {
    this.spaces = ResourceLoader.loadMap("assets/text/spaces/", "StartingCamp");
  }
}
