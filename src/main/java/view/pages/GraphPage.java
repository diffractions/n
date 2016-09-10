package view.pages;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JComponent;

import entity.Table;
import view.AbstractViewCreator;
import view.ViewCreator;
import view.elements.GraphViewCreator;

public class GraphPage extends AbstractViewCreator implements ViewCreator {

	public GraphPage(Container cont) {
		super(cont);
		this.graph = new GraphViewCreator(cont);
	}

	private GraphViewCreator graph;

	@Override
	public Container getView(int width, int height, Table table) {
		cont.removeAll();
		cont.setLayout(new GridLayout(1, 1));
		cont.add(this.graph.getView(width, width, table));
		cont.revalidate();
		return cont;
	}

}
