/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skeletonization;

import java.io.IOException;
import javax.swing.SwingUtilities;


/**
 *
 * @author Kamila
 */
public class Skeletonization {
public static void main(String[] args) throws IOException, InterruptedException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Wyświetlacz w = new Wyświetlacz();
                w.setVisible(true);
            }

        });

    }
    
}
