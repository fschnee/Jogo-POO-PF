package proj.jogo.spaces;

import proj.jogo.common.Interactable;
import proj.jogo.mobs.Team;
import java.util.ArrayList;

public abstract class Space
{
  // Espaços adjacentes
  public Space getUp() {return null;}
  public Space getDown() {return null;}
  public Space getNorth() {return null;}
  public Space getSouth() {return null;}
  public Space getWest() {return null;}
  public Space getEast() {return null;}

  public abstract String getName();
  public abstract String getDescription();
  public abstract boolean isHidden();
  public abstract void unhide();
  public ArrayList<Interactable> getInteractables() {return null;}
  public Team getEnemies() {return null;}
  public void deleteEnemies() {}
}
