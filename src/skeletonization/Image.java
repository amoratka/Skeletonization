package skeletonization;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

/**
 *
 * @author Kamila
 */
public class Image {

    BufferedImage image;
    String name;

    public Image() {
        this.image = null;
        this.name = null;
    }

// załadowanie obrazka z podanego pliku
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
    // zapis obrazka w postaci do pliku o nazwie greyscale z rozszerzeniem bmp

    public static void write_image(BufferedImage image, String name) throws IOException {
        
        String[] parts = name.split(Pattern.quote("."));
        String name2 = parts[0].concat("_greyscale.");
        name2 = name2.concat(parts[1]);
        
        File ouptut = new File(name2);
        ImageIO.write(image, parts[1], ouptut);
    }

    /**
     *
     * @param image2
     * @return
     */
    
    //zamiana obrazka z kolorowego do skali szarości, zwraca obrazek
    public static BufferedImage to_grey_scale(BufferedImage image2) {

        int width;
        int height;
        width = image2.getWidth();
        height = image2.getHeight();
        
        BufferedImage image3 = new BufferedImage(width, height, TYPE_INT_RGB);
        
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

    // sprowadzenie obrazka ze skali szarości do macierzy punktów z współrzędnymi i wartością punktu
    public static PointsMatrix grey_scale_image_to_point_matrix(BufferedImage image, int backgroundColor, int threshold) {
        int width;
        int height;
        width = image.getWidth();
        height = image.getHeight();
        
        PointsMatrix matrix = new PointsMatrix(width, height);
        
        int pom = 0;
        int pixelColor = 0;
        int count = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Color c = new Color(image.getRGB(i, j));
                int red = (int) c.getRed();
                int green = (int) c.getGreen();
                int blue = (int) c.getBlue();
                if (red == green && red == blue) {
                    pixelColor = (int) c.getRed();
                    count++;
                    if (backgroundColor == 255) {
                        if (pixelColor > threshold) {
                            pom = 255;
                        } else {
                            pom = 0;
                        }
                    } else {
                        if (pixelColor > threshold) {
                            pom = 0;
                        } else {
                            pom = 255;
                        }
                    }

                    matrix.matrix[i][j].p = pom;
                    matrix.matrix[i][j].x = i;
                    matrix.matrix[i][j].y = j;

                }
            }

        }
        return matrix;
    }
    
    //kownersja macierzy punktów do obrazka
    public static BufferedImage point_matrix_to_image(PointsMatrix matrix) {
        int width = matrix.width;
        int height = matrix.height;
        
        BufferedImage image = new BufferedImage(width, height, TYPE_INT_RGB);
        Color c;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = matrix.matrix[i][j].p;
                c = new Color(color, color, color);
                image.setRGB(i, j, c.getRGB());
            }
        }
        return image;

    }

}
