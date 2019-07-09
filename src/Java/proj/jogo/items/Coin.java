package proj.jogo.items;

import proj.jogo.items.Item;
import proj.ResourceLoader;

public class Coin implements Item
{
  private static final String name = ResourceLoader.getJsonField("assets/text/items/",
                                                                 "Coin.json",
                                                                 "name");
  private static final String description = ResourceLoader.getJsonField("assets/text/items/",
                                                                        "Coin.json",
                                                                        "description");

  public String getName() {return sgetName();}
  public String getDescription() {return sgetDescription();}
  public float getWeight() {return sgetWeight();}
  public float getVolume() {return sgetVolume();}
  public float getValue() {return sgetValue();}
  public Boolean isTradeable() {return sisTradeable();}
  public Boolean isUseable() {return sisUseable();}
  public Boolean isSingleUse() {return sisSingleUse();}

  public static String sgetName() {return name;}
  public static String sgetDescription() {return description;}
  public static float sgetWeight() {return 0.1f;}
  public static float sgetVolume() {return 1;}
  public static float sgetValue() {return 1;}
  public static Boolean sisTradeable() {return true;}
  public static Boolean sisUseable() {return false;}
  public static Boolean sisSingleUse() {return false;}
}
