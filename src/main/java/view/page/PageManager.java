package view.page;

import java.awt.BorderLayout;
import java.awt.Container;

import view.page.panels.GraphPanel;
import view.page.panels.MenuPanel;
import view.page.panels.RedactorPanel;
import view.page.panels.TablePanel;
import entity.ModifyTable; 
import entity.Table;

public class PageManager {

	private Container cont;
	private ViewCreator redactorPage;
	private ViewCreator graphPage;
	private ViewCreator tablePage;
	private ViewCreator menuElement;

	public PageManager(Container cont) {

		this.cont = cont;
		redactorPage = new RedactorPanel();
		graphPage = new GraphPanel();
		tablePage = new TablePanel();
		menuElement = new MenuPanel();

	}

	public void paintTablePage(Table table, ModifyTable... tables) {
		paint(tablePage, table,tables );

	}

	public void paintRedactorPage(Table table, ModifyTable... tables) {
		paint(redactorPage, table,tables );
	}
	

	public void paintGraphPage(Table table, ModifyTable... tables) {
		paint(graphPage, table,tables );
	}

	private void paint(ViewCreator viev, Table table, ModifyTable... tables) {
		cont.removeAll();
		cont.add(viev.getView(table,tables));
		cont.add(menuElement.getView(table,tables), BorderLayout.SOUTH);
		cont.revalidate();
	}

}