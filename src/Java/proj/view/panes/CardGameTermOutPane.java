package proj.view.panes;

import proj.view.CardGameTerm;
import proj.view.panes.GenericScrollableOutPane;
import proj.view.Writeable;
import proj.resource.SlowText;
import proj.resource.SlowLetter;
import java.util.LinkedList;
import java.util.ArrayList;
import javax.swing.text.*;
import javax.swing.JTextPane;
import java.awt.*;

public class CardGameTermOutPane extends GenericScrollableOutPane implements Writeable
{
  LinkedList<SlowText> t;
  SlowText currenttext;

  public CardGameTermOutPane()
  {
    super();
    tp.setBackground(CardGameTerm.getColorScheme(CardGameTerm.BG));
    setupStyles();
    t = new LinkedList<SlowText>();
    startWriterThread();
  }

  public void setupStyles()
  {
    StyledDocument doc = tp.getStyledDocument();
    Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

    Style terminal = doc.addStyle("terminal", def);
    StyleConstants.setFontFamily(terminal, "VCR OSD Mono");
    // TODO: se não funcionar, carregar "Perfect DOS VGA 437"
    StyleConstants.setFontSize(terminal, 16);
    StyleConstants.setForeground(terminal, CardGameTerm.getColorScheme(CardGameTerm.TEXT));
  }

  @Override
  public synchronized void appendText(String s, String style)
  {
    ArrayList<Integer> temp = new ArrayList<Integer>();
    temp.add(Integer.valueOf(55));
    t.addLast(new SlowText(style, s, temp));
  }

  private void startWriterThread()
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

  private synchronized int write()
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
