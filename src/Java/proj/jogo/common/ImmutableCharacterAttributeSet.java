package proj.jogo.common;

import proj.jogo.common.CharacterAttributeSet;

public class ImmutableCharacterAttributeSet implements CharacterAttributeSet
{
  private int strength;
  private int vitality;
  private int stamina;
  private int charisma;
  private int luck;
  private int accuracy;

  public int getStrength() {return strength;}
  public int getVitality() {return vitality;}
  public int getStamina() {return stamina;}
  public int getCharisma() {return charisma;}
  public int getLuck() {return luck;}
  public int getAccuracy() {return accuracy;}

  public ImmutableCharacterAttributeSet(int str, int vit, int sta, int ch, int lu, int ac)
  {
    strength = str;
    vitality = vit;
    stamina = sta;
    charisma = ch;
    luck = lu;
    accuracy = ac;
  }
}
