package view.page.elements.graph;

import java.awt.event.MouseEvent;

public interface ModifyGraphPanel extends GraphPanel {
	public void movePoint(int pos, int newX, int newY);

	public int searchPoint(MouseEvent e);
}
