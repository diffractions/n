package view.pages;
 
import java.awt.GridLayout;
 

import javax.swing.JComponent;
import javax.swing.JPanel; 

import view.AbstractViewCreator;
import view.ViewCreator;
import view.elements.GraphViewCreator;
import view.elements.TableViewCreator;
import entity.Table;

public class StartPage extends AbstractViewCreator implements ViewCreator {

	private TableViewCreator table;
	private GraphViewCreator graph;

	public StartPage(int x, int y) {
		super(x, y);
		this.table = new TableViewCreator(x / 2, y);
		this.graph = new GraphViewCreator(x / 2, y);
	};

	@Override
	public JPanel getView(Table table) {
		JPanel startPage = new JPanel();
		startPage.setSize(x, y);
		startPage.setLayout(new GridLayout(1, 2));

		JComponent tables = this.table.getView(table);
		JComponent graph = this.graph.getView(table);

		startPage.add(tables);
		startPage.add(graph);
		startPage.setVisible(true);

		return startPage;
	}

}
