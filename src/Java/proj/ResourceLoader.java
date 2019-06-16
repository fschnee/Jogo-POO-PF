package proj;

import java.io.IOException;
import java.awt.*;

public class ResourceLoader
{
  public void loadFont(String filename)
  {
    try
    {
      Font novafonte = Font.createFont(Font.TRUETYPE_FONT,
                                        getClass().
                                        getClassLoader().
                                        getResourceAsStream("assets/font/" + filename));
      GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(novafonte);
    }
    catch (FontFormatException | IOException e)
    {
      e.printStackTrace();
    }
  }
}
