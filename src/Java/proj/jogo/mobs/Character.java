package proj.jogo.mobs;

import proj.Global;
import proj.jogo.common.Named;
import proj.jogo.common.MutableCharacterAttributeSet;
import proj.jogo.items.armor.ArmorSet;
import proj.jogo.mobs.NoAttack;
import proj.jogo.common.CharacterActionResult;
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

  public void applyAction(CharacterActionResult action)
  {
    if(health + action.getHealthChange() > maxhealth) health = maxhealth;
    else health += action.getHealthChange();
  }
  // Pega o primeiro inimigo que achar e ataca com o que da mais dano que consegue usar
  public void signalForAttack()
  {
    for(Character target : Global.getGlobal().getActiveBattle().getAllies().get())
      if(target.getHealth() > 0)
      {
        Attack atkToUse = moves.get(0);
        for(Attack a : moves) if(canUse(a) && (a.getDamage() > atkToUse.getDamage())) atkToUse = a;
        Global.getGlobal().getActiveBattle().attack(target, atkToUse);
        return;
      }
  }

  public boolean canUse(Attack a) {return (energy - a.getEnergyConsumption()) >= 0;}
  public void restoreEnergy() {if(++energy > maxenergy) energy = maxenergy;}
  public void removeEnergy(int e) {energy -= e;}
  public ArrayList<Attack> getMoves() {return moves;}
  public String getName() {return "Generic Character";}
  public String getDescription() {return "A generic character";}
  public int getHealth() {return this.health;}
  public int getMaxHealth() {return this.maxhealth;}
  public int getEnergy() {return this.energy;}
  public int getMaxEnergy() {return this.maxenergy;}
}
