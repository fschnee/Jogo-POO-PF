package proj.jogo.mobs;

import proj.Global;
import proj.ResourceLoader;
import proj.jogo.mobs.Character;
import proj.jogo.mobs.OgreSmash;
import proj.jogo.mobs.OgrePunch;

public class Ogre extends Character
{
  public static final String name = ResourceLoader.getJsonField("assets/text/mobs/", "Ogre.json", "name");
  public static final String desc = ResourceLoader.getJsonField("assets/text/mobs/", "Ogre.json", "description");

  public Ogre()
  {
    super();
    characteristics.stamina = 3;
    calculateMaxHealth();
    health = maxhealth;
    calculateMaxEnergy();
    energy = maxenergy;

    moves.add(new OgreSmash());
    moves.add(new OgrePunch());
  }

  public void calculateMaxHealth() {this.maxhealth = 15;}

  // Ja que ele é cego ele pega um alvo qualer
  @Override
  public void signalForAttack()
  {
    Attack atkToUse = moves.get(0);
    for(Attack a : moves) if(canUse(a) && (a.getDamage() > atkToUse.getDamage())) atkToUse = a;

    int teamsize = Global.getGlobal().getActiveBattle().getAllies().get().size();
    int targetnum;
    Character target;
    java.util.Random gerador = new java.util.Random();
    do {
      targetnum = Math.abs(gerador.nextInt()) % teamsize;
      target = Global.getGlobal().getActiveBattle().getAllies().get(targetnum);
    } while (target.getHealth() <= 0);
    Global.getGlobal().getActiveBattle().attack(target, atkToUse);

  }

  public String getName() {return name;}
  public String getDescription() {return desc;}
  public int getHealth() {return this.health;}
  public int getEnergy() {return this.energy;}
}
