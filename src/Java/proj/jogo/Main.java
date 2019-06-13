package proj.jogo;

import proj.cardgames.Blackjack;
import proj.jogo.Personagem;
import proj.view.GuiTeste;

public class Main
{
  public static void main(String[] args) {
    GuiTeste teste = new GuiTeste();

    new Blackjack(teste).play(0);
  }
}
