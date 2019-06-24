package proj.json;

import proj.json.JSONValue;
import proj.json.JSONEncoded;
import java.util.HashMap;

public class JSONObj implements JSONEncoded
{
  private HashMap<String, JSONEncoded> fields;

  public JSONObj()
  {
    fields = new HashMap<String, JSONEncoded>();
  }

  public void addField(String fieldname, JSONEncoded fieldval)
  {
    fields.put(fieldname, fieldval);
  }

  public Object getData() {return fields;}
  public JSONValue getType()   {return JSONValue.Obj;}
}
