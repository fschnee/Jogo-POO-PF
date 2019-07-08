package proj.jogo.mobs;

import proj.ResourceLoader;
import proj.jogo.mobs.Character;
import proj.jogo.mobs.ViperBite;

public class Viper extends Character
{
  public static final String name = ResourceLoader.getJsonField("assets/text/mobs/", "Viper.json", "name");
  public static final String desc = ResourceLoader.getJsonField("assets/text/mobs/", "Viper.json", "description");

  public Viper()
  {
    super();
    characteristics.stamina = 2;
    calculateMaxHealth();
    health = maxhealth;
    calculateMaxEnergy();
    energy = maxenergy;

    moves.add(new ViperBite());
  }

  public void calculateMaxHealth() {this.maxhealth = 7;}

  public String getName() {return name;}
  public String getDescription() {return desc;}
  public int getHealth() {return this.health;}
  public int getEnergy() {return this.energy;}
}
