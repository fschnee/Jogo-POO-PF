package proj.jogo.items;

import proj.jogo.common.Named;

public interface Item extends Named
{
  public float getWeight();
  public float getValue();
  public float getVolume();
  public Boolean isTradeable();
  public Boolean isUseable();
  public Boolean isSingleUse();
}
