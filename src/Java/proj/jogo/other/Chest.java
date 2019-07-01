package proj.jogo.other;

import proj.jogo.common.Interactable;
import proj.jogo.items.Item;
import proj.jogo.mobs.Character;
import java.util.ArrayList;

public class Chest implements Interactable
{
  private ArrayList<Item> contents;

  public Chest()
  {
    contents = new ArrayList<Item>();
  }

  public void interactUsing(Character interactor) {}

  public void add(Item i)
  {
    contents.add(i);
  }

  public Item remove(Item i)
  {
    contents.remove(i);
    return i;
  }

  public Item switchWith(Item i1, Item i2)
  {
    contents.add(i1);
    contents.remove(i2);
    return i2;
  }
}
