package collision;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tester {

	public static void main(String[] args) {
		SpatialHasher<Circle> hash = new SpatialHasher<>(40);
		ArrayList<Circle> c = new ArrayList<>();
		for(int i = 0; i < 500; i++) c.add(new Circle(Math.random()*700, Math.random()*700, Math.random()*15+5));
		hash.checkCollisions(c);
//		for(Circle c0 : c) for(Circle c1 : c) if(c0 != c1) if(c0.collides(c1)) c0.collideWith(c1);
		
		JFrame j = new JFrame("Testing window");
		j.setSize(new Dimension(800, 800));
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
		
		j.add(new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				for(Circle c : c) c.draw(g);
				hash.draw(g);
			}
		});
	}

}
