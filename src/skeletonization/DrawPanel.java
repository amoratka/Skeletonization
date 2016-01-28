package skeletonization;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;

class DrawPanel extends JPanel {

    BufferedImage image;
    int width;
    int height;

    public DrawPanel() throws IOException {
        super();

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
