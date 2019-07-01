package proj.json;

import proj.json.*;
import java.io.InputStream;
import java.io.IOException;

public abstract class JSON
{
  public static JSONEncoded parse(InputStream i) throws IOException
  {
    int l = 0;
    do
    {
      try
      {
        l = i.read();
        if(l == -1) break;
        else if(l == '{') return parse(i, new JSONObj());
        else if(l == '[') return parse(i, new JSONArr());
        else if(l != ' ') throw new IOException();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      catch (NullPointerException e)
      {
        e.printStackTrace();
        return new JSONNull();
      }
    } while(true);

    return new JSONNull();
  }

  private static JSONEncoded parse(InputStream i, JSONObj parent) throws IOException
  {
    int l = 0;
    do
    {
      try
      {
        l = i.read();
        if(l == -1) throw new IOException();
        else if(l == '}') return parent;
        else if(l != ' ' && l != '\n') //le o campo
        {
          if(l != '\"') throw new IOException();
          String fname = (String)(readstring(i).getData());

          if(i.read() != ':') throw new IOException();

          l = i.read();
          JSONEncoded fvalue = null;
          if(l == '\"') fvalue = readstring(i);
          else if(l == 't' || l == 'f') fvalue = readbool(i);
          else if(l == 'n') fvalue = readnull(i);
          else if(l >= '0' && l <= '9') fvalue = readnum(i);
          else if(l == '[') fvalue = parse(i, new JSONArr());

          parent.addField(fname, fvalue);

          do
          {
            l = i.read();
            if(l == '}') return parent;
            if(l == ',') break;
            if(l != ' ' && l != '\n') throw new IOException();
          }while(true);
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.exit(1);
      }
    }while(true);
  }

  private static JSONEncoded parse(InputStream i, JSONArr parent) throws IOException
  {
    int l = 0;
    do
    {
      try
      {
        l = i.read();

        if(l == -1) throw new IOException();
        else if(l == '{') return parse(i, new JSONObj());
        else if(l == '[') return parse(i, new JSONArr());
        else if(l == ']') return parent;
        else if(l != ' ' && l != '\n') // lê o elemento
        {
          if(l == '\"') parent.push(readstring(i));
          else if(l == 't' || l == 'f') parent.push(readbool(i));
          else if(l == 'n') parent.push(readnull(i));
          else if(l >= '0' && l <= '9') parent.push(readnum(i));
          else if(l == '{') parent.push(parse(i, new JSONObj()));

          // lê o que tem depois do elemento
          do
          {
            l = i.read();
            if(l == ']') return parent;
            if(l == ',') break;
            if(l != ' ' && l != '\n') throw new IOException();
          }while(true);
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }while(true);
  }

  private static JSONStr readstring(InputStream i) throws IOException
  {
    int l = 0;
    StringBuilder str = new StringBuilder();
    do
    {
      try
      {
        l = i.read();
        if(l == -1) throw new IOException();
        if(l == '\"') return new JSONStr(str.toString());
        else str.append((char)l);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }while(true);
  }

  private static JSONBool readbool(InputStream i) throws IOException
  {
    StringBuilder val = new StringBuilder();
    int l = 0;
    do
    {
      try
      {
        l = i.read();
        if(!(l >= 'A' && l <= 'Z') || !(l >= 'a' && l <= 'z')) break;
        val.append((char)l);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }while(true);

    String valor = val.toString();
    if(valor.equals("rue")) return new JSONBool(true);
    else if(valor.equals("alse")) return new JSONBool(false);
    else throw new IOException();
  }

  private static JSONNull readnull(InputStream i) throws IOException
  {
    StringBuilder val = new StringBuilder();
    int l = 0;
    do
    {
      try
      {
        l = i.read();
        if(!(l >= 'A' && l <= 'Z') || !(l >= 'a' && l <= 'z')) break;
        val.append((char)l);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }while(true);

    String valor = val.toString();
    if(valor.equals("ull")) return new JSONNull();
    else throw new IOException();
  }

  private static JSONNum readnum(InputStream i) throws NumberFormatException
  {
    StringBuilder val = new StringBuilder();
    int l = 0;
    do
    {
      try
      {
        l = i.read();
        if(!(l >= '0' && l <= '9')) break;
        val.append((char)l);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }while(true);

    String valor = val.toString();
    return new JSONNum(Integer.parseInt(valor));
  }
}
