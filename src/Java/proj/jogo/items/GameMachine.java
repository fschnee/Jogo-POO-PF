package proj.jogo.items;

import proj.ResourceLoader;
import proj.cardgames.Blackjack;
import proj.jogo.items.Item;
import proj.jogo.items.SelfUsable;
import proj.view.CardgamePanel;
import proj.jogo.common.CharacterActionResult;
import proj.view.GameGUI;

public class GameMachine implements SpecialUsable, Item
{
  private static final String name = ResourceLoader.getJsonField("assets/text/items/",
                                                                 "GameMachine.json",
                                                                 "name");
  private static final String description = ResourceLoader.getJsonField("assets/text/items/",
                                                                        "GameMachine.json",
                                                                        "description");

  @Override
  public void use(Object sender)
  {
    ((GameGUI)sender).setActivePane("Terminal");
    new Blackjack(((GameGUI)sender).getTextout("Terminal")).play(0);
  }

  public String getName() {return name;}
  public float getWeight() {return 0;}
  public float getValue()  {return 99999;}
  public float getVolume() {return 0;}
  public Boolean isTradeable() {return false;}
  public Boolean isUseable() {return true;}
  public Boolean isSingleUse() {return false;}
  public String getDescription() {return description;}
}
