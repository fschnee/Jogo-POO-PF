package proj.jogo.spaces;

import proj.Global;
import proj.ResourceLoader;
import proj.jogo.spaces.Space;
import proj.jogo.common.Interactable;
import java.util.ArrayList;

public class StartingCamp extends Space
{
  private static final String name = ResourceLoader.getJsonField("assets/text/spaces/",
                                                                 "StartingCamp.json", "name");
  private static final String description = ResourceLoader.getJsonField("assets/text/spaces/",
                                                                        "StartingCamp.json", "description");
  private static final ArrayList<Interactable> interactables = new ArrayList<Interactable>();

  public Space getUp() {return null;}
  public Space getDown() {return Global.getGlobal().getSpace("Dev room");}
  public Space getNorth() {return null;}
  public Space getSouth() {return null;}
  public Space getWest() {return null;}
  public Space getEast() {return null;}

  public String getName() {return name;}
  public String getDescription() {return description;}
  public boolean isHidden() {return false;}
  public void unhide() {}
  public ArrayList<Interactable> getInteractables() {return interactables;}
}
