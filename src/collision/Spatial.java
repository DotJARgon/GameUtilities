package collision;

public interface Spatial<T extends Spatial> {
	public int getX();
	public int getY();
	
	public boolean collides(T t);
	public void collideWith(T t);
}
