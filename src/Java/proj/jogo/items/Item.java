package proj.jogo.items;

public interface Item
{
  public abstract String getName();
  public abstract float getWeight();
  public abstract float getValue();
  public abstract float getVolume();
  public abstract Boolean isTradeable();
  public abstract Boolean isUseable();
  public abstract String getDescription();
}
