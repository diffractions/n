package view.page.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.page.AbstractPageViewCreator;
import view.page.ViewCreator;
import view.page.elements.GraphViewCreator;
import view.page.elements.TableViewCreator;
import entity.Table;
import entity.ModifyTable;
import entity.SimpleTable;
import entity.TwoColTable;

public class RedactorPanel extends AbstractPageViewCreator implements
		ViewCreator {

	private TableViewCreator table;
	private GraphViewCreator graph;

	public RedactorPanel() {
		this.table = new TableViewCreator();
		this.graph = new GraphViewCreator();
		this.pageToView = new JPanel();
	}

	@Override
	public void fillComponent(Table table, ModifyTable... tables) {
		pageToView.setLayout(new GridLayout(1, 2));
		pageToView.add(this.table.getView(table, tables));
		pageToView.add(this.graph.getView(table, tables));

	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("Test");

		Dimension size = new Dimension(300, 600);
		f.setSize(size);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Table table1 = new SimpleTable(new double[][] {
				new double[] { 0, 1, 1.5 }, new double[] { 0.5, 1.5, 2 },
				new double[] { 1, 2, 2.5 }, new double[] { 1.5, 2.5, 3 } },
				new String[] { "Hello", "Bye", "Bye" });
		ModifyTable table2 = new TwoColTable(new double[][] {
				new double[] { 2, 1.5 }, new double[] { 1, 2 },
				new double[] { 0, 2.5 } }, new String[] { "Hello", "Byet" });
		ModifyTable table3 = new TwoColTable(new double[][] {
				new double[] { 0, 0 }, new double[] { 3, 1 }, }, new String[] {
				"Hello", "Byes" });

		RedactorPanel p = new RedactorPanel();
		f.add(p.getView(table1, table2, table3));
		f.setVisible(true);
	}

}
