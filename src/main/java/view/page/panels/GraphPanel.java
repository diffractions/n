package view.page.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import entity.Table;
import entity.ModifyTable;
import view.page.AbstractPageViewCreator;
import view.page.ViewCreator;
import view.page.elements.GraphViewCreator;

public class GraphPanel extends AbstractPageViewCreator implements ViewCreator {

	private GraphViewCreator graph;

	public GraphPanel() {
		this.graph = new GraphViewCreator();
		this.pageToView = new JPanel();
	}

	@Override
	public void fillComponent(Table table, ModifyTable... tables) {
		pageToView.setLayout(new GridLayout(1, 1));
		pageToView.add(this.graph.getView(table, tables));
	}

}
