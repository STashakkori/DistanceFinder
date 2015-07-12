/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DistanceFinder;

import java.util.Comparator;

/**
 *
 * @author sina
 */
public class Point {
    
    private int x;
    private int y;
    
    public Point(int x, int y){
    	this.x = x;
    	this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public static class CompareByX implements Comparator<Point>
    {
        public int compare(Point p1, Point p2)
        {
            int p1x = p1.getX();
            int p2x = p2.getX();
            if(p1x < p2x) {
                return -1;
            } else if(p1x > p2x) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    public static class CompareByY implements Comparator<Point>
    {
        public int compare(Point p1, Point p2)
        {
            int p1y = p1.getY();
            int p2y = p2.getY();
            if(p1y < p2y) {
                return -1;
            } else if(p1y > p2y) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    
    public String toString(){
		return "(" + x + "," + y + ")";
    	
    }
}
