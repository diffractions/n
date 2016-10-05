package view.page.elements.elementview;

import java.awt.Color;
import java.awt.Graphics;

import entity.Table; 

public class GraphLineWiew2 extends GraphLineWiew1 {

	public GraphLineWiew2(Table table) {

		super(table);

	}

	int droppPoint = 1;

	@Override
	public int getDropPoint() {
		return droppPoint;
	}

	@Override
	public void setDropPoint(int i) {
		droppPoint = i;
	}
 
	
	@Override
	public void setDefaultDiameters(int count) { 
		for (int i = 0; i < count; i++) { 
			diameter.add(15.0);
		} 
	}
 
	
	@Override
	public void paintPoint(Graphics g, int col) {
		g.setColor(Color.RED);
	}
	
}
