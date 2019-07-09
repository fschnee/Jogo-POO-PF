package proj.json;

import proj.json.JSONValue;
import proj.json.JSONEncoded;

public class JSONBool implements JSONEncoded
{
  private Boolean value;

  public JSONBool(Boolean value)
  {
    this.value = value;
  }

  public Object getData()    {return value;}
  public JSONValue getType() {return JSONValue.Bool;}
}
