
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DistanceFinder;

import java.util.*;
/**
 *
 * @author sina
 */
public class DistanceFinder {

    Point[] P;  // Initial array of points
    Point[] X;    // Array of xcoords
    Point[] XL;	// Array of first half of xcoords
    Point[] XR;	// Array of second half of ycoords
    Point[] Y;    // Array of y coords
    Point[] YL;	// Array of first half of xcoords 
    Point[] YR;	// Array of second half of ycoords
    
    // Constructor
    public DistanceFinder(int size){
        P = new Point[size];
        X = new Point[size];
        Y = new Point[size];
        MersenneTwister rand = new MersenneTwister();
              
        for(int i=0; i<size;i++){
        	P[i] = new Point(rand.nextInt(),rand.nextInt());
            System.out.println("X: " + P[i].getX());
            System.out.println("Y: " + P[i].getY());
            X[i] = P[i]; // add xcoords from P to X
            Y[i] = P[i]; // add ycoords from P to Y
        }
        
        // sort the Point array P while adding appropriately to subarrays
        Arrays.sort(X,new Point.CompareByX());
        Arrays.sort(Y,new Point.CompareByY());
    }
    
    public Point[] findDistance(Point[] X, Point[] Y){
    	int distance1 = 0;	
    	int distance2 = 0;
    	
    	// Base case
    	if(X.length <= 3 || Y.length <= 3){
    		Point first = null;
    		Point second = null;
    		Point[] points;
    		distance1 = distanceFormula(X[0].getX(),X[1].getX(),Y[0].getY(),Y[1].getY());
    		if(X.length == 3) distance2 = distanceFormula(X[1].getX(),X[2].getX(),Y[1].getY(),Y[2].getY());
    		if(distance1 > distance2) {points = new Point[]{X[0],X[1]};}
    		else if(distance1 < distance2) {points = new Point[]{X[1],X[2]};}
    		else {points = new Point[]{X[0],X[1]};}
		return points;
    	}
    	
	    	// Divide and conquer segment	
	    	XL = new Point[X.length/2]; // create an xcoord array that is the first half of the input xcoord array
	    	XR = new Point[X.length - X.length/2]; // create a xcoord array that is the second half of the input xcoord array
	    	YL = new Point[Y.length/2]; // create an xcoord array that is the first half of the input xcoord array
	    	YR = new Point[Y.length - Y.length/2]; // create an xcoord array that is the first half of the input xcoord array
	    	
	    	for(int i = 0; i<X.length/2; i++){
	    		XL[i]=X[i];	// add points to XL array that
	    	}
	    	for(int i = 0; i<Y.length/2; i++){
	    		YL[i]=Y[i]; //	
	    	} 
	    	for(int i = 0; i<(X.length - X.length/2); i++){
	    		XR[i]=X[i+X.length/2];	// 
	    	} 
	    	for(int i = 0; i<(Y.length - Y.length/2); i++){
	    		YR[i]=Y[i+Y.length/2];	//	
	    	} 
	    	Point[] closestL = findDistance(XL,YL);
	    	Point[] closestR = findDistance(XR,YR);
	    	int closestLDistance = distanceFormula(closestL[0].getX(),closestL[1].getX(),closestL[0].getY(),closestL[1].getY());
	    	int closestRDistance = distanceFormula(closestR[0].getX(),closestR[1].getX(),closestR[0].getY(),closestR[1].getY());
	    	Point[] delta = new Point[2];
	    	int deltaDistance = 0;
	    	if(closestLDistance < closestRDistance){
	    		delta = closestL;
	    		deltaDistance = closestLDistance;
	    	}
	    	else {
	    		delta = closestR;
	    		deltaDistance = closestRDistance;
	    	}
	    	
	    	int deltaMid = X[X.length/2].getX();
	    	int deltaLeftBound = deltaMid - deltaDistance;
	    	int deltaRightBound = deltaMid + deltaDistance; 
	    
	    	ArrayList<Point> YPrime = new ArrayList<Point>(); 
	    	for(int i = 0; i < X.length;i++){
	    		if(X[i].getX() >= deltaLeftBound && X[i].getX() <= deltaRightBound && !X[i].equals(delta)){
	    			YPrime.add(X[i]);
	    		}
	    	} 	
	    	
	    	int newTempDistance = 0;
	    	int oldTempDistance = deltaDistance;
	    	Point[] temp = new Point[2];
	    	
	    	for(Point p : delta){
	    		System.out.println("Delta Points: " + p);
	    	}
	    	
	    	if(YPrime.size() == 0) return delta;
	    	for(Point p : YPrime){
	    		System.out.println("YPrime Array Point: " + p);
	    	}
	    	
	    	for(int i = 0; i < YPrime.size(); i++){
	    		for(int j = 0; j < YPrime.size(); j++){
	    			if(i != j){
		    			newTempDistance = distanceFormula(YPrime.get(i).getX(),YPrime.get(j).getX(), YPrime.get(i).getY(), YPrime.get(j).getY());
		    			if(newTempDistance < oldTempDistance){
		    				temp[0] = YPrime.get(i);
			        		temp[1] = YPrime.get(j);
			        		oldTempDistance = newTempDistance;
		    			}
	    			}
	    		}
	    	}
	    	if(temp==null){
	    		for(Point p : temp){
		    		System.out.println("Temp Array Point: " + p);
		    	}
	    		return temp;
	    	}
	    	else {
	    		return delta;
	    	}
    }
    
    public int distanceFormula(int X1, int X2, int Y1, int Y2){
    	return (int) Math.sqrt((Math.pow(X2 - X1,2) + Math.pow(Y2 - Y1,2)));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DistanceFinder d = new DistanceFinder(10);
        Point[] answer = d.findDistance(d.X,d.Y);
        System.out.println("-----------------------------");
        for(Point p: answer){
        	System.out.println("Answer: " + p);
        }
    }
  
}
