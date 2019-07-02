package proj.view.panes;

import proj.view.GameGUI;
import proj.view.panes.WritableScrollableOutPane;
import proj.resource.SlowText;
import java.util.ArrayList;
import javax.swing.text.*;
import java.awt.Dimension;

public class GameGUIOutPane extends WritableScrollableOutPane
{
  public GameGUIOutPane()
  {
    super();
    tp.setBackground(GameGUI.getColorScheme(GameGUI.BG));
    setupStyles();
    startWriterThread();
    pause();
  }

  @Override
  public synchronized void appendText(String s, String style)
  {
    ArrayList<Integer> temp = new ArrayList<Integer>();
    temp.add(Integer.valueOf(80));
    t.addLast(new SlowText(style, s, temp));
  }

  protected void setupStyles()
  {
    StyledDocument doc = tp.getStyledDocument();
    Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

    Style defaultinho = doc.addStyle("default", def);
    StyleConstants.setFontFamily(defaultinho, "Pixel Operator Mono");
    StyleConstants.setFontSize(defaultinho, 20);
    StyleConstants.setForeground(defaultinho, GameGUI.getColorScheme(GameGUI.TEXT));
    Style defaultinho2 = doc.addStyle("default-bold", defaultinho);
    StyleConstants.setFontFamily(defaultinho2, "Pixel Operator Mono Bold");
  }
}
