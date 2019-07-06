package proj.jogo.mobs;

import proj.ResourceLoader;
import proj.jogo.mobs.Character;

public class Healer extends Character
{
  private static final String name = ResourceLoader.getJsonField("assets/text/mobs/", "Healer.json", "name");
  private static final String desc = ResourceLoader.getJsonField("assets/text/mobs/", "Healer.json", "description");

  public Healer()
  {
    super();
  }

  public String getName() {return name;}
  public String getDescription() {return desc;}
}
