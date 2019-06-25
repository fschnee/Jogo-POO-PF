package proj.jogo.mobs;

import proj.jogo.mobs.CharacterAttributeSet;

public class Character
{
  private CharacterAttributeSet characteristics;
  private String name;

  public Character() {}
}

class CharacterAttributeSet
{
  public int Strength;
  public int Stamina;
  public int Resolve;
  public int Charisma;
  public int Luck;
  public int Accuracy;
}
