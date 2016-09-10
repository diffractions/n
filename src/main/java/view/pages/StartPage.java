package view.pages;

import java.awt.Container;
import java.awt.GridLayout;

import view.AbstractViewCreator;
import view.ViewCreator;
import view.elements.GraphViewCreator;
import view.elements.TableViewCreator;
import entity.Table;

public class StartPage extends AbstractViewCreator implements ViewCreator {

	private TableViewCreator table;
	private GraphViewCreator graph;

	public StartPage(Container cont) {
		super(cont);
		this.table = new TableViewCreator(cont);
		this.graph = new GraphViewCreator(cont);
	}

	@Override
	public Container getView(int width, int height, Table table) {
		cont.removeAll();
		cont.setLayout(new GridLayout(1, 2));

		cont.add(this.table.getView(cont.getWidth() / 2, cont.getHeight(),
				table));
		cont.add(this.graph.getView(cont.getWidth() / 2, cont.getHeight(),
				table));

		cont.revalidate();
		return cont;
	};

}
