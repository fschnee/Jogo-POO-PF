package proj.view.panes;

import proj.view.GameGUI;
import proj.view.PlayerSetupPanel;
import proj.view.panes.WritableScrollableOutPane;
import proj.resource.SlowText;
import proj.resource.SlowLetter;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;
import javax.swing.text.*;
import java.awt.Dimension;
import java.util.ArrayList;

public class InteractiveOutPane extends WritableScrollableOutPane
{
  public InteractiveOutPane()
  {
    super();
    tp.setBackground(GameGUI.getColorScheme(GameGUI.BG));
    setupStyles();

    // center text
    StyledDocument doc = tp.getStyledDocument();
    doc.setParagraphAttributes(0, doc.getLength(), doc.getStyle("centered"), false);

    startWriterThread();
    pause();
  }

  protected void setupStyles()
  {
    StyledDocument doc = tp.getStyledDocument();
    Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

    Style centered = doc.addStyle("centered", def);
    StyleConstants.setAlignment(centered, StyleConstants.ALIGN_CENTER);

    Style defaultinho = doc.addStyle("default", def);
    StyleConstants.setFontFamily(defaultinho, "Pixel Operator Mono");
    StyleConstants.setFontSize(defaultinho, 20);
    StyleConstants.setForeground(defaultinho, GameGUI.getColorScheme(GameGUI.TEXT));
    Style defaultinho2 = doc.addStyle("default-bold", defaultinho);
    StyleConstants.setFontFamily(defaultinho2, "Pixel Operator Mono Bold");

    Style alt = doc.addStyle("alt", def);
    StyleConstants.setFontFamily(alt, "rainyhearts");
    StyleConstants.setFontSize(alt, 25);
    StyleConstants.setForeground(alt, PlayerSetupPanel.getColorScheme(PlayerSetupPanel.GM));
    Style defaultinho3 = doc.addStyle("alt-user", alt);
    StyleConstants.setForeground(defaultinho3, PlayerSetupPanel.getColorScheme(PlayerSetupPanel.USER));
    defaultinho3.addAttribute("userinput", true);
    Style altp1 = doc.addStyle("alt-p1", alt);
    StyleConstants.setForeground(altp1, PlayerSetupPanel.getColorScheme(PlayerSetupPanel.P1));
    Style altp2 = doc.addStyle("alt-p2", alt);
    StyleConstants.setForeground(altp2, PlayerSetupPanel.getColorScheme(PlayerSetupPanel.P2));
    Style altp3 = doc.addStyle("alt-p3", alt);
    StyleConstants.setForeground(altp3, PlayerSetupPanel.getColorScheme(PlayerSetupPanel.P3));
  }

  @Override
  public synchronized void appendText(String s, String style)
  {
    ArrayList<Integer> temp = new ArrayList<Integer>();
    temp.add(Integer.valueOf(100));
    t.addLast(new SlowText(style, s, temp));
  }

  @Override
  protected synchronized int write()
  {
    if(currenttext == null || currenttext.isDone())
    {
      if(t.size() == 0) return 2;
      else currenttext =  t.removeFirst();
    }
    SlowLetter tobeprinted = currenttext.getNext();
    StyledDocument doc = tp.getStyledDocument();
    char c = tobeprinted.getChar();

    try
    {
      Style s = doc.getStyle(tobeprinted.getStyle());

      if(s.containsAttribute("userinput", true))
      {
        if(c == 8) // backspace
        {
          // se o elemento anterior foi do usuario remove
          if(doc.getCharacterElement(doc.getLength() - 1).getAttributes().containsAttribute("userinput", true))
            doc.remove(doc.getLength() - 1, 1);
        }
      }

      if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ' || c == '\n'
         || c == '>' || (c >= '0' && c <= '9') || c == ',' || c == '.' || c == '!'
         || c == '\'' || c == '\"' || c == '-' || c == '?' || c == ':')
      {
        doc.insertString(doc.getLength(), String.valueOf(c), null);
        doc.setCharacterAttributes(doc.getLength() - 1, 1, s, false);
      }
    }
    catch (Exception e)
    {
      System.out.println(e);
    }

    return tobeprinted.getDelay();
  }
}
