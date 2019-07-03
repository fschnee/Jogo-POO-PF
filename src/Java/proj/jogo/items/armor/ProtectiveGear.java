package proj.jogo.items.armor;

import proj.jogo.common.CharacterAttributeSet;

public interface ProtectiveGear
{
  public Boolean isHeadwear();
  public Boolean isChestwear();
  public Boolean isHandwear();
  public Boolean isLegwear();
  public Boolean isFootwear();

  public CharacterAttributeSet getStatChange();
  public float getProtection();
}
