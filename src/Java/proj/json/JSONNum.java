package proj.json;

import proj.json.JSONValue;
import proj.json.JSONEncoded;

public class JSONNum implements JSONEncoded
{
  private int number;

  public JSONNum(int number)
  {
    this.number = number;
  }

  public Object getData()    {return number;}
  public JSONValue getType() {return JSONValue.Num;}
}
