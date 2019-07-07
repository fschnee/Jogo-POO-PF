package proj.jogo.mobs;

import proj.ResourceLoader;
import proj.jogo.mobs.Attack;
import proj.jogo.common.StatusEffect;
import proj.jogo.common.CharacterAttributeSet;
import java.util.ArrayList;

public class NoAttack implements Attack
{
  public static final String name = "Nothing";
  public static final String desc = "Does not attack";

  public String getName() {return name;}
  public String getDescription() {return desc;}
  public int getDamage() {return 0;}
  public int getEnergyConsumption() {return 0;}
  public CharacterAttributeSet getStatChange() {return null;}
  public ArrayList<StatusEffect> getEffects() {return null;}
}
