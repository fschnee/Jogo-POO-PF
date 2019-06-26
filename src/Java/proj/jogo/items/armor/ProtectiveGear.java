package proj.jogo.items.armor;

import proj.jogo.common.CharacterAttributeSet;

public interface ProtectiveGear
{
  public String getName();
  public String getDescription();

  public Boolean isFacewear();
  public Boolean isChestwear();
  public Boolean isHandwear();
  public Boolean isLegwear();
  public Boolean isShoewear();

  public CharacterAttributeSet getStatChange();
  public float getProtection();
}
