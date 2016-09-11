package view.pages;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;

import entity.Table;
//import view.AbstractViewCreator;
import view.ViewCreator;
import view.elements.GraphViewCreator;

public class GraphPage /*extends AbstractViewCreator*/ implements ViewCreator {

	public GraphPage() {
		this.graph = new GraphViewCreator();
		this.p = new JPanel();
	}

	private JPanel p;
	private GraphViewCreator graph;

	@Override
	public Container getView(Table table) {
		p.removeAll();
		p.setLayout(new GridLayout(1, 1));
		p.add(this.graph.getView(table));
		p.revalidate();
		return p;
	}

}
