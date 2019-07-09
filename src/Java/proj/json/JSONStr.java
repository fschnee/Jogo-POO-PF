package proj.json;

import proj.json.JSONValue;
import proj.json.JSONEncoded;

public class JSONStr implements JSONEncoded
{
  private String text;

  public JSONStr(String text)
  {
    this.text = text;
  }

  public Object getData()    {return text;}
  public JSONValue getType() {return JSONValue.Str;}
}
