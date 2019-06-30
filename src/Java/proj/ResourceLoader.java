package proj;

import proj.json.*;
import proj.resource.*;
import java.io.IOException;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.net.URL;
import java.util.HashMap;

public abstract class ResourceLoader
{
  public static void printFonts()
  {
    for(Font f : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts())
      System.out.println(f.getName());
  }

  public static void loadLibrary(String libname)
  {
    try
    {
      File tmpDir = Files.createTempDirectory("JOGO-POO-PF-TEMPDIR").toFile();
      tmpDir.deleteOnExit();
      File nativeLibTmpFile = new File(tmpDir, libname);
      nativeLibTmpFile.deleteOnExit();

      InputStream in = ResourceLoader.class.getResource("/libs/" + libname).openStream();
      Files.copy(in, nativeLibTmpFile.toPath());
      in.close();

      System.load(nativeLibTmpFile.getAbsolutePath());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static void loadFont(String filename)
  {
    try
    {
      Font novafonte = Font.createFont(Font.TRUETYPE_FONT,
                                       ResourceLoader.class.
                                       getClassLoader().
                                       getResourceAsStream("assets/font/" + filename));
      GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(novafonte);
    }
    catch (FontFormatException | IOException e)
    {
      e.printStackTrace();
    }
  }

  public static String getJsonField(String location, String filename, String fieldname)
  {
    JSONEncoded item = null;

    try (InputStream f = ResourceLoader.class.getClassLoader().
                         getResourceAsStream(location + filename))
    {
      item = JSON.parse(f);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (item != null)
    {
      if (item.getType() == JSONValue.Obj)
      {
        HashMap<String, JSONEncoded> content = (HashMap<String, JSONEncoded>)item.getData();
        if (content.get(fieldname) != null) return ((String)(content.get(fieldname).getData()));
      }
    }
    return "Field not found: " + fieldname + "@" + location + filename;
  }

}
