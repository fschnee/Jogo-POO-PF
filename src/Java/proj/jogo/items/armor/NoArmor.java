package proj.jogo.items.armor;

import proj.ResourceLoader;
import proj.jogo.items.Item;
import proj.jogo.items.armor.ProtectiveGear;
import proj.jogo.common.CharacterAttributeSet;
import proj.jogo.common.ImmutableCharacterAttributeSet;

public class NoArmor implements ProtectiveGear, Item
{
  private static final String name = ResourceLoader.getJsonField("assets/text/items/armor/",
                                                                 "NoArmor.json",
                                                                 "name");
  private static final String description = ResourceLoader.getJsonField("assets/text/items/armor/",
                                                                        "NoArmor.json",
                                                                        "description");
  private ImmutableCharacterAttributeSet statchange = new ImmutableCharacterAttributeSet(0, 0, 0,
                                                                                         0, 0, 0);
  private static final float protection = 0;

  public Boolean isHeadwear() {return true;}
  public Boolean isChestwear() {return true;}
  public Boolean isHandwear() {return true;}
  public Boolean isLegwear() {return true;}
  public Boolean isFootwear() {return true;}

  public CharacterAttributeSet getStatChange() {return statchange;}
  public float getProtection() {return protection;}

  public String getName() {return name;}
  public String getDescription() {return description;}
  public float getWeight() {return 0;}
  public float getValue() {return 0;}
  public float getVolume() {return 0;}
  public Boolean isTradeable() {return false;}
  public Boolean isUseable() {return false;}
  public Boolean isSingleUse() {return false;}
}
