package proj.jogo.items;

import proj.ResourceLoader;
import proj.cardgames.Blackjack;
import proj.jogo.items.Item;
import proj.jogo.items.SelfUsable;
import proj.view.CardGameTerm;
import proj.jogo.common.CharacterActionResult;

public class GameMachine implements SelfUsable, Item
{
  private static final String description = ResourceLoader.getItemDescription("GameMachine.json");

  public CharacterActionResult use(Character sender)
  {
    CardGameTerm cgt = new CardGameTerm();
    new Blackjack(cgt.getTextOut()).play(0);

    return new CharacterActionResult(0, null, null);
  }

  public String getName() {return "Game Machine #2597";}
  public float getWeight() {return 0;}
  public float getValue()  {return 99999;}
  public float getVolume() {return 0;}
  public Boolean isTradeable() {return false;}
  public Boolean isUseable() {return true;}
  public Boolean isSingleUse() {return false;}
  public String getDescription() {return description;}
}
