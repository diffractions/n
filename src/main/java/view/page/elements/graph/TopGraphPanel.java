package view.page.elements.graph;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import entity.Table;

public class TopGraphPanel extends RootGraphPanel implements ModifyGraphPanel {

	private static final long serialVersionUID = 1L;

	private LinkedList<Ellipse2D> points;

	public TopGraphPanel(Table table) {
		super(table);
	}

	@Override
	public void movePoint(int pos, int newX, int newY) {

		double[] tRow = new double[2];
		tRow[0] = rowToTable(newX - getLineView().getDiameter(1) / 2);
		tRow[1] = colToTable(newY - getLineView().getDiameter(1) / 2);

		table.updRow(pos, tRow);
	}

	@Override
	public void paintComponent(Graphics g) {

		points = new LinkedList<Ellipse2D>();
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		for (Ellipse2D e : points) {
			g2.draw(e);
		}
	}

	@Override
	public void drawPoint(Graphics g, double[][] tableToDraw, int collNumber,
			int rowNumber) {

		points.add(new Ellipse2D.Double(Math.round(xPosition
				- getLineView().getMaxRadius() + tableToDraw[rowNumber][0]),
				Math.round(yPosition - getLineView().getMaxRadius()
						+ tableToDraw[rowNumber][collNumber]), getLineView()
						.getDiameter(collNumber), getLineView().getDiameter(
						collNumber)));
	}

	@Override
	public int searchPoint(MouseEvent e) {
		if (points != null) {
			int i = 0;
			Point2D p = e.getPoint();
			for (Ellipse2D t : points) {
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
