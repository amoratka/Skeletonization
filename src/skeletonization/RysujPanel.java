package skeletonization;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
    import javax.swing.JPanel;

class RysujPanel extends JPanel {
    BufferedImage image;
    
    public RysujPanel() {
		super();

           //do otwierania automatycznieobrazka     
               File imageFile;
    imageFile = new File("Obraz.bmp"  );
		try {
                    image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("Blad odczytu obrazka");
		}
                 /*
		File imageFile = new File("java.jpg");
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("Blad odczytu obrazka");
			e.printStackTrace();
                }
*/
		Dimension dimension = new Dimension(10000,100000);
		setPreferredSize(dimension);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, this);
	}}
