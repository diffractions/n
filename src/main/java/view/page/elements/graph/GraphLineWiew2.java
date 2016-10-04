package view.page.elements.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import entity.Table; 

public class GraphLineWiew2 implements GraphLineView {

	private ArrayList<Double> diameter = null;

	
	
	public GraphLineWiew2(Table table) {

		diameter = new ArrayList<>();

		for (int i = 0; i < table.getCollCount(); i++) {
			// System.out.println("i = " + i);
			diameter.add((double) 15);
		}
//		System.out.println("C = " + diameter);
	}

	@Override
	public double getMaxRadius() {

		double ret = 0;
		for (int i = 0; i < diameter.size(); i++) {
			ret = getDiameter(i) > ret ? getDiameter(i) : ret;
		}

		return (ret / 2);
	}

	@Override
	public void paintPoint(Graphics g, int col) {
		g.setColor(Color.RED);
	}

	@Override
	public double getDiameter(int i) {
		return diameter.get(i);
	}

	@Override
	public void update(Table table) {

		if (diameter.size() < table.getCollCount()) {
			int add = table.getCollCount() - diameter.size();
			for (int i = 0; i < add; i++) {
				diameter.add((double) 10);
			}

		} else if (diameter.size() > table.getCollCount()) {
			int rem = diameter.size() - table.getCollCount();
			for (int i = 0; i < rem; i++) {
				diameter.remove(diameter.size() - 1);

			}

//			System.out.println("h = " + diameter);

		}

	}

	@Override
	public int getDropPoint(){
		return 1;
	}

	@Override
	public void setDiameter(int col, double diameter) {
		// TODO Auto-generated method stub
		
	}
}
