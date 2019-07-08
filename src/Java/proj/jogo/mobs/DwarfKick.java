package proj.jogo.mobs;

import proj.ResourceLoader;
import proj.jogo.mobs.Attack;
import proj.jogo.common.StatusEffect;
import proj.jogo.common.CharacterAttributeSet;
import java.util.ArrayList;

public class DwarfKick implements Attack
{
  public static final String name = ResourceLoader.getJsonField("assets/text/mobs/attacks/", "Dwarf_Kick.json", "name");
  public static final String desc = ResourceLoader.getJsonField("assets/text/mobs/attacks/", "Dwarf_Kick.json", "description");

  public String getName() {return name;}
  public String getDescription() {return desc;}
  public int getDamage() {return 1;}
  public int getEnergyConsumption() {return 0;}
  public CharacterAttributeSet getStatChange() {return null;}
  public ArrayList<StatusEffect> getEffects() {return null;}
}
