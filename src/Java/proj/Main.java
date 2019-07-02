package proj;

import proj.Global;
import proj.ResourceLoader;
import proj.view.GameGUI;
import proj.jogo.mobs.Player;
import java.awt.*;
import java.util.ArrayList;

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
    gui.getTextOut("Storytime").appendText(ResourceLoader
                                           .getJsonField("assets/text/",
                                                         "intro.json",
                                                         "text"),
                                           "default");
    gui.getTextOut("Setup").appendText(ResourceLoader
                                       .getJsonField("assets/text/",
                                                     "setup.json",
                                                     "q1") + "\n> ",
                                       "alt");
    gui.getPanel("Setup").setInputEnabled();
    while(gui.getPanel("Setup").isEnabled()) try{Thread.sleep(200);}catch(InterruptedException e){}
    String pname = gui.getPanel("Setup").getInput();
    Player p = new Player(pname);

    ArrayList<Integer> temp = new ArrayList<Integer>();
    temp.add(Integer.valueOf(300));
    temp.add(Integer.valueOf(400));
    temp.add(Integer.valueOf(500));
    gui.getTextOut("Setup").appendText("So, " + pname + ", let me tell you a story then ", "alt");
    gui.getTextOut("Setup").appendText(". . .", "alt", temp);
    while(!gui.getTextOut("Setup").isDone()) try{Thread.sleep(200);}catch(InterruptedException e){}
    gui.setActivePane("Storytime");
  }
}
