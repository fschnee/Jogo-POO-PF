package proj.view.panes;

import proj.view.panes.GenericScrollableOutPane;
import proj.view.Writeable;
import proj.resource.SlowText;
import proj.resource.SlowLetter;
import java.util.LinkedList;
import java.util.ArrayList;
import javax.swing.text.StyledDocument;

public class WritableScrollableOutPane extends GenericScrollableOutPane implements Writeable
{
  protected LinkedList<SlowText> t;
  protected SlowText currenttext;

  public WritableScrollableOutPane()
  {
    super();
    t = new LinkedList<SlowText>();
  }

  @Override
  public synchronized void appendText(String s, String style)
  {
    ArrayList<Integer> temp = new ArrayList<Integer>();
    temp.add(Integer.valueOf(55));
    t.addLast(new SlowText(style, s, temp));
  }

  protected void startWriterThread()
  {
    new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try {while(true) Thread.sleep(write());}
        catch (InterruptedException e) {}
      }
    }).start();
  }

  protected synchronized int write()
  {
    if(currenttext == null || currenttext.isDone())
    {
      if(t.size() == 0) return 10;
      else currenttext =  t.removeFirst();
    }
    SlowLetter tobeprinted = currenttext.getNext();

    try
    {
      StyledDocument doc = tp.getStyledDocument();
      doc.insertString(doc.getLength(), String.valueOf(tobeprinted.getChar()), null);
      doc.setCharacterAttributes(doc.getLength() - 1, 1,
                                 doc.getStyle(tobeprinted.getStyle()), false);
    }
    catch (Exception e)
    {
      System.out.println(e);
    }

    return tobeprinted.getDelay();
  }
}
