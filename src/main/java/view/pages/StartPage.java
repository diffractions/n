package view.pages;

import java.awt.GridLayout;

import javax.swing.JPanel;

import view.AbstractViewCreator;
import view.ViewCreator;
import view.elements.GraphViewCreator;
import view.elements.TableViewCreator;
import entity.Table;

public class StartPage extends AbstractViewCreator implements ViewCreator {

	private TableViewCreator table;
	private GraphViewCreator graph;

	public StartPage() {
		this.table = new TableViewCreator();
		this.graph = new GraphViewCreator();
		this.pageToView = new JPanel();
	}

	@Override
	public void fillComponent(Table table) {
		pageToView.setLayout(new GridLayout(1, 2));
		pageToView.add(this.table.getView(table));
		pageToView.add(this.graph.getView(table));
	}

}
