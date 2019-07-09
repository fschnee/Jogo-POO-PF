package proj.jogo.common;

import proj.jogo.common.Named;
import proj.jogo.mobs.Character;

public interface Interactable extends Named
{
  public void interactUsing(Character interactor);
}
