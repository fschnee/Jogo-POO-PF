package proj.jogo.items;

import proj.Global;
import proj.ResourceLoader;
import proj.jogo.items.Item;
import proj.jogo.items.Usable;
import proj.jogo.mobs.Character;
import proj.jogo.common.CharacterActionResult;

public class Potion implements Usable, Item
{
  private static final String name = ResourceLoader.getJsonField("assets/text/items/",
                                                                 "Potion.json",
                                                                 "name");
  private static final String description = ResourceLoader.getJsonField("assets/text/items/",
                                                                        "Potion.json",
                                                                        "description");

  @Override
  public void use(Character sender)
  {
    sender.applyAction(new CharacterActionResult(5, null, null));
  }

  public String getName() {return name;}
  public String getDescription() {return description;}
  public float getWeight() {return 1;}
  public float getValue()  {return 5;}
  public float getVolume() {return 2;}
  public Boolean isTradeable() {return true;}
  public Boolean isUseable() {return true;}
  public Boolean isSingleUse() {return true;}
}
