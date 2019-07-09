package proj.view.panes;

import proj.view.panes.GenericScrollableOutPane;
import proj.view.Writable;
import proj.view.Pausable;
import proj.resource.SlowText;
import proj.resource.SlowLetter;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;
import java.util.LinkedList;
import java.util.ArrayList;

public class WritableScrollableOutPane extends GenericScrollableOutPane implements Writable, Pausable
{
  protected LinkedList<SlowText> t;
  protected SlowText currenttext;
  protected Thread writer;

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
    appendText(s, style, temp);
  }

  @Override
  public synchronized void appendText(String s, String style, ArrayList<Integer> delays)
  {
    t.addLast(new SlowText(style, s, delays));
  }

  protected void startWriterThread()
  {
    writer = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try {while(true) Thread.sleep(write());}
        catch (InterruptedException e) {}
      }
    });
    writer.start();
  }

  public synchronized void pause() {writer.suspend();}
  public synchronized void resume() {writer.resume();}
  public synchronized boolean isDone()
  {return (t.size() == 0 && (currenttext == null || currenttext.isDone()));}

  protected synchronized int write()
  {
    if(currenttext == null || currenttext.isDone())
    {
      if(t.size() == 0) return 10;
      else currenttext =  t.removeFirst();
    }

    SlowLetter tobeprinted = currenttext.getNext();
    StyledDocument doc = tp.getStyledDocument();
    char c = tobeprinted.getChar();
    Style s = doc.getStyle(tobeprinted.getStyle());

    try
    {
      doc.insertString(doc.getLength(), String.valueOf(c), null);
      doc.setCharacterAttributes(doc.getLength() - 1, 1, s, false);
    }
    catch (Exception e)
    {
      System.out.println(e);
    }

    return tobeprinted.getDelay();
  }
}
