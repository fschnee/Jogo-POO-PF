package proj.view;

import proj.view.GameTerm;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.text.*;
import java.awt.*;

public class GameTermOutPane implements Writeable
{
  protected JTextPane tp;
  protected JScrollPane sp;

  public GameTermOutPane()
  {
    tp = new JTextPane();
    tp.setBounds(0, 0, 600, 600);
    tp.setEditable(false);
    tp.setBackground(GameTerm.getColorScheme(GameTerm.BG));
    setupStyles();
    ((DefaultCaret)tp.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

    sp = new JScrollPane(tp);
  }

  public JScrollPane getContent()
  {
    return this.sp;
  }

  public void setupStyles()
  {
    StyledDocument doc = tp.getStyledDocument();
    Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

    Style terminal = doc.addStyle("terminal", def);
    StyleConstants.setFontFamily(terminal, "VCR OSD Mono");
    // TODO: se não funcionar, carregar "Perfect DOS VGA 437"
    StyleConstants.setFontSize(terminal, 16);
    StyleConstants.setForeground(terminal, GameTerm.getColorScheme(GameTerm.TEXT));
  }

  @Override
  public void appendText(String s, Font fonte)
  {
    try
    {
      MutableAttributeSet attr = tp.getInputAttributes();
      StyledDocument doc = tp.getStyledDocument();

      StyleConstants.setFontFamily(attr, fonte.getName());
      StyleConstants.setFontSize(attr, fonte.getSize());
      StyleConstants.setItalic(attr, (fonte.getStyle() & Font.ITALIC) != 0);
      StyleConstants.setBold(attr, (fonte.getStyle() & Font.BOLD) != 0);
      StyleConstants.setForeground(attr, Color.BLACK);

      doc.insertString(doc.getLength(), s, null);
      doc.setCharacterAttributes(doc.getLength() - s.length(), s.length(),
                                 attr, false);
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
  }

  @Override
  public void appendText(String s, String style)
  {
    try
    {
      StyledDocument doc = tp.getStyledDocument();
      doc.insertString(doc.getLength(), s, null);
      doc.setCharacterAttributes(doc.getLength() - s.length(), s.length(),
                                 doc.getStyle(style), false);
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
  }
}
