package proj.jogo.items.armor;

import proj.ResourceLoader;
import proj.jogo.items.Item;
import proj.jogo.items.armor.ProtectiveGear;
import proj.jogo.common.CharacterAttributeSet;
import proj.jogo.common.ImmutableCharacterAttributeSet;

public class LeatherJacket implements ProtectiveGear, Item
{
  private static final String name = ResourceLoader.getJsonField("assets/text/items/armor/",
                                                                 "LeatherJacket.json",
                                                                 "name");
  private static final String description = ResourceLoader.getJsonField("assets/text/items/armor/",
                                                                        "LeatherJacket.json",
                                                                        "description");
  private ImmutableCharacterAttributeSet statchange;
  private float protection;

  public LeatherJacket()
  {
    int str = 0;
    int vit = 0;
    int sta = 0;
    int ch = 0;
    int lu = 0;
    int ac = 0;
    protection = 10;

    statchange = new ImmutableCharacterAttributeSet(str, vit, sta, ch, lu, ac);
  }

  public Boolean isHeadwear() {return false;}
  public Boolean isChestwear() {return true;}
  public Boolean isHandwear() {return false;}
  public Boolean isLegwear() {return false;}
  public Boolean isFootwear() {return false;}

  public CharacterAttributeSet getStatChange() {return statchange;}
  public float getProtection() {return protection;}

  public String getName() {return name;}
  public String getDescription() {return description;}
  public float getWeight() {return 12;}
  public float getValue()  {return 3 * protection;}
  public float getVolume() {return 30;}
  public Boolean isTradeable() {return true;}
  public Boolean isUseable() {return false;}
  public Boolean isSingleUse() {return false;}
}
