package proj.jogo.mobs;

import proj.ResourceLoader;
import proj.jogo.mobs.Attack;
import proj.jogo.common.StatusEffect;
import proj.jogo.common.CharacterAttributeSet;
import java.util.ArrayList;

public class OgreSmash implements Attack
{
  public static final String name = ResourceLoader.getJsonField("assets/text/mobs/attacks/", "Ogre_Smash.json", "name");
  public static final String desc = ResourceLoader.getJsonField("assets/text/mobs/attacks/", "Ogre_Smash.json", "description");

  public String getName() {return name;}
  public String getDescription() {return desc;}
  public int getDamage() {return 5;}
  public int getEnergyConsumption() {return 6;}
  public CharacterAttributeSet getStatChange() {return null;}
  public ArrayList<StatusEffect> getEffects() {return null;}
}
