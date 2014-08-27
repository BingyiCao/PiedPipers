package piedpipers.dumb;

import java.util.*;

import piedpipers.sim.Point;

public class Player extends piedpipers.sim.Player {
	static double dimension = 100.00;
	static boolean hastarget = false;
	static boolean enoughclose = false;
	static double pspeed = 0.49;
	static double mpspeed = 0.09;
	static Point gate = new Point(50, 50);
	static Point target = new Point();

	public void init() {

	}

	static double distance(Point a, Point b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	// Return: the next position
	// my position: dogs[id-1]

	public Point move(Point[] pipers, // positions of dogs
			Point[] rats) { // positions of the sheeps
		Point current = pipers[id];
		double ox = 0, oy = 0;

		if (getSide(current) == 0 && !hastarget) {
			this.music = false;
			double dist = distance(current, gate);
			assert dist > 0;
			ox = (gate.x - current.x) / dist * pspeed;
			oy = (gate.y - current.y) / dist * pspeed;
			System.out.println("move toward the right side");
		} else {
			if (!hastarget) {
				this.music = false;
				for (int i = 0; i < rats.length / pipers.length; i++) {
					if (getSide(rats[i * pipers.length + id]) == 1) {
						target = rats[i * pipers.length + id];
						hastarget = true;
						break;
					}
				}
			} else {
				if (getSide(target) == 0) {
					hastarget = false;
					System.out
							.println("This target is on the left, a new target needed!");
				}
				if (getSide(target) == 1) {
					Point behindtarget = new Point(target.x + 5
							/ distance(target, gate) * (target.x - 50),
							target.y + 5 / distance(target, gate)
									* (target.y - 50));
					if (!enoughclose && distance(current, behindtarget) > 5) {
						this.music = false;
						double dist = distance(current, behindtarget);
						ox = (behindtarget.x - current.x) / dist * mpspeed;
						oy = (behindtarget.y - current.y) / dist * mpspeed;
						System.out.println("move to wards behind rat position");
					} else {
						this.music = true;
						enoughclose = true;
						if (getSide(current) == 1) {
							double dist = distance(current, gate);
							ox = (gate.x - current.x) / dist * mpspeed;
							oy = (gate.y - current.y) / dist * mpspeed;
							System.out.println("push towards gate");
						} else if (current.x > 45 && this.music) {
							Point tmp = new Point(40, 50);
							double dist = distance(current, tmp);
							ox = (tmp.x - current.x) / dist * mpspeed;
							oy = (tmp.y - current.y) / dist * mpspeed;
							System.out.println("push a little bit more inside");
						} else {
							this.music = false;
							hastarget = false;
							enoughclose = false;
							double dist = distance(current, gate);
							ox = (gate.x - current.x) / dist * pspeed;
							oy = (gate.y - current.y) / dist * pspeed;
							System.out.println("heading back");
						}
					}
				} else {
					this.music = false;
					hastarget = false;
					enoughclose = false;
					ox = (target.x - current.x) / distance(target, current)
							* pspeed;
					oy = (target.y - current.y) / distance(target, current)
							* pspeed;
					System.out
							.println("In the last else statement, move towards target");
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
