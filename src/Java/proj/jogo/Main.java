package proj.jogo;

import javax.swing.SwingUtilities;
import java.awt.*;
import proj.ResourceLoader;
import proj.cardgames.Blackjack;
import proj.jogo.Personagem;
import proj.view.GuiTeste;

public class Main
{
  public static void main(String[] args) {
    ResourceLoader loader = new ResourceLoader();
    loader.loadFont("VCR_OSD_MONO_1.001.ttf");
    loader.loadFont("Perfect-DOS-VGA-437.ttf");

    GuiTeste gui = new GuiTeste();
    // SwingUtilities.invokeLater(new Runnable() {
      // public void run() {
        // gui.init();
      // }
    // });

    new Blackjack(gui.getTextOut()).play(0);
  }
}
