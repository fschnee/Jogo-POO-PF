package proj.view.panes;

import proj.Global;
import proj.view.panes.WritableScrollableOutPane;
import javax.swing.text.*;
import java.awt.*;

public class CardGameTermOutPane extends WritableScrollableOutPane
{
  public CardGameTermOutPane()
  {
    super();
    tp.setBackground(Global.getColorScheme(Global.BG));
    setupStyles();
    startWriterThread();
    pause();
  }

  protected void setupStyles()
  {
    StyledDocument doc = tp.getStyledDocument();
    Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

    Style terminal = doc.addStyle("default", def);
    StyleConstants.setFontFamily(terminal, "VCR OSD Mono");
    StyleConstants.setFontSize(terminal, 16);
    StyleConstants.setForeground(terminal, Global.getColorScheme(Global.TEXT));
  }
}
