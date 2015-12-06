package skeletonization;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kamila
 */
public class Image {
    //private static Object BMPDecoder;
    
    /**
     *
     * @param f
     * @param image
     * @return
     */
        
    
    public static BufferedImage load_image( FileReader f, BufferedImage image) {
        File imageFile;
    imageFile = new File("first_test.bmp"  );
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("Blad odczytu obrazka");
		}

 /*
        // działa tylko dla bmp orginalnego, 32-bitowego
        /*BufferedImage image;
        image = null;
        image = ImageIO.read(new File("test3.bmp"));
                
        
        // create command

     
ImageInfo origInfo = new ImageInfo(absPath); //load image info
MagickImage image = new MagickImage(origInfo); //load image
image = image.scaleImage(finalWidth, finalHeight); //to Scale image
image.setFileName(absNewFilePath); //give new location
image.writeImage(origInfo); //save*/
        return image;
    }
    
    
}
