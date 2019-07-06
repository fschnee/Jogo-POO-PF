package proj.jogo.spaces;

import proj.jogo.common.Interactable;
import java.util.ArrayList;

public abstract class Space
{
  // Espaços adjacentes
  public abstract Space getUp();
  public abstract Space getDown();
  public abstract Space getNorth();
  public abstract Space getSouth();
  public abstract Space getWest();
  public abstract Space getEast();

  public abstract String getName();
  public abstract String getDescription();
  public abstract boolean isHidden();
  public abstract void unhide();
  public abstract ArrayList<Interactable> getInteractables();
}
