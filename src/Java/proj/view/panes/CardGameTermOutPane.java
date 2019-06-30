package proj.view.panes;

import proj.view.CardgamePanel;
import proj.view.panes.WritableScrollableOutPane;
import javax.swing.text.*;
import java.awt.*;

public class CardGameTermOutPane extends WritableScrollableOutPane
{
  public CardGameTermOutPane()
  {
    super();
    tp.setBackground(CardgamePanel.getColorScheme(CardgamePanel.BG));
    tp.setPreferredSize(new Dimension(230, 400));
    setupStyles();
    startWriterThread();
    pause();
  }

  public void setupStyles()
  {
    StyledDocument doc = tp.getStyledDocument();
    Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

    Style terminal = doc.addStyle("terminal", def);
    StyleConstants.setFontFamily(terminal, "VCR OSD Mono");
    // TODO: se não funcionar, carregar "Perfect DOS VGA 437"
    StyleConstants.setFontSize(terminal, 16);
    StyleConstants.setForeground(terminal, CardgamePanel.getColorScheme(CardgamePanel.TEXT));
  }
}
