package proj.jogo;

import proj.Global;
import proj.jogo.mobs.Team;
import proj.jogo.mobs.Attack;
import proj.jogo.mobs.Character;
import proj.view.BattlePanel;

public class Battle
{
  private Team playerteam;
  private Team enemyteam;
  private boolean playerturn;
  private int currcharacter;

  public Battle(Team enemyteam, boolean playerstart)
  {
    if(playerstart == true) playerturn = true;
    else playerturn = false;
    currcharacter = 0;
    this.playerteam = Global.getGlobal().getPlayer().getParty();
    this.enemyteam = enemyteam;

    Global.getGlobal().setActiveBattle(this);
    Global.getGlobal().getGUI().setActivePane("Battle");
    ((BattlePanel)Global.getGlobal().getGUI().getPanel("Battle")).remakeGUI();
  }

  public void attack(Character target, Attack atk)
  {
    target.recieveAttack(atk);

    boolean wonbattle = true;
    for(Character en : enemyteam.get())
      if(en.getHealth() > 0)
      {
        wonbattle = false;
        break;
      }
    boolean lostbattle = true;
    for(Character ally : playerteam.get())
      if(ally.getHealth() > 0)
      {
        lostbattle = false;
        break;
      }
    if(wonbattle)
    {
      Global.getGlobal().getGUI().setActivePane("Battlewon");
      return;
    }
    else if(lostbattle)
    {
      Global.getGlobal().getGUI().setActivePane("Battlelost");
      return;
    }

    Team attacking;
    if(playerturn == true) attacking = playerteam;
    else attacking = enemyteam;

    int attacker = currcharacter;
    if(++currcharacter >= attacking.size())
    {
      currcharacter = 0;
      playerturn = !playerturn;
    }

    ((BattlePanel)Global.getGlobal().getGUI().getPanel("Battle"))
    .signalAttack(attacking.get(attacker), target, atk);
  }

  public Team getAllies() {return playerteam;}
  public Team getEnemies() {return enemyteam;}
  public Character getTurnOwner()
  {
    if(playerturn == true) return playerteam.get(currcharacter);
    else return enemyteam.get(currcharacter);
  }
  public void advanceTurn()
  {
    if(playerturn == false)
      if(enemyteam.get(currcharacter).getHealth() > 0)
        enemyteam.get(currcharacter).signalForAttack();
  }
}
