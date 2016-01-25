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
public class myPoint {
    int x;
    int y;
    int p;
    
    
    public myPoint (int x, int y, int p){
        this.x=x;
        this.y=y;
        this.p=p;
        
    }
    public myPoint (int x, int y){
        this.x=x;
        this.y=y;
        this.p=0;
        
    }
    public myPoint (){
       this.x=0;
        this.y=0;
        this.p=0;
    }
}
