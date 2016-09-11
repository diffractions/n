package view.pages;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;

//import view.AbstractViewCreator;
import view.ViewCreator;
import view.elements.TableViewCreator;
import entity.Table;

public class TablePage /*extends AbstractViewCreator*/ implements ViewCreator {

	private TableViewCreator table_tp;
	private JPanel p;
 
	public TablePage() {
		this.table_tp = new TableViewCreator();
		this.p = new JPanel();
	}
 
	@Override
	public Container getView(Table table) { 
		p.removeAll();
		p.setLayout(new GridLayout(1, 1));
		p.add(this.table_tp.getView(table));
		p.revalidate();
		return p;
	}

}
