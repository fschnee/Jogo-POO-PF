package proj.jogo.mobs;

import proj.jogo.mobs.Character;
import java.util.ArrayList;

public class Team
{
  private ArrayList<Character> integrants;

  public Team()
  {
    integrants = new ArrayList<Character>();
  }

  public void add(Character newintegrant) {integrants.add(newintegrant);}
}
