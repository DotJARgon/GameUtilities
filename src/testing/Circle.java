package testing;

import java.awt.Color;
import java.awt.Graphics;

import collision.Spatial;

public class Circle implements Spatial<Circle> {
	public double x, y, r;
	boolean collided = false;
	public Circle(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	@Override
	public int getX() {
		return (int) x;
	}

	@Override
	public int getY() {
		return (int) y;
	}

	@Override
	public boolean collides(Circle c) {
		return (x-c.x)*(x-c.x)+(y-c.y)*(y-c.y) <= (r+c.r)*(r+c.r);
	}

	@Override
	public void collideWith(Circle t) {
		collided = true;
	}

	public void reset() {
		collided = true;
	}
	
	public void draw(Graphics g) {
		g.setColor(collided ? Color.GREEN : Color.RED);
		g.fillOval((int) (x-r), (int) (y-r), (int) (r*2), (int) (r*2));
	}
	
}
