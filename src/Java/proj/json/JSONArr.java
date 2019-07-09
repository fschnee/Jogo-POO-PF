package proj.json;

import proj.json.JSONEncoded;
import proj.json.JSONValue;
import java.util.ArrayList;

public class JSONArr implements JSONEncoded
{
  private ArrayList<JSONEncoded> elements;

  public JSONArr()
  {
    elements = new ArrayList<JSONEncoded>();
  }

  public void push(JSONEncoded e)
  {
    elements.add(e);
  }

  public Object getData()   {return elements;}
  public JSONValue getType(){return JSONValue.Arr;}
}
