package proj;

import proj.Global;
import proj.ResourceLoader;
import proj.view.GameGUI;
import proj.view.Writable;
import proj.view.GUIPanel;
import proj.jogo.TraversalManager;
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
    Global.init(new TraversalManager());

    //playSetup();
    Global.init(new Player("Fred"));
    gui.setActivePane("Traversal");
  }

  public static void playSetup()
  {
    GameGUI gui = Global.getGlobal().getGUI();
    GUIPanel panel = gui.getPanel("Setup");
    Writable output = gui.getTextOut("Setup");
    ArrayList<Integer> temp = new ArrayList<Integer>();
    ArrayList<Integer> temp2 = new ArrayList<Integer>();
    temp2.add(Integer.valueOf(5));

    // Name
    temp.add(Integer.valueOf(300));
    output.appendText("???: ", "default-bold");
    output.appendText(ResourceLoader.getJsonField("assets/text/", "setup.json", "t1"), "alt");
    output.appendText("\n. . .\n", "alt", temp);
    output.appendText("???: ", "default-bold");
    output.appendText(ResourceLoader.getJsonField("assets/text/", "setup.json", "q1") + "\n> ", "alt");
    while(!output.isDone()) try{Thread.sleep(200);}catch(InterruptedException e){}
    panel.setInputEnabled();
    while(panel.isEnabled()) try{Thread.sleep(200);}catch(InterruptedException e){}
    String pname = panel.getInput();
    Global.init(new Player(pname));

    // Purpose
    temp = new ArrayList<Integer>();
    temp.add(Integer.valueOf(300));
    temp.add(Integer.valueOf(400));
    temp.add(Integer.valueOf(500));
    output.appendText("???: ", "default-bold", temp2);
    output.appendText("I see ", "alt");
    output.appendText(". . . ", "alt", temp);
    output.appendText(ResourceLoader.getJsonField("assets/text/", "setup.json", "q2") + pname + " ?\n> ", "alt");
    while(!output.isDone()) try{Thread.sleep(200);}catch(InterruptedException e){}
    panel.setInputEnabled();
    while(panel.isEnabled()) try{Thread.sleep(200);}catch(InterruptedException e) {}
    output.appendText("???: ", "default-bold", temp2);
    output.appendText(ResourceLoader.getJsonField("assets/text/", "setup.json", "t2") + "\n", "alt");
    // String purpose = panel.getInput();

    temp = new ArrayList<Integer>();
    temp.add(Integer.valueOf(60));
    output.appendText("???-2: ", "default-bold", temp2);
    output.appendText("Good, finally, yes.\n", "alt-p1", temp);
    temp = new ArrayList<Integer>();
    temp.add(Integer.valueOf(200));
    output.appendText("???-3: ", "default-bold", temp2);
    output.appendText("Sure . . .\n", "alt-p2", temp);
    output.appendText("???-4: ", "default-bold", temp2);
    output.appendText("Just start already!\n", "alt-p3");
    output.appendText("???-1: ", "default-bold", temp2);
    output.appendText(ResourceLoader.getJsonField("assets/text/", "setup.json", "t3") + "\n\n", "alt");
    while(!output.isDone()) try{Thread.sleep(200);}catch(InterruptedException e){}
  }
}
