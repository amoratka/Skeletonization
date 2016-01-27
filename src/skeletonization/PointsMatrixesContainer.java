/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skeletonization;

import static skeletonization.PointsMatrix.print_pointsmatrix;

/**
 *
 * @author Kamila
 */
public class PointsMatrixesContainer {

    
    PointsMatrix[][] tab;
    int width;
    int height;

    public PointsMatrixesContainer(int width, int height) {
        this.tab = new PointsMatrix[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.tab[i][j] = new PointsMatrix(3, 3);
            }
        }

        this.width = width;
        this.height = height;
    }

    
    //wypisanie kontenera ( wszystkich elementów strukturalnych )
    public static void print_container(PointsMatrixesContainer sec) {
        for (int i = 0; i < sec.width; i++) {

            for (int j = 0; j < sec.height; j++) {
                System.out.println(i+" "+j);
                print_pointsmatrix(sec.tab[i][j]);
            }
        }
        System.out.println();
    }

   

   
     
}
