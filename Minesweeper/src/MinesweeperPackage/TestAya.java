package MinesweeperPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class TestAya
{

   public static void main(String[] args) throws Exception
   {

       /*
       GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
       Font[] allFonts = ge.getAllFonts();
       for (int i = 0; i < allFonts.length; i++)
       {
           Font f = allFonts[i].deriveFont(10.0f);
           System.out.println(f);
       }
        */

       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        System.out.println("width:" + width);
        System.out.println("height: " + height);

   }

   private static void BufferedImageStuff() throws IOException
   {

    BufferedImage image = ImageIO.read(
         new URL("http://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png"));

    int w = image.getWidth();
    int h = image.getHeight();

    int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w);

    Color c = new Color(dataBuffInt[100]);

    System.out.println(c.getRed());   // = (dataBuffInt[100] >> 16) & 0xFF
    System.out.println(c.getGreen()); // = (dataBuffInt[100] >> 8)  & 0xFF
    System.out.println(c.getBlue());  // = (dataBuffInt[100] >> 0)  & 0xFF
    System.out.println(c.getAlpha()); // = (dataBuffInt[100] >> 24) & 0xFF
   }
}