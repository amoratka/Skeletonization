/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skeletonization;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kamila
 */
public class Skeletonization {

    public static void main(String[] args) throws IOException, InterruptedException {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                GUI w = null;
                try {
                    w = new GUI();
                } catch (IOException ex) {
                    Logger.getLogger(Skeletonization.class.getName()).log(Level.SEVERE, null, ex);
                }
                w.setVisible(true);
            }

        });

    }

}
