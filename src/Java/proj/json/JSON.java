package proj.json;

import proj.json.*;
import java.io.InputStream;

public abstract class JSON
{
  public static JSONEncoded parse(InputStream i)
  {
    // TODO: parse the input

    return new JSONNull();
  }
}
