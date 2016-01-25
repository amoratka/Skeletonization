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
                
               /* ElementsContainer structuralElementContainer;
                structuralElementContainer = new ElementsContainer();
                make_structural_element_container(structuralElementContainer);
                //print_container(s);
                Element dozamiany;
                dozamiany=structuralElementContainer.container.get(0);
                dozamiany=left90(dozamiany);
                structuralElementContainer.container.set(0, dozamiany);
                
                Element dozamiany2;
                dozamiany2=structuralElementContainer.container.get(1);
                dozamiany2=left90(dozamiany2);
                structuralElementContainer.container.set(1, dozamiany2);
                
                print_container(structuralElementContainer);
                ElementsContainer imageElementContainer;
                imageElementContainer = new ElementsContainer();
                
                */
                
                
                Wyświetlacz w = null;
                try {
                    w = new Wyświetlacz();
                } catch (IOException ex) {
                    Logger.getLogger(Skeletonization.class.getName()).log(Level.SEVERE, null, ex);
                }
                w.setVisible(true);
            }

            

            

        });

    }
    
}
