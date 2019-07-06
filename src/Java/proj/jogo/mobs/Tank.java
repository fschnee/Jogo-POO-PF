package proj.jogo.mobs;

import proj.ResourceLoader;
import proj.jogo.mobs.Character;

public class Tank extends Character
{
  private static final String name = ResourceLoader.getJsonField("assets/text/mobs/", "Tank.json", "name");
  private static final String desc = ResourceLoader.getJsonField("assets/text/mobs/", "Tank.json", "description");

  public Tank()
  {
    super();
  }

  public String getName() {return name;}
  public String getDescription() {return desc;}
}
