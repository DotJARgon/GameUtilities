package collision;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpatialHasher<T extends Spatial> {
	private final int MAX_DIST;
	private final Hash hash;
	private final HashMap<Grid, ArrayList<T>> map;
	
	public SpatialHasher(int MAX_DIST, Hash hash) {
		this.MAX_DIST = MAX_DIST*2;
		this.map = new HashMap<>();
		this.hash = hash;
	}
	public SpatialHasher(int MAX_DIST) {
		this.MAX_DIST = MAX_DIST*2;
		this.map = new HashMap<>();
		this.hash = (x, y) -> x^y;
	}
	
	public void checkCollisions(List<T> items) {
		map.clear();
		for(int i = 0; i < items.size(); i++) {
			Grid g = new Grid(items.get(i).getX()/MAX_DIST, items.get(i).getY()/MAX_DIST);
			if(!map.containsKey(g)) map.put(g, new ArrayList<>());
			map.get(g).add(items.get(i));
		}
		
		for(Grid g : map.keySet()) {
			List<T> list = map.get(g);
			for(int i = 0; i < list.size(); i++)
				for(int j = 0; j < list.size(); j++)
					if(list.get(i) != list.get(j)) 
						if(list.get(i).collides(list.get(j))) 
							list.get(i).collideWith(list.get(j));
			
			Grid grid = g.left();
			List<T> l = map.get(grid);
			if(map.containsKey(grid)) 
				for(int i = 0; i < list.size(); i++)
					for(int j = 0; j < l.size(); j++)
						if(list.get(i) != l.get(j)) 
							if(list.get(i).collides(l.get(j))) 
								list.get(i).collideWith(l.get(j));
			grid = g.right();
			l = map.get(grid);
			if(map.containsKey(grid)) 
				for(int i = 0; i < list.size(); i++)
					for(int j = 0; j < l.size(); j++)
						if(list.get(i) != l.get(j)) 
							if(list.get(i).collides(l.get(j))) 
								list.get(i).collideWith(l.get(j));
			grid = g.bottom();
			l = map.get(grid);
			if(map.containsKey(grid)) 
				for(int i = 0; i < list.size(); i++)
					for(int j = 0; j < l.size(); j++)
						if(list.get(i) != l.get(j)) 
							if(list.get(i).collides(l.get(j))) 
								list.get(i).collideWith(l.get(j));
			grid = g.top();
			l = map.get(grid);
			if(map.containsKey(grid)) 
				for(int i = 0; i < list.size(); i++)
					for(int j = 0; j < l.size(); j++)
						if(list.get(i) != l.get(j)) 
							if(list.get(i).collides(l.get(j))) 
								list.get(i).collideWith(l.get(j));
			grid = g.topleft();
			l = map.get(grid);
			if(map.containsKey(grid)) 
				for(int i = 0; i < list.size(); i++)
					for(int j = 0; j < l.size(); j++)
						if(list.get(i) != l.get(j)) 
							if(list.get(i).collides(l.get(j))) 
								list.get(i).collideWith(l.get(j));
			grid = g.topright();
			l = map.get(grid);
			if(map.containsKey(grid)) 
				for(int i = 0; i < list.size(); i++)
					for(int j = 0; j < l.size(); j++)
						if(list.get(i) != l.get(j)) 
							if(list.get(i).collides(l.get(j))) 
								list.get(i).collideWith(l.get(j));
			grid = g.bottomright();
			l = map.get(grid);
			if(map.containsKey(grid)) 
				for(int i = 0; i < list.size(); i++)
					for(int j = 0; j < l.size(); j++)
						if(list.get(i) != l.get(j)) 
							if(list.get(i).collides(l.get(j))) 
								list.get(i).collideWith(l.get(j));
			grid = g.bottomleft();
			l = map.get(grid);
			if(map.containsKey(grid)) 
				for(int i = 0; i < list.size(); i++)
					for(int j = 0; j < l.size(); j++)
						if(list.get(i) != l.get(j)) 
							if(list.get(i).collides(l.get(j))) 
								list.get(i).collideWith(l.get(j));
		}
		
	}
	
	private final class Grid {
		protected final int x, y;
		private Grid(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(Object o) {
			if(o instanceof SpatialHasher.Grid) return equals((SpatialHasher.Grid) o);
			return super.equals(o);
		}
		public boolean equals(SpatialHasher.Grid g) {
			return x == g.x && y == g.y;
		}
		public int hashCode() {
			return hash.hash(x, y);
		}
		private Grid left() {return new Grid(x-1, y);}
		private Grid right() {return new Grid(x+1, y);}
		
		private Grid top() {return new Grid(x, y-1);}
		private Grid bottom() {return new Grid(x, y+1);}
		
		private Grid topleft() {return new Grid(x-1, y-1);}
		private Grid topright() {return new Grid(x+1, y-1);}
		private Grid bottomleft() {return new Grid(x-1, y+1);}
		private Grid bottomright() {return new Grid(x+1, y+1);}
		public String toString() {
			return "("+x+", "+y+")";
		}
	}
	
	private interface Hash {
		public int hash(int x, int y);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		
		for(Grid grid : map.keySet()) {
			g.drawRect(grid.x*MAX_DIST, grid.y*MAX_DIST, MAX_DIST, MAX_DIST);
		}
	}
}
