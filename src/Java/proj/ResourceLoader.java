package proj;

import proj.json.*;
import proj.resource.*;
import proj.jogo.spaces.*;
import java.io.IOException;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.net.URL;
import java.util.HashMap;
import java.util.ArrayList;

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
                                       ResourceLoader.class
                                       .getClassLoader()
                                       .getResourceAsStream("assets/font/" + filename));
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

    try (InputStream f = ResourceLoader.class.getClassLoader()
                         .getResourceAsStream(location + filename))
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

  // Verifique se o json do mapa está com todas os fields escritos certo
  public static HashMap<String, Space> loadMap(String folder, String startingroom)
  {
    HashMap<String, Space> ret = new HashMap<String, Space>();
    ArrayList<String> spacestoload = new ArrayList<String>();
    ArrayList<String> spacesloaded = new ArrayList<String>();
    JSONEncoded currentroomjson;
    spacestoload.add(startingroom);

    while(spacestoload.size() != 0)
    {
      String currentroom = spacestoload.remove(0);
      if (spacesloaded.contains(currentroom)) continue;

      try (InputStream f = ResourceLoader.class.getClassLoader()
                          .getResourceAsStream(folder + currentroom + ".json"))
      {
        currentroomjson = JSON.parse(f);

        HashMap<String, JSONEncoded> content = (HashMap<String, JSONEncoded>)currentroomjson.getData();

        if(content.get("connections") != null)
        {
          ArrayList<JSONEncoded> connections = (ArrayList<JSONEncoded>)content.get("connections").getData();
          for(JSONEncoded e : connections) spacestoload.add((String)e.getData());
        }

        spacesloaded.add(currentroom);
        ret.put((String)content.get("name").getData(), (Space)Class.forName((String)content.get("classpath").getData()).newInstance());
        System.out.println("Loaded room: " + currentroom);
      }
      catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e)
      {
        e.printStackTrace();
      }
    }

    return ret;
  }
}
