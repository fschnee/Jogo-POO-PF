package proj.jogo.items;

import proj.Global;
import proj.ResourceLoader;
import proj.cardgames.Blackjack;
import proj.jogo.items.Item;
import proj.jogo.items.SpecialUsable;
import proj.jogo.mobs.Character;

public class GameMachine implements SpecialUsable, Item
{
  private static final String name = ResourceLoader.getJsonField("assets/text/items/",
                                                                 "GameMachine.json",
                                                                 "name");
  private static final String description = ResourceLoader.getJsonField("assets/text/items/",
                                                                        "GameMachine.json",
                                                                        "description");

  @Override
  public void use(Character sender)
  {
    Global.getGlobal().getGUI().setActivePane("Terminal");
    new Blackjack(Global.getGlobal().getGUI().getTextOut("Terminal")).play(0);
  }

  public String getName() {return name;}
  public String getDescription() {return description;}
  public float getWeight() {return 0;}
  public float getValue()  {return 99999;}
  public float getVolume() {return 0;}
  public Boolean isTradeable() {return false;}
  public Boolean isUseable() {return true;}
  public Boolean isSingleUse() {return false;}
}
