package proj.view;

import proj.view.Writeable;
import proj.view.WriteOnlyScrollableTextPane;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class GuiTeste
{
  private JFrame frame;
  private WriteOnlyScrollableTextPane tpw;

  public GuiTeste()
  {
    frame = new JFrame("Janela teste");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.setSize(600, 600);

    tpw = new WriteOnlyScrollableTextPane();

    frame.add(tpw.getWrapper());
    frame.setVisible(true);
  }

  public Writeable getTextOut()
  {
    return tpw;
  }
}
