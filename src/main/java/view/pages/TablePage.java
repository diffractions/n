package view.pages;
 
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JComponent;

import view.AbstractViewCreator;
import view.ViewCreator;
import view.elements.TableViewCreator;
import entity.Table;

public class TablePage extends AbstractViewCreator implements ViewCreator {

	private TableViewCreator table;

	public TablePage(Container cont) {
		super(cont);
		this.table = new TableViewCreator(cont);
	}

	@Override
	public Container getView(int width, int height, Table table) {
		cont.removeAll();
		cont.setLayout(new GridLayout(1, 1));
		cont.add(this.table.getView(width, width, table));
		cont.revalidate();
		return cont;
	}

}
