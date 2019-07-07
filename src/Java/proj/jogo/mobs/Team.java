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
  public int size() {return integrants.size();}
  public Character get(int i) {return integrants.get(i);}
  public ArrayList<Character> get() {return integrants;}
}
