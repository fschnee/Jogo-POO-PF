package proj;

import proj.ResourceLoader;
import proj.jogo.items.armor.LeatherJacket;
import proj.view.GameGUI;
import java.awt.*;

public class Main
{
  public static void main(String[] args) {
    ResourceLoader.loadFont("VCR_OSD_MONO_1.001.ttf");
    ResourceLoader.loadFont("rainyhearts.ttf");
    ResourceLoader.loadFont("PixelOperatorMono.ttf");
    ResourceLoader.loadFont("PixelOperatorMono-Bold.ttf");

    GameGUI gui = new GameGUI();
    LeatherJacket j = new LeatherJacket();
    gui.getWritable().appendText(j.getName() + ":\n", "default");
    gui.getWritable().appendText(j.getDescription(), "default");
    // Player p = new Player("Teste");
  }
}
