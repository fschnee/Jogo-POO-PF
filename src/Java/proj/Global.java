package proj;

import proj.view.GameGUI;
import proj.jogo.TraversalManager;
import proj.jogo.Battle;
import proj.jogo.spaces.Space;
import proj.jogo.mobs.Player;
import proj.jogo.other.Chest;
import java.awt.Color;
import java.util.HashMap;

public class Global
{
  private static Global instance = null;
  private GameGUI g;
  private Player p;
  private TraversalManager tm;
  private Chest currentopenedchest;
  private Battle activebattle;
  private boolean inbattle;
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

  public static void init(TraversalManager tm)
  {
    if(instance == null) instance = new Global();
    instance.tm = tm;
  }

  public static Global getGlobal()
  {
    return instance;
  }

  public GameGUI getGUI() {return g;}
  public Player getPlayer() {return p;}
  public Space getSpace(String s) {return spaces.get(s);}
  public TraversalManager getTraversal() {return tm;}
  public Chest getCurrentOpenedChest() {return currentopenedchest;}
  public void setCurrentOpenedChest(Chest c) {currentopenedchest = c;}
  public Battle getActiveBattle() {return activebattle;}
  public void setActiveBattle(Battle b) {activebattle = b;inbattle = true;}
  public void setBattleEnd() {inbattle = false;}
  public boolean isInBattle() {return inbattle;}

  private Global()
  {
    this.spaces = ResourceLoader.loadMap("assets/text/spaces/", "StartingCamp");
  }

  //Color info
  public static int GM = 0;
  public static int TEXT = 0;
  public static int USER = 1;
  public static int HIGHLIGHT = 1;
  public static int P1 = 2;
  public static int P2 = 3;
  public static int P3 = 4;
  public static int BG = 5;
  public static int DEAD = 6;
  public static Color getColorScheme(int colour)
  {
    int[] colours = {0xCEDABD, 0xF8F490, 0xEB8CB7, 0xB18538, 0x33E0DD,
                     0x000000, 0x333333};
    return new Color(colours[colour]);
  }
}
