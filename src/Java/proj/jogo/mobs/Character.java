package proj.jogo.mobs;

import proj.jogo.mobs.CharacterAttributeSet;

public class Character
{
  private String name;
  private CharacterAttributeSet characteristics;
  private int health;
  private int energy;

  public Character() {}

  public String getName() {return this.name;}
  public int getHealth() {return this.health;}
  public int getEnergy() {return this.energy;}
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
