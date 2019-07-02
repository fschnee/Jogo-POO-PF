package proj.view;

import java.awt.Font;
import java.util.ArrayList;

public interface Writable
{
  public void appendText(String s, String style);
  public void appendText(String s, String style, ArrayList<Integer> delays);
  public boolean isDone();
}
