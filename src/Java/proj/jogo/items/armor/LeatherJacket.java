package proj.jogo.items.armor;

import proj.ResourceLoader;
import proj.jogo.items.armor.ProtectiveGear;
import proj.jogo.common.CharacterAttributeSet;
import proj.jogo.common.ImmutableCharacterAttributeSet;

public class LeatherJacket
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

  public String getName() {return name;}
  public String getDescription() {return description;}

  public Boolean isFacewear() {return false;}
  public Boolean isChestwear() {return true;}
  public Boolean isHandwear() {return false;}
  public Boolean isLegwear() {return false;}
  public Boolean isShoewear() {return false;}

  public CharacterAttributeSet getStatChange() {return statchange;}
  public float getProtection() {return protection;}
}
