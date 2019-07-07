package proj.jogo.mobs;

import proj.Global;
import proj.jogo.common.Named;
import proj.jogo.common.MutableCharacterAttributeSet;
import proj.jogo.items.armor.ArmorSet;
import proj.jogo.mobs.NoAttack;
import java.util.ArrayList;

public class Character implements Named
{
  protected MutableCharacterAttributeSet characteristics;  // Base stats;
  protected MutableCharacterAttributeSet stats;            // Stats after item use;
  protected ArmorSet armor;
  protected int health;
  protected int maxhealth;
  protected int energy;
  protected int maxenergy;
  protected ArrayList<Attack> moves;

  public Character()
  {
    characteristics = new MutableCharacterAttributeSet();
    stats = new MutableCharacterAttributeSet();
    health = 0;
    maxhealth = 0;
    energy = 0;
    maxenergy = 0;
    armor = new ArmorSet();
    moves = new ArrayList<Attack>();
    moves.add(new NoAttack());
  }

  public void calculateMaxHealth()
  {
    this.maxhealth = (characteristics.vitality + stats.vitality) * 7;
  }

  public void calculateMaxEnergy()
  {
    this.maxenergy = (characteristics.stamina + stats.stamina) * 2;
  }

  public void recieveAttack(Attack atk)
  {
    if(armor.getProtection() == 0) health -= atk.getDamage();
    else health -= Math.round(atk.getDamage() * (float)armor.getProtection()/100);
  }

  // Placeholder, logica de ataque vai aqui
  public void signalForAttack()
  {
    Global.getGlobal().getActiveBattle().attack(Global.getGlobal().getActiveBattle().getAllies().get(0), moves.get(0));
  }
  public ArrayList<Attack> getMoves() {return moves;}
  public String getName() {return "Generic Character";}
  public String getDescription() {return "A generic character";}
  public int getHealth() {return this.health;}
  public int getMaxHealth() {return this.maxhealth;}
  public int getEnergy() {return this.energy;}
  public int getMaxEnergy() {return this.maxenergy;}
}
