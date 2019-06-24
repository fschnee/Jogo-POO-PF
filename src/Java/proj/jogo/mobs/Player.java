package proj.jogo.mobs;

import proj.jogo.mobs.Team;
import proj.jogo.items.Inventory;

public class Player
{
  private String name;
  private float money;
  private Inventory stash;
  private Inventory backpacks;

  public Player(String name)
  {
    this.name = name;
    this.money = 0;
    this.stash = new Inventory(2500, 6000);
    this.backpacks = new Inventory(500, 2500);
  }

  public String getName() {return this.name;}

  public float getMoney()
  {
    return backpacks.getCoins();
  }

  public float getWorth()
  {
    return backpacks.getWorth();
  }
}
