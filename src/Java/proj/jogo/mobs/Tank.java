package proj.jogo.mobs;

import proj.ResourceLoader;
import proj.jogo.mobs.Character;
import proj.jogo.mobs.GenericPunch;

public class Tank extends Character
{
  private static final String name = ResourceLoader.getJsonField("assets/text/mobs/", "Tank.json", "name");
  private static final String desc = ResourceLoader.getJsonField("assets/text/mobs/", "Tank.json", "description");

  public Tank()
  {
    super();
    characteristics.vitality = 3;
    characteristics.stamina = 2;
    calculateMaxHealth();
    health = maxhealth;
    calculateMaxEnergy();
    energy = maxenergy;

    moves.add(new GenericPunch());
  }

  public String getName() {return name;}
  public String getDescription() {return desc;}
}
