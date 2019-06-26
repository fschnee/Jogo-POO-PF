package proj;

import proj.ResourceLoader;
import proj.jogo.mobs.Player;
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
    String sampletext = ResourceLoader.getJsonField("assets/text/","sampletext.json","text");
    gui.getWritable().appendText(sampletext.substring(0, 38), "default-bold");
    gui.getWritable().appendText(sampletext.substring(38), "default");
    // Player p = new Player("Teste");
  }
}
