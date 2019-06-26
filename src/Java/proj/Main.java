package proj;

import proj.ResourceLoader;
import proj.jogo.items.armor.LeatherJacket;
import proj.jogo.items.Coin;
import proj.jogo.items.GameMachine;
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
    Coin c = new Coin();
    GameMachine gm = new GameMachine();
    gui.getWritable().appendText(j.getName() + ":\n", "default-bold");
    gui.getWritable().appendText(j.getDescription() + '\n', "default");
    gui.getWritable().appendText(c.getName() + ":\n", "default-bold");
    gui.getWritable().appendText(c.getDescription() + '\n', "default");
    gui.getWritable().appendText(gm.getName() + ":\n", "default-bold");
    gui.getWritable().appendText(gm.getDescription() + '\n', "default");
    gm.use(null);
    // Player p = new Player("Teste");
  }
}
