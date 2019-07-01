package proj.jogo.mobs;

import proj.jogo.common.MutableCharacterAttributeSet;
import proj.jogo.items.armor.ArmorSet;

public class Character
{
  private String name;
  private MutableCharacterAttributeSet characteristics;  // Base stats;
  private MutableCharacterAttributeSet stats;            // Stats after item use;
  private ArmorSet armor;
  private int health;
  private int maxhealth;
  private int energy;
  private int maxenergy;

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

  public void calculateMaxHealth()
  {
    this.maxhealth = stats.vitality * 7;
  }

  public void calculateMaxEnergy()
  {
    this.maxhealth = stats.stamina * 2;
  }

  public String getName() {return this.name;}
  public int getHealth() {return this.health;}
  public int getEnergy() {return this.energy;}
}
