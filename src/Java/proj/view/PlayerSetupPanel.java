package proj.view;

import proj.view.Writable;
import proj.view.panes.InteractiveOutPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;

public class PlayerSetupPanel extends JPanel implements GUIPanel
{
  private InteractiveOutPane textout;
  private GridBagConstraints c;
  private boolean inputEnabled;
  private StringBuilder inputBuilder;

  public PlayerSetupPanel()
  {
    super();

    setLayout(new GridBagLayout());
    c = new GridBagConstraints();
    setPreferredSize(new Dimension(800, 600));

    textout = new InteractiveOutPane();
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    add(textout.getContent(), c);

    inputEnabled = false;
    inputBuilder = new StringBuilder();
  }

  public synchronized void pause() {textout.pause();}
  public synchronized void resume() {textout.resume();}
  public Writable getTextOut() {return textout;}
  public synchronized void setInputEnabled() {inputEnabled = true;inputBuilder.setLength(0);}
  public synchronized boolean isEnabled() {return inputEnabled;}
  public synchronized String getInput() {return inputBuilder.toString();}
  public synchronized void inputChannel(int c) {
    if(inputEnabled)
    {
      if(c == '\n') // Enter
      {
        inputEnabled = false;
        textout.appendText(String.valueOf((char)c), "default");
      }
      else if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' '
              || (c >= '0' && c <= '9') || c == '\'' || c == '\"'
              || c == '.' || c == ',')
      {
        textout.appendText(String.valueOf((char)c), "alt-user");
        inputBuilder.append((char)c);
      }
      else if(c == 8) // Backspace
      {
        if(inputBuilder.length() > 0)
          inputBuilder.setLength(inputBuilder.length() - 1);
        textout.appendText(String.valueOf((char)c), "alt-user");
      }
    }
  }
}
