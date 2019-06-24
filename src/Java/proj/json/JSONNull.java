package proj.json;

import proj.json.JSONValue;
import proj.json.JSONEncoded;

public class JSONNull implements JSONEncoded
{
  public Object getData()    {return null;}
  public JSONValue getType() {return JSONValue.NIL;}
}
