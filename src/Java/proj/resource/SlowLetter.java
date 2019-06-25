package proj.resource;

public class SlowLetter
{
  private String s;
  private char c;
  private int delay;

  public SlowLetter(char c, int d, String s)
  {
    this.s = s;
    this.c = c;
    this.delay = d;
  }

  public char getChar() {return this.c;}
  public int getDelay() {return this.delay;}
  public String getStyle() {return this.s;}
}
