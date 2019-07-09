package proj.jogo.common;

import proj.jogo.common.CharacterAttributeSet;
import proj.jogo.common.StatusEffect;
import java.util.ArrayList;

public class CharacterActionResult
{
  private int healthchange;
  private CharacterAttributeSet attributechange;
  private ArrayList<StatusEffect> statuseffects;

  public CharacterActionResult(int hc, CharacterAttributeSet casc, ArrayList<StatusEffect> sec)
  {
    healthchange = hc;
    attributechange = casc;
    statuseffects = sec;
  }

  public int getHealthChange() {return healthchange;}
  public CharacterAttributeSet getAttributeChange() {return attributechange;}
  public ArrayList<StatusEffect> getStatusEffects() {return statuseffects;}
}
