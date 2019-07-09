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
  private boolean isHidden;

  public Devroom()
  {
    interactables = new ArrayList<Interactable>();
    Chest c = new Chest();
    interactables.add(c);
    isHidden = true;
  }

  public Space getUp() {return Global.getGlobal().getSpace("Starting camp");}

  public String getName() {return name;}
  public String getDescription() {return description;}
  public boolean isHidden() {return isHidden;}
  public void unhide() {isHidden = false;}
  public ArrayList<Interactable> getInteractables() {return interactables;}
}
