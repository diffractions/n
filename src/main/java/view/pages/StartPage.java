package view.pages;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;

//import view.AbstractViewCreator;
import view.ViewCreator;
import view.elements.GraphViewCreator;
import view.elements.TableViewCreator;
import entity.Table;

public class StartPage /*extends AbstractViewCreator*/ implements ViewCreator {

	private TableViewCreator table_s;
	private GraphViewCreator graph_s;

	// public StartPage(Container cont) {
	// super(cont);
	// this.table_s = new TableViewCreator(cont);
	// this.graph_s = new GraphViewCreator(cont);
	// }

	public StartPage() {
//		this.table_s = new TableViewCreator(cont);
//		this.graph_s = new GraphViewCreator(cont);
		this.table_s = new TableViewCreator( );
		this.graph_s = new GraphViewCreator();
	}

	@Override
	public Container getView(Table table) {
		// public Container getView(int width, int height, Table table) {
		// cont.removeAll();
		// cont.setLayout(new GridLayout(1, 2));

		// cont.add(this.table_s.getView(cont.getWidth() / 2, cont.getHeight(),
		// table));
		// cont.add(this.graph_s.getView(cont.getWidth() / 2, cont.getHeight(),
		// table));

		// cont.add(this.table_s.getView(table));
		// cont.add(this.graph_s.getView(table));

		// cont.revalidate();
		// return cont;

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(this.table_s.getView(table));
		p.add(this.graph_s.getView(table));
		return p;

	}

//	@Override
//	public Container getView(int width, int height, Table table) { 
//		return null;
//	}

}
