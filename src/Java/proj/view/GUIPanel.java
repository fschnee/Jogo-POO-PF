package proj.view;

import proj.view.Pausable;
import proj.view.Writable;

public interface GUIPanel extends Pausable
{
  public Writable getTextOut();
}
