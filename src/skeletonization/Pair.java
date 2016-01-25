/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skeletonization;

/**
 *
 * @author Kamila
 */
public class Pair {

    PointsMatrix image;
    boolean change;

    public Pair(int w, int h) {
        this.image = new PointsMatrix(w, h);
        this.change = true;

    }
}
