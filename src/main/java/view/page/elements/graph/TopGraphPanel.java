package view.page.elements.graph;

import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import view.page.PageManager;
import view.page.elements.elementview.GraphLineWiew2;
import entity.Table;

public class TopGraphPanel extends RootGraphPanel implements ModifyGraphPanel {

	private static final long serialVersionUID = 1L;

	public TopGraphPanel(Table table) {
		super(table);
	}

	public void putView(Table table) { 
		PageManager.tableLineView.put(table, new GraphLineWiew2(table));
	}
	
	@Override
	public void movePoint(int pos, int newX, int newY) {

		double[] tRow = new double[2];
		tRow[0] = rowToTable(newX - getLineView().getDiameter(1) / 2);
		tRow[1] = colToTable(newY - getLineView().getDiameter(1) / 2);

		table.updRow(pos, tRow);
	}

	@Override
	public int searchPoint(MouseEvent e) {
		if (points != null) {
			int i = 0;
			Point2D p = e.getPoint();
			for(LinkedList<Ellipse2D> point : points.values())
			for (Ellipse2D t : point) {
				if (t.contains(p)) {
					return i;
				}
				i++;
			}
		}
		return -1;
	}

	private double colToTable(double val) {
		return ymin + (val / colMultiply);
	}

	private double rowToTable(double val) {
		return xmin + (val / rowMultiply);
	}

}
