package proj;

import javax.swing.SwingUtilities;
import java.awt.*;
import proj.ResourceLoader;
import proj.jogo.mobs.Player;
import proj.view.GameGUI;
import proj.jogo.items.GameMachine;

public class Main
{
  public static void main(String[] args) {
    ResourceLoader.loadFont("VCR_OSD_MONO_1.001.ttf");
    ResourceLoader.loadFont("Perfect-DOS-VGA-437.ttf");

    // new GameMachine().use(null);
    GameGUI gui = new GameGUI();
    Player p = new Player(gui.prompt(ResourceLoader.getPrompt("username")));
    System.out.println(p.getName());
  }
}
