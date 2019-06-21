package proj;

import java.io.IOException;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.net.URL;

public class ResourceLoader
{
  public static void loadLibrary(String libname)
  {
    try
    {
      URL url = ResourceLoader.class.getResource("/libs/" + libname);
      File tmpDir = Files.createTempDirectory("tmp").toFile();
      tmpDir.deleteOnExit();
      File nativeLibTmpFile = new File(tmpDir, libname);
      nativeLibTmpFile.deleteOnExit();
      InputStream in = url.openStream();
      Files.copy(in, nativeLibTmpFile.toPath());
      System.load(nativeLibTmpFile.getAbsolutePath());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

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
