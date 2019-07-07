package proj.jogo.items.armor;

import proj.jogo.items.armor.NoArmor;
import proj.jogo.items.armor.ProtectiveGear;
import proj.jogo.common.MutableCharacterAttributeSet;
import proj.jogo.common.ImmutableCharacterAttributeSet;
import proj.jogo.common.CharacterAttributeSet;

public class ArmorSet
{
  private ProtectiveGear headwear;
  private ProtectiveGear chestwear;
  private ProtectiveGear handwear;
  private ProtectiveGear legwear;
  private ProtectiveGear shoes;

  private MutableCharacterAttributeSet overallstatchange;

  public ArmorSet()
  {
    overallstatchange = new MutableCharacterAttributeSet();
    headwear = new NoArmor();
    chestwear = new NoArmor();
    handwear = new NoArmor();
    legwear = new NoArmor();
    shoes = new NoArmor();

    calculateStatChange();
  }

  public ProtectiveGear getHeadwear() {return headwear;}
  public ProtectiveGear getChestwear() {return chestwear;}
  public ProtectiveGear getHandwear() {return handwear;}
  public ProtectiveGear getLegwear() {return legwear;}
  public ProtectiveGear getShoes() {return shoes;}

  private void calculateStatChange()
  {
    CharacterAttributeSet hw = headwear.getStatChange();
    CharacterAttributeSet cw = chestwear.getStatChange();
    CharacterAttributeSet haw = handwear.getStatChange();
    CharacterAttributeSet lw = legwear.getStatChange();
    CharacterAttributeSet s = shoes.getStatChange();

    overallstatchange.strength = hw.getStrength() + cw.getStrength() + haw.getStrength()
                                 + lw.getStrength() + s.getStrength();
    overallstatchange.vitality = hw.getVitality() + cw.getVitality() + haw.getVitality()
                                 + lw.getVitality() + s.getVitality();
    overallstatchange.stamina = hw.getStamina() + cw.getStamina() + haw.getStamina()
                                 + lw.getStamina() + s.getStamina();
    overallstatchange.charisma = hw.getCharisma() + cw.getCharisma() + haw.getCharisma()
                                 + lw.getCharisma() + s.getCharisma();
    overallstatchange.luck = hw.getLuck() + cw.getLuck() + haw.getLuck()
                                 + lw.getLuck() + s.getLuck();
    overallstatchange.accuracy = hw.getAccuracy() + cw.getAccuracy() + haw.getAccuracy()
                                 + lw.getAccuracy() + s.getAccuracy();
  }

  // retorna o que foi tirado
  private ProtectiveGear setHeadwear(ProtectiveGear newgear)
  {
    if(!newgear.isHeadwear()) return newgear;

    ProtectiveGear h = headwear;
    headwear = newgear;

    return h;
  }

  // retorna o que foi tirado
  private ProtectiveGear setChestwear(ProtectiveGear newgear)
  {
    if(!newgear.isChestwear()) return newgear;

    ProtectiveGear c = chestwear;
    chestwear = newgear;

    return c;
  }

  // retorna o que foi tirado
  private ProtectiveGear setHandwear(ProtectiveGear newgear)
  {
    if(!newgear.isHandwear()) return newgear;

    ProtectiveGear h = handwear;
    handwear = newgear;

    return h;
  }

  // retorna o que foi tirado
  private ProtectiveGear setLegwear(ProtectiveGear newgear)
  {
    if(!newgear.isLegwear()) return newgear;

    ProtectiveGear l = legwear;
    legwear = newgear;

    return l;
  }

  // retorna o que foi tirado
  private ProtectiveGear setShoes(ProtectiveGear newgear)
  {
    if(!newgear.isFootwear()) return newgear;

    ProtectiveGear s = shoes;
    shoes = newgear;

    return s;
  }


  public ImmutableCharacterAttributeSet getStatChange()
  {
    return overallstatchange.toImmutable();
  }

  public int getProtection()
  {
    int protection = headwear.getProtection();
    protection += chestwear.getProtection();
    protection += handwear.getProtection();
    protection += legwear.getProtection();
    protection += shoes.getProtection();

    return protection;
  }
}
