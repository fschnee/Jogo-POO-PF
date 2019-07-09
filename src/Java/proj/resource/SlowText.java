package proj.resource;

import java.util.ArrayList;

public class SlowText
{
  private String style;
  private String text;
  private ArrayList<Integer> delays;

  private int lastletter;
  private int lastdelay;
  private Boolean isdone;

  public SlowText(String s, String t, ArrayList<Integer> d)
  {
    this.style = s;
    this.text = t;
    this.delays = d;
    this.lastletter = 0;
    this.lastdelay = 0;
    this.isdone = (t == null || t.equals(""));
  }

  public String getRest()
  {
    if(isdone) return null;
    return text.substring(lastletter);
  }

  public SlowLetter getNext()
  {
    if(isdone) return null;

    int currletter = lastletter;
    int currdelay = lastdelay;
    incrementLetter();

    return new SlowLetter(text.charAt(currletter), delays.get(currdelay), style);
  }

  public Boolean isDone() {return this.isdone;}

  private void incrementLetter()
  {
    if(lastletter >= text.length() - 1) isdone = true;
    else ++lastletter;

    if(lastdelay < delays.size() - 1) ++lastdelay;
  }
}
