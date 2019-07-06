package proj.jogo.mobs;

import proj.jogo.common.Named;
import proj.jogo.common.MutableCharacterAttributeSet;
import proj.jogo.items.armor.ArmorSet;

public class Character implements Named
{
  protected MutableCharacterAttributeSet characteristics;  // Base stats;
  protected MutableCharacterAttributeSet stats;            // Stats after item use;
  protected ArmorSet armor;
  protected int health;
  protected int maxhealth;
  protected int energy;
  protected int maxenergy;

  public Character()
  {
    characteristics = new MutableCharacterAttributeSet();
    stats = new MutableCharacterAttributeSet();
    health = 0;
    maxhealth = 0;
    energy = 0;
    maxenergy = 0;
    armor = new ArmorSet();
  }

  public String getName() {return "Generic Character";}
  public String getDescription() {return "A generic character";}
  public int getHealth() {return this.health;}
  public int getEnergy() {return this.energy;}
}
