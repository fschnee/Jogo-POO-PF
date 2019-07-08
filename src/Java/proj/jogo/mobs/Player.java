package proj.jogo.mobs;

import proj.Global;
import proj.jogo.common.Named;
import proj.jogo.mobs.Team;
import proj.jogo.mobs.Healer;
import proj.jogo.mobs.Tank;
import proj.jogo.mobs.DPS;
import proj.jogo.items.Inventory;
import proj.jogo.spaces.Space;

public class Player implements Named
{
  private String name;
  private float money;
  private Inventory backpacks;
  private Space currmap;
  private Team party;

  public Player(String name)
  {
    this.name = name;
    this.money = 0;
    this.backpacks = new Inventory(100000, 100000);
    this.currmap = Global.getGlobal().getSpace("Starting camp");
    this.party = new Team();
    party.add(new Healer());
    party.add(new Tank());
    party.add(new DPS());
  }

  public String getName() {return this.name;}
  public String getDescription() {return "The game's player";}
  public synchronized Team getParty() {return party;}
  public synchronized Space getCurrentSpace() {return this.currmap;}
  public synchronized void setCurrentSpace(Space s) {this.currmap = s;}
  public synchronized float getMoney() {return backpacks.getCoins();}
  public synchronized float getWorth() {return backpacks.getWorth();}
  public synchronized Inventory getBackpack() {return backpacks;}
}
