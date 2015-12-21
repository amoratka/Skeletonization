package skeletonization;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_BYTE_GRAY;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Kamila
 */
public class Image {
    BufferedImage image;
    //private static Object BMPDecoder;


    public static BufferedImage load_image() {
        //do otwierania automatycznie obrazka     
        BufferedImage image = null;
        File imageFile;
        imageFile = new File("lenna.bmp");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
        }
        return image;
    }
    public static void write_image(BufferedImage image) throws IOException {
        File ouptut = new File("grayscale.jpg");
        ImageIO.write(image, "bmp", ouptut);
    }

    /**
     *
     * @param image2
     * @param image
     * @return 
     */
    public static BufferedImage to_grey_scale(BufferedImage image2) {
        
       // image= image2;
        int width;
    int height;
    width = image2.getWidth();
            height = image2.getHeight();
            BufferedImage image3 = new BufferedImage(width,height,TYPE_INT_RGB);
            for (int i = 0; i < height; i++) {

                for (int j = 0; j < width; j++) {

                    Color c = new Color(image2.getRGB(j, i));
                    int red = (int) (c.getRed() * 0.299);
                    int green = (int) (c.getGreen() * 0.587);
                    int blue = (int) (c.getBlue() * 0.114);
                    Color newColor = new Color(red + green + blue,
                            red + green + blue, red + green + blue);

                    image3.setRGB(j, i, newColor.getRGB());
                }
            }
        return image3;
    }
}
