package view.page.elements;

import javax.swing.JPanel;

import view.page.ViewCreator;
import entity.Table;

public class GraphViewCreator implements ViewCreator {

	@Override
	public JPanel getView(Table table) {
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
