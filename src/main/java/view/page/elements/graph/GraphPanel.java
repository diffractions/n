package view.page.elements.graph;

import java.awt.Dimension;
import java.awt.Graphics;

import entity.Table; 

public interface GraphPanel {


	public void drawPoint(Graphics g, double[][] tableToDraw, int collNumber,
			int rowNumber);

	public Table getTable();

	public void setPosition(Dimension position);

	public void setSize(Dimension panelSize);

	public void paintComponent(Graphics g);

	public void setXdif(double xdif);

	public void setYdif(double ydif);

	public void setXmin(double xmin);

	public void setYmin(double ymin);
	
	public GraphLineView getLineView();
}
