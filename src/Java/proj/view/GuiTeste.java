package proj.view;

import proj.view.Writeable;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class GuiTeste implements Writeable
{
  private JTextPane janeladetexto;
  private JScrollPane janeladetextowrapper;
  private JFrame frame;

  public GuiTeste()
  {
    frame = new JFrame("Janela teste");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.setSize(600, 600);

    janeladetexto = new JTextPane();
    janeladetexto.setBounds(0, 0, 600, 600);
    janeladetexto.setEditable(false);
    janeladetexto.setText("Teste");
    janeladetextowrapper = new JScrollPane(janeladetexto);

    frame.add(janeladetextowrapper);
    frame.setVisible(true);
  }

  public void appendText(String s)
  {
    try
    {
      StyledDocument doc = janeladetexto.getStyledDocument();
      doc.insertString(doc.getLength(), s, null);
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
  }
}
