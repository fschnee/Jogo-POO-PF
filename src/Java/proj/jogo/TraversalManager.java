package proj.jogo;

import proj.Global;
import proj.ResourceLoader;
import proj.jogo.spaces.Space;
import proj.view.TraversalPanel;
import java.util.ArrayList;

public class TraversalManager
{
  private int downrequests;
  public TraversalManager()
  {
    downrequests = 0;
  }

  public synchronized void goNorth()
  {
    Space playerspace = Global.getGlobal().getPlayer().getCurrentSpace();
    traverse(playerspace, playerspace.getNorth());
  }
  public synchronized void goSouth()
  {
    Space playerspace = Global.getGlobal().getPlayer().getCurrentSpace();
    traverse(playerspace, playerspace.getSouth());
  }
  public synchronized void goEast()
  {
    Space playerspace = Global.getGlobal().getPlayer().getCurrentSpace();
    traverse(playerspace, playerspace.getEast());
  }
  public synchronized void goWest()
  {
    Space playerspace = Global.getGlobal().getPlayer().getCurrentSpace();
    traverse(playerspace, playerspace.getWest());
  }
  public synchronized void goUp()
  {
    Space playerspace = Global.getGlobal().getPlayer().getCurrentSpace();
    traverse(playerspace, playerspace.getUp());
  }
  public synchronized void goDown()
  {
    Space playerspace = Global.getGlobal().getPlayer().getCurrentSpace();
    Space request = playerspace.getDown();

    if(request != null && request.getName().equals(ResourceLoader
                                                   .getJsonField("assets/text/spaces/",
                                                                 "Devroom.json", "name")))
    {
      downrequests += 1;
      if(downrequests <= 3)
      {
        if(downrequests == 1) Global.getGlobal().getGUI().getTextOut("Traversal")
                              .appendText("You want to go down?", "default");
        else if(downrequests == 2) Global.getGlobal().getGUI().getTextOut("Traversal")
                                   .appendText("Are you sure about that?", "default");
        else Global.getGlobal().getGUI().getTextOut("Traversal")
             .appendText("I think there's nothing of interest there, forget it", "default");
        return;
      }
    }
    traverse(playerspace, request);
  }

  private synchronized void traverse(Space playerspace, Space request)
  {
    if(request != null)
    {
      Global.getGlobal().getPlayer().setCurrentSpace(request);
      ((TraversalPanel)Global.getGlobal().getGUI().getPanel("Traversal")).switchLocationTo(request);
      if(request.isHidden())
      {
        Global.getGlobal().getGUI().getTextOut("Traversal")
        .appendText("You have discovered a place called " + request.getName(), "default-bold");
        request.unhide();
      }
      else Global.getGlobal().getGUI().getTextOut("Traversal")
      .appendText("You have been here before", "default");
    }
    else printNothingThatWay();
  }

  private void printNothingThatWay()
  {
    Global.getGlobal().getGUI().getTextOut("Traversal")
    .appendText("There is nothing of interest that way", "default");
  }

  // stub
  public void mapRequest() {}
}
