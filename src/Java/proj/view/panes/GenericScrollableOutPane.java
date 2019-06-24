package proj.view.panes;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.text.*;

public class GenericScrollableOutPane
{
  protected JTextPane tp;
  protected JScrollPane sp;

  public GenericScrollableOutPane()
  {
    tp = new JTextPane();
    tp.setEditable(false);
    ((DefaultCaret)tp.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

    sp = new JScrollPane(tp);
  }

  public JScrollPane getContent()
  {
    return this.sp;
  }
}
