package proj.jogo.mobs;

import proj.jogo.common.Named;
import proj.jogo.common.StatusEffect;
import proj.jogo.common.CharacterAttributeSet;
import java.util.ArrayList;

public interface Attack extends Named
{
  public int getDamage();
  public int getEnergyConsumption();
  public CharacterAttributeSet getStatChange(); // buffs || debuffs
  public ArrayList<StatusEffect> getEffects();
}
