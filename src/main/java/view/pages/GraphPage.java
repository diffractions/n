package view.pages;

import java.awt.GridLayout;

import javax.swing.JPanel;

import entity.Table;
import view.AbstractViewCreator;
import view.ViewCreator;
import view.elements.GraphViewCreator;

public class GraphPage extends AbstractViewCreator implements ViewCreator {

	private GraphViewCreator graph;

	public GraphPage() {
		this.graph = new GraphViewCreator();
		this.pageToView = new JPanel();
	}

	public void fillComponent(Table table) {
		pageToView.setLayout(new GridLayout(1, 1));
		pageToView.add(this.graph.getView(table));
	}

}
