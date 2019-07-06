package proj.view.panes;

import proj.Global;
import proj.view.panes.WritableScrollableOutPane;
import proj.resource.SlowText;
import java.util.ArrayList;
import javax.swing.text.*;
import java.awt.Dimension;

public class TraversalOutPane extends WritableScrollableOutPane
{
  public TraversalOutPane()
  {
    super();
    tp.setBackground(Global.getColorScheme(Global.BG));
    setupStyles();
    startWriterThread();
    pause();
  }

  @Override
  public synchronized void appendText(String s, String style)
  {
    ArrayList<Integer> temp = new ArrayList<Integer>();
    temp.add(Integer.valueOf(80));
    appendText(s, style, temp);
  }

  @Override
  public synchronized void appendText(String s, String style, ArrayList<Integer> delays)
  {
    t.clear();
    try
    {
      StyledDocument doc = tp.getStyledDocument();
      doc.remove(0, doc.getLength());
    }
    catch (BadLocationException e) {e.printStackTrace();}
    currenttext = null;

    t.addLast(new SlowText(style, s, delays));
  }

  protected void setupStyles()
  {
    StyledDocument doc = tp.getStyledDocument();
    Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

    Style defaultinho = doc.addStyle("default", def);
    StyleConstants.setFontFamily(defaultinho, "Pixel Operator Mono");
    StyleConstants.setFontSize(defaultinho, 20);
    StyleConstants.setForeground(defaultinho, Global.getColorScheme(Global.TEXT));
    Style defaultinho2 = doc.addStyle("default-bold", defaultinho);
    StyleConstants.setFontFamily(defaultinho2, "Pixel Operator Mono Bold");
  }
}
