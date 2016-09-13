package view.page.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import view.page.AbstractPageViewCreator;
import view.page.ViewCreator;
import view.page.elements.GraphViewCreator;
import view.page.elements.TableViewCreator;
import entity.Table;

public class RedactorPanel extends AbstractPageViewCreator implements ViewCreator {

	private TableViewCreator table;
	private GraphViewCreator graph;

	public RedactorPanel() {
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
