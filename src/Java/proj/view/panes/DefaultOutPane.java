package proj.view.panes;

import proj.Global;
import proj.view.panes.WritableScrollableOutPane;
import javax.swing.text.*;

public class DefaultOutPane extends WritableScrollableOutPane
{
  public DefaultOutPane()
  {
    super();
    tp.setBackground(Global.getColorScheme(Global.BG));
    setupStyles();
    startWriterThread();
    pause();
  }

  public void clear()
  {
    t.clear();
    try
    {
      StyledDocument doc = tp.getStyledDocument();
      doc.remove(0, doc.getLength());
    }
    catch (BadLocationException e) {e.printStackTrace();}
    currenttext = null;
  }

  protected void setupStyles()
  {
    StyledDocument doc = tp.getStyledDocument();
    Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
    Style centered = doc.addStyle("centered", def);
    StyleConstants.setAlignment(centered, StyleConstants.ALIGN_CENTER);

    Style defaultinho = doc.addStyle("default", def);
    StyleConstants.setFontFamily(defaultinho, "Pixel Operator Mono");
    StyleConstants.setFontSize(defaultinho, 20);
    StyleConstants.setForeground(defaultinho, Global.getColorScheme(Global.TEXT));
    Style defaultinho2 = doc.addStyle("default-bold", defaultinho);
    StyleConstants.setFontFamily(defaultinho2, "Pixel Operator Mono Bold");
    Style c1 = doc.addStyle("default-c1", defaultinho);
    StyleConstants.setForeground(c1, Global.getColorScheme(Global.P1));
    Style c2 = doc.addStyle("default-c2", defaultinho);
    StyleConstants.setForeground(c2, Global.getColorScheme(Global.P2));
    Style c3 = doc.addStyle("default-c3", defaultinho);
    StyleConstants.setForeground(c3, Global.getColorScheme(Global.P3));
  }
}
