package proj.jogo.items;

import proj.jogo.items.Item;
import proj.ResourceLoader;

public class Coin implements Item
{
  private static final String description = ResourceLoader.getItemDescription("Coin.json");

  public String getName() {return "Coin";}
  public float getWeight() {return 0.1f;}
  public float getVolume() {return 1;}
  public float getValue() {return 1;}
  public Boolean isTradeable() {return true;}
  public Boolean isUseable() {return false;}
  public String getDescription() {return description;}

  // TODO: Tem que haver um jeito melhor de fazer isso...
  public static String sgetName() {return "Coin";}
  public static float sgetWeight() {return 0.1f;}
  public static float sgetVolume() {return 1;}
  public static float sgetValue() {return 1;}
  public static Boolean sisTradeable() {return true;}
  public static Boolean sisUseable() {return false;}
  public static String sgetDescription() {return description;}
}
