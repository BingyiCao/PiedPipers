package piedpipers.dumb;

import java.util.*;

import piedpipers.sim.Point;

public class Player extends piedpipers.sim.Player {
	static double dimension =100.00;
	static boolean hastarget = false;
	static double pspeed = 0.49;
	static Point gate = new Point(50,50);
	
    public void init() {
    	
    }
    
    static double distance(Point a, Point b) {
        return Math.sqrt((a.x-b.x) * (a.x-b.x) +
                         (a.y-b.y) * (a.y-b.y));
    }
    
    // Return: the next position
    // my position: dogs[id-1]
    
    public Point move(Point[] pipers, // positions of dogs
                      Point[] rats) { // positions of the sheeps
        Point current = pipers[id];
        
        double ox = 0, oy = 0;
        Point target = new Point();
        
        if (getSide(current) == 0) {
        	
        	this.music = false;
        	
        	
            double dist = distance(current, target);
            assert dist > 0;
         
            ox = (gate.x-current.x) / dist * pspeed;
            oy = (gate.y-current.y) / dist * pspeed;
            System.out.println("left, try to enter the right");
        
        }
        else {
        	if (!hastarget){
        	this.music = true;
        	for (int i=0; i<rats.length/pipers.length; i++) {
        		if (getSide(rats[i*rats.length+id])==1) {
        			target = rats[i];
        			hastarget = true;
        			break;
        		}
        	}
        	System.out.println("should has a target by now");
        	}
        	else {
        		if (getSide(target)==1) {
                	if (distance(current, target) <= 2) {
                		double dist = distance(current, gate);
                		ox = (gate.x-current.x) / dist * pspeed;
                        oy = (gate.y-current.y) / dist * pspeed;
                        System.out.println("part 3 now");
                	} else if (distance(current, target) > 2){
                		ox = (target.x-current.x) / distance(target, current) * pspeed;
                        oy = (target.y-current.y) / distance(target, current)* pspeed;
                        System.out.println("part 4 now");
                	}
                	} else {
                		hastarget = false;
                		System.out.println("part 5 now");
                	}
        	}
        }
        
        current.x += ox;
        current.y += oy;
        		
        return current;
    }

    
    int getSide(double x, double y) {
        if (x < dimension * 0.5)
            return 0;
        else if (x > dimension * 0.5)
            return 1;
        else
            return 2;
    }
    int getSide(Point p) {
        return getSide(p.x, p.y);
    }
    
    
}
