package proj.view;

import proj.view.Pausable;
import proj.view.Writeable;

public interface GUIPanel extends Pausable
{
  public Writeable getTextOut();
}
