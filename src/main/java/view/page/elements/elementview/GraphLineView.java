package view.page.elements.elementview;

import java.awt.Graphics;

import entity.Table;

public interface GraphLineView{

	public double getMaxRadius();

	public double getDiameter(int i);

	public void paintPoint(Graphics g, int col);

	public void update(Table key);

	public int getDropPoint();

	public void setDropPoint(int i);

	public void setDiameter(int col, double diameter);

	public void setDefaultDiameters(int count);

	public boolean getUpdate ();

	public void setUpdate (boolean update);

}
