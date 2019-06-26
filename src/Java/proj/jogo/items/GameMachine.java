package proj.jogo.items;

import proj.ResourceLoader;
import proj.cardgames.Blackjack;
import proj.jogo.items.Item;
import proj.jogo.items.SelfUsable;
import proj.view.CardGameTerm;
import proj.jogo.common.CharacterActionResult;

public class GameMachine implements SelfUsable, Item
{
  private static final String name = ResourceLoader.getJsonField("assets/text/items/",
                                                                 "GameMachine.json",
                                                                 "name");
  private static final String description = ResourceLoader.getJsonField("assets/text/items/",
                                                                        "GameMachine.json",
                                                                        "description");

  public CharacterActionResult use(Character sender)
  {
    CardGameTerm cgt = new CardGameTerm();
    new Blackjack(cgt.getTextOut()).play(0);

    return new CharacterActionResult(0, null, null);
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
