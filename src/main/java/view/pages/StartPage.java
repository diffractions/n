package view.pages;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.AbstractViewCreator;
import view.ViewCreator;
import view.elements.GraphViewCreator;
import view.elements.TableViewCreator;
import entity.Table;

public class StartPage extends AbstractViewCreator implements ViewCreator {

	private TableViewCreator table;
	private GraphViewCreator graph;
	JPanel startPage;

//	JFrame f = new JFrame("H2");
//	private TableViewCreator table2;
//	private GraphViewCreator graph2;
	
	public StartPage(int x, int y) {
		super(x, y);
		this.table = new TableViewCreator(x / 2, y);
		this.graph = new GraphViewCreator(x / 2, y);
		startPage = new JPanel();
		startPage.setLayout(new GridLayout(1, 2));

		
		
		
		
		
		
//		f.setLayout(new GridLayout(1, 2));
//		f.setSize(700, 700);
//		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		f.setVisible(true);
////		this.table2 = new TableViewCreator(700 / 2, 700);
//		this.graph2 = new GraphViewCreator(700 / 2, 700);

	};

	@Override
	public JComponent getView(Table table) {

		JComponent tables = this.table.getView(table);
		JComponent graph = this.graph.getView(table);

		
//		f.add(this.table2.getView(table));
//		f.add(this.graph2.getView(table));

		startPage.removeAll();

		tables.revalidate();

		startPage.add(tables);
		startPage.add(graph);

//		System.out.println("SP : " + startPage.hashCode());
//		System.out.println("G : SP : " + graph.hashCode());
		
		if(table==null)
			tables.setVisible(false);
		else
			tables.setVisible(true);
		
		return startPage;
	}
}
