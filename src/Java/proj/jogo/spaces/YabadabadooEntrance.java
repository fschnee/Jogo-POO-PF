package proj.jogo.spaces;

import proj.Global;
import proj.ResourceLoader;
import proj.jogo.spaces.Space;
import proj.jogo.common.Interactable;
import proj.jogo.mobs.Team;
import proj.jogo.mobs.Ogre;
import proj.jogo.mobs.Dwarf;
import java.util.ArrayList;

public class YabadabadooEntrance extends Space
{
  private static final String name = ResourceLoader.getJsonField("assets/text/spaces/",
                                                                 "YabadabadooEntrance.json", "name");
  private static final String description = ResourceLoader.getJsonField("assets/text/spaces/",
                                                                        "YabadabadooEntrance.json", "description");
  private boolean isHidden;
  private Team enemies;

  public YabadabadooEntrance()
  {
    isHidden = true;
    enemies = new Team();
    enemies.add(new Ogre());
    enemies.add(new Dwarf());
  }

  public Space getEast() {return Global.getGlobal().getSpace("Super bad dark mysterious forest");}

  public String getName() {return name;}
  public String getDescription() {return description;}
  public boolean isHidden() {return isHidden;}
  public void unhide() {isHidden = false;}
  public Team getEnemies() {return enemies;}
  public void deleteEnemies() {enemies = null;}
}
