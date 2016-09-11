package view.elements;

import javax.swing.JComponent;
import javax.swing.JPanel;

import view.ViewCreator;
import entity.Table;

public class GraphViewCreator implements ViewCreator {

	@Override
	public JComponent getView(Table table) {
		if (table != null) {
			JPanel graph = new GraphPanel(table);
			paintAxis(table);
			return graph;
		}
		return null;
	}

	private void paintAxis(Table table) {

	}

}
