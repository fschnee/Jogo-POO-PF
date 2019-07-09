package proj.view;

import proj.view.Pausable;
import proj.view.Writable;

public interface GUIPanel extends Pausable
{
  public Writable getTextOut();
  public void setInputEnabled();
  public void inputChannel(int c);
  public boolean isEnabled();
  public String getInput();
}
