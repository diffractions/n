package view.page.elements.elementview;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import entity.Table; 

public class GraphLineWiew1 implements GraphLineView {

	protected ArrayList<Double> diameter = null;

	
	
	public GraphLineWiew1(Table table) {

		diameter = new ArrayList<>();

		setDefaultDiameters(table.getCollCount()); 
	}

	 
	
	@Override
	public void setDefaultDiameters(int count) { 
		for (int i = 0; i < count; i++) { 
			diameter.add(10.0);
		}
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
		g.setColor(Color.BLUE);
	}

	@Override
	public double getDiameter(int i) {
//		System.out.print(diameter.get(i));
		return diameter.get(i);
	}

	@Override
	public void update(Table table) {
		setUpdate(true);

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


	int droppPoint = 1;

	@Override
	public int getDropPoint(){
		return droppPoint;
	}

	@Override
	public void setDropPoint(int i){
		setUpdate(true);
		 droppPoint = i;
	}

	@Override
	public void setDiameter(int col, double diameter) {
		setUpdate(true);
		this.diameter.set(col, diameter);
		
	}


	boolean update = true;
	public boolean getUpdate (){return update;};
	public void setUpdate (boolean update){this.update =update;};
}
