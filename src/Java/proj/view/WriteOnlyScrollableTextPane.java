package proj.view;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.text.*;
import java.awt.*;

// tem que ter um nome melhor pra isso...
public class WriteOnlyScrollableTextPane implements Writeable
{
  protected JTextPane tp;
  protected JScrollPane sp;

  public WriteOnlyScrollableTextPane()
  {
    tp = new JTextPane();
    tp.setBounds(0, 0, 600, 600);
    tp.setEditable(false);
    sp = new JScrollPane(tp);
  }

  public JScrollPane getWrapper()
  {
    return this.sp;
  }

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
      doc.setCharacterAttributes(doc.getLength() - s.length(), s.length(), attr, false);
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
  }
}
