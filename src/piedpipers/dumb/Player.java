package piedpipers.dumb;

import java.util.*;

import piedpipers.sim.Point;

public class Player extends piedpipers.sim.Player {

	
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
        Point target = new Point(50,50);
        double ox = 0, oy = 0;
        
        if (current.x <50) {
        	
        	this.music = false;
        	double pspeed = 0.49;
        	
            double dist = distance(current, target);
            assert dist > 0;
         
            ox = (target.x-current.x) / dist * pspeed;
            oy = (target.y-current.y) / dist * pspeed;
        
        }
        else {
        	this.music = true;
        	target = randomPosition(1);
        	double pspeed = 0.09;
        	double dist = distance(current, target);
            assert dist > 0;
         
            ox = (target.x-current.x) / dist * pspeed;
            oy = (target.y-current.y) / dist * pspeed;
        }
        current.x += ox;
        current.y += oy;
        		
        return current;
    }

    static Point randomPosition(int side) {
        Point point = new Point();
        // generate [0-50)
        point.x = random.nextDouble() * 100 * 0.5;
        // generate [50-100)
        if (side == 1)
            point.x  = point.x + (100 * 0.5);
        // generate [0-100)
        point.y = random.nextDouble() * 100;
        return point;
    }
    static Random random = new Random();
    
}
