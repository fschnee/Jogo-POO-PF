package proj.jogo.mobs;

import proj.ResourceLoader;
import proj.jogo.mobs.Character;
import proj.jogo.mobs.DwarfKick;
import proj.jogo.mobs.DwarfPunch;

public class Dwarf extends Character
{
  public static final String name = ResourceLoader.getJsonField("assets/text/mobs/", "Dwarf.json", "name");
  public static final String desc = ResourceLoader.getJsonField("assets/text/mobs/", "Dwarf.json", "description");

  public Dwarf()
  {
    super();
    characteristics.stamina = 3;
    calculateMaxHealth();
    health = maxhealth;
    calculateMaxEnergy();
    energy = maxenergy;

    moves.add(new DwarfKick());
    moves.add(new DwarfPunch());
  }

  public void calculateMaxHealth() {this.maxhealth = 8;}

  public String getName() {return name;}
  public String getDescription() {return desc;}
  public int getHealth() {return this.health;}
  public int getEnergy() {return this.energy;}
}
