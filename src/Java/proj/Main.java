package proj;

import proj.Global;
import proj.ResourceLoader;
import proj.view.Writable;
import proj.jogo.items.Item;
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
    Global.init(gui);
    gui.setupGUI();
    Writable w = gui.getTextOut("Storytime");

    GameMachine gm = new GameMachine();
    w.appendText(gm.getName() + ":\n", "default-bold");
    w.appendText(gm.getDescription() + "\n", "default");
    gm.use(null);

    Item c = new Coin();
    w.appendText(c.getName() + ":\n", "default-bold");
    w.appendText(c.getDescription() + "\n", "default");

    Item l = new LeatherJacket();
    w.appendText(l.getName() + ":\n", "default-bold");
    w.appendText(l.getDescription() + "\n", "default");
  }
}
