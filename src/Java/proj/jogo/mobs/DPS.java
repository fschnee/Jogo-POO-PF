package proj.jogo.mobs;

import proj.ResourceLoader;
import proj.jogo.mobs.Character;
import proj.jogo.mobs.GenericPunch;
import proj.jogo.mobs.PowerfullPunch;

public class DPS extends Character
{
  private static final String name = ResourceLoader.getJsonField("assets/text/mobs/", "DPS.json", "name");
  private static final String desc = ResourceLoader.getJsonField("assets/text/mobs/", "DPS.json", "description");

  public DPS()
  {
    super();
    characteristics.vitality = 1;
    characteristics.stamina = 4;
    calculateMaxHealth();
    health = maxhealth;
    calculateMaxEnergy();
    energy = maxenergy;

    moves.add(new GenericPunch());
    moves.add(new PowerfullPunch());
  }

  public void calculateMaxHealth()
  {
    this.maxhealth = (characteristics.vitality + stats.vitality) * 10;
  }

  public String getName() {return name;}
  public String getDescription() {return desc;}
}
