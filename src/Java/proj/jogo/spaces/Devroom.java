package proj.jogo.spaces;

import proj.Global;
import proj.ResourceLoader;
import proj.jogo.spaces.Space;
import proj.jogo.other.Chest;
import proj.jogo.common.Interactable;
import java.util.ArrayList;

public class Devroom extends Space
{
  private static final String name = ResourceLoader.getJsonField("assets/text/spaces/",
                                                                 "Devroom.json", "name");
  private static final String description = ResourceLoader.getJsonField("assets/text/spaces/",
                                                                        "Devroom.json", "description");
  private ArrayList<Interactable> interactables;

  public Devroom()
  {
    interactables = new ArrayList<Interactable>();
    Chest c = new Chest();
    interactables.add(c);
  }

  public Space getUp() {return null;}
  public Space getDown() {return null;}
  public Space getNorth() {return null;}
  public Space getSouth() {return Global.getGlobal().getSpace("StartingCamp");}
  public Space getWest() {return null;}
  public Space getEast() {return null;}

  public String getName() {return name;}
  public String getDescription() {return description;}
  public Boolean isHidden() {return true;}
  public ArrayList<Interactable> getInteractables() {return interactables;}
}
