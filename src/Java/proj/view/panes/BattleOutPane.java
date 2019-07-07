package proj.view.panes;

import proj.Global;
import proj.view.panes.WritableScrollableOutPane;
import proj.resource.SlowText;
import java.util.ArrayList;
import javax.swing.text.*;
import java.awt.Dimension;

public class BattleOutPane extends WritableScrollableOutPane
{
  public BattleOutPane()
  {
    super();
    tp.setBackground(Global.getColorScheme(Global.BG));
    setupStyles();
    StyledDocument doc = tp.getStyledDocument();
    doc.setParagraphAttributes(0, doc.getLength(), doc.getStyle("centered"), false);
    startWriterThread();
    pause();
  }

  @Override
  public synchronized void appendText(String s, String style)
  {
    ArrayList<Integer> temp = new ArrayList<Integer>();
    temp.add(Integer.valueOf(10));
    appendText(s, style, temp);
  }

  protected void setupStyles()
  {
    StyledDocument doc = tp.getStyledDocument();
    Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
    Style centered = doc.addStyle("centered", def);
    StyleConstants.setAlignment(centered, StyleConstants.ALIGN_CENTER);

    Style defaultinho = doc.addStyle("default", def);
    StyleConstants.setFontFamily(defaultinho, "Pixel Operator Mono");
    StyleConstants.setFontSize(defaultinho, 22);
    StyleConstants.setForeground(defaultinho, Global.getColorScheme(Global.TEXT));
    Style defaultinho2 = doc.addStyle("default-bold", defaultinho);
    StyleConstants.setFontFamily(defaultinho2, "Pixel Operator Mono Bold");
  }
}
