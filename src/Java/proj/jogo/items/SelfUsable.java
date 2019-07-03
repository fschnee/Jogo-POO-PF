package proj.jogo.items;

import proj.jogo.common.CharacterActionResult;
import proj.jogo.mobs.Character;

public interface SelfUsable
{
  public CharacterActionResult use(Character sender);
}
