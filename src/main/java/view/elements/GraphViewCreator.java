package view.elements;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JPanel;

import view.AbstractViewCreator;
import view.ViewCreator;
import entity.Table;

public class GraphViewCreator extends AbstractViewCreator implements
		ViewCreator {

	public GraphViewCreator(Container cont) {
		super(cont);
	}

	@Override
	public JComponent getSizedView(int width, int height, Table table) {
		if (table != null) {
			JPanel graph = new TableGraphPanel(table);
			graph.setSize(width, height);
			paintAxis(table);
			return graph;
		}
		return null;
	}

	private void paintAxis(Table table) {

	}

}
