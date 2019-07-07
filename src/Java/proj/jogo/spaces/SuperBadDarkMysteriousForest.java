package proj.jogo.spaces;

import proj.Global;
import proj.ResourceLoader;
import proj.jogo.spaces.Space;
import proj.jogo.common.Interactable;
import proj.jogo.mobs.Team;
import proj.jogo.mobs.Viper;
import java.util.ArrayList;

public class SuperBadDarkMysteriousForest extends Space
{
  private static final String name = ResourceLoader.getJsonField("assets/text/spaces/",
                                                                 "SuperBadDarkMysteriousForest.json", "name");
  private static final String description = ResourceLoader.getJsonField("assets/text/spaces/",
                                                                        "SuperBadDarkMysteriousForest.json", "description");
  private boolean isHidden;
  private Team enemies;

  public SuperBadDarkMysteriousForest()
  {
    isHidden = true;
    enemies = new Team();
    enemies.add(new Viper());
  }

  public Space getSouth() {return Global.getGlobal().getSpace("Starting camp");}

  public String getName() {return name;}
  public String getDescription() {return description;}
  public boolean isHidden() {return isHidden;}
  public void unhide() {isHidden = false;}
  public Team getEnemies() {return enemies;}
  public void deleteEnemies() {enemies = null;}
}
