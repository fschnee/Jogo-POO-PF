package proj;

import java.io.IOException;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.net.URL;
import proj.json.*;
import java.util.HashMap;

public abstract class ResourceLoader
{
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

  public static String getPrompt(String filename)
  {
    return "Placeholder";
  }

  public static String getItemDescription(String itemname)
  {
    JSONEncoded item = null;

    try (InputStream f = ResourceLoader.class.getClassLoader().
                         getResourceAsStream("assets/text/items/" + itemname))
    {
      item = JSON.parse(f);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (item != null)
    {
      if(item.getType() == JSONValue.Obj)
      {
        HashMap<String, JSONEncoded> content = (HashMap<String, JSONEncoded>)item.getData();
        if(content.get("description") != null)
          if(content.get("description").getType() == JSONValue.Str)
            return (String)(content.get("description").getData());
      }
    }
    return "null";
  }
}
