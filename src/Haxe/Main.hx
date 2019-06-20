class Main
{
    public static function main():Void
    {
      var args = Sys.args();
      if(args.length <= 1)
      {
        trace("arguments should be: <outputdir> <inputfile1> ... <inputfileN>");
        Sys.exit(1);
      }

      for (i in 1...args.length)
      {
        processimg(args[0], args[i]);
      }
    }

    public static function processimg(outputdir:String, filepath:String)
    {
      var arquivoimg = sys.io.File.read(filepath);
      var filename = new haxe.io.Path(filepath).file + ".png";
      var image = new format.png.Reader(arquivoimg).read();
      var imginfo = getimgdata(image);
      arquivoimg.close();

      var imagem = new Array<Array<{r:Int,g:Int,b:Int,a:Int}>>();
      for (y in 0...imginfo.height) {
        var coluna = new Array<{r:Int, g:Int, b:Int, a:Int}>();
        for (x in 0...imginfo.width) {
          var p = imginfo.data.getInt32(4*(x+y*imginfo.width));

          var imginfo = {
            a:p>>>24,
            r:(p>>>16)&0xff,
            g:(p>>>8)&0xff,
            b:(p)&0xff
          };
          coluna.push(imginfo);
        }
        imagem.push(coluna);
      }

      var imagem2 = new Array<Array<Int>>();
      for (y in 0...imginfo.height) {
        var coluna2 = new Array<Int>();
        for (x in 0...imginfo.width) {
          coluna2.push(toGrayscale(imagem[y][x]));
        }
        imagem2.push(coluna2);
      }

      floyd_stanberg_dither_greyscale(imagem2, imginfo.width, imginfo.height);

      var b = haxe.io.Bytes.alloc(imginfo.height * imginfo.width * 3);
      for (y in 0...imginfo.height)
        for (x in 0...imginfo.width) {
          b.set(3*(x+y*imginfo.width), imagem2[y][x]);
          b.set(3*(x+y*imginfo.width) + 1, imagem2[y][x]);
          b.set(3*(x+y*imginfo.width) + 2, imagem2[y][x]);
        }


      var outimg = format.png.Tools.buildRGB(imginfo.width, imginfo.height, b);
      new format.png.Writer(sys.io.File.write(outputdir + "/" + filename)).write(outimg);
    }

    public static function toGrayscale(p:{r:Int, g:Int, b:Int}):Int
    {
      return Std.int(0.30*p.r + 0.59*p.g + 0.11*p.b);
    }

    public static function discrete(v:Int):Int
    {
      if (v > 128) return 255
      else return 0;
    }

    public static function floyd_stanberg_dither_greyscale(img:Array<Array<Int>>, width:Int, height:Int)
    {
      for (y in 0...height)
      {
        for (x in 0...width)
        {
          var pixeloriginal = img[y][x];
          var pixeldiscreto = discrete(pixeloriginal);
          var erro = pixeloriginal - pixeldiscreto;

          img[y][x] = pixeldiscreto;
          if(x + 1 < width) img[y][x + 1] += Std.int(7 * erro / 16);
          if(y + 1 < height) img[y + 1][x] += Std.int(5 * erro / 16);
          if(y + 1 < height && x - 1 >= 0) img[y + 1][x - 1] += Std.int(3 * erro / 16);
          if(y + 1 < height && x + 1 < width) img[y + 1][x + 1] += Std.int(erro / 16);
        }
      }
    }

    public static function getimgdata(d:format.png.Data):{data:haxe.io.Bytes, width:Int, height:Int}
    {
      var imgheader = format.png.Tools.getHeader(d);

      var tmp = {
        data:format.png.Tools.extract32(d),
        width:imgheader.width,
        height:imgheader.height
      }

      return tmp;
    }
}
