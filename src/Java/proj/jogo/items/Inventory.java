package proj.jogo.items;

import proj.jogo.items.Item;
import proj.jogo.items.Coin;
import java.util.ArrayList;

public class Inventory
{
  private float maxweight;
  private float currweight;
  private float maxvolume;
  private float currvolume;
  private ArrayList<Item> storeditems;
  private int coincount;

  public Inventory(float w, float v)
  {
    this.maxweight = w;
    this.currweight = 0;
    this.maxvolume = v;
    this.currvolume = 0;
    this.storeditems = new ArrayList<Item>();
    this.coincount = 0;
  }

  public Boolean insert(Item i)
  {
    if(this.canStore(i))
    {
      if(i instanceof Coin)
      {
        coincount++;
      }
      this.storeditems.add(i);
      this.currweight += i.getWeight();
      this.currvolume += i.getVolume();
    }
    else return false;

    return true;
  }

  public Boolean canStore(Item i)
  {
    if (this.currweight + i.getWeight() > this.maxweight) return false;
    else if (this.currvolume + i.getVolume() > this.maxvolume) return false;
    else return true;
  }

  public float getCoins() {return coincount * Coin.sgetValue();}

  public float getWorth()
  {
    float ret = 0;

    for(Item i : this.storeditems)
    {
      if(i.isTradeable()) ret += i.getValue();
    }

    return ret;
  }
}
