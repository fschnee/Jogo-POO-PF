package proj.jogo.items;

import proj.jogo.common.CharacterActionResult;
import proj.jogo.mobs.Character;

public interface OtherUsable
{
  public CharacterActionResult use(Character sender, Character reciever);
}
