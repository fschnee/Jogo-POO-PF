package proj.jogo.common;

import proj.jogo.common.CharacterAttributeSet;

public class MutableCharacterAttributeSet implements CharacterAttributeSet
{
  public int strength;
  public int vitality;
  public int stamina;
  public int charisma;
  public int luck;
  public int accuracy;

  public int getStrength() {return strength;}
  public int getVitality() {return vitality;}
  public int getStamina() {return stamina;}
  public int getCharisma() {return charisma;}
  public int getLuck() {return luck;}
  public int getAccuracy() {return accuracy;}

  public MutableCharacterAttributeSet()
  {
    strength = 0;
    vitality = 0;
    stamina = 0;
    charisma = 0;
    luck = 0;
    accuracy = 0;
  }
}
