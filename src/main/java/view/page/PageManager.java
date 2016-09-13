package view.page;

import java.awt.BorderLayout;
import java.awt.Container;

import view.page.panels.GraphPanel;
import view.page.panels.MenuPanel;
import view.page.panels.RedactorPanel;
import view.page.panels.TablePanel;
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

	public void paintTablePage(Table table) {
		paint(table, tablePage);

	}

	public void paintRedactorPage(Table table) {
		paint(table, redactorPage);
	}

	public void paintGraphPage(Table table) {
		paint(table, graphPage);
	}

	private void paint(Table table, ViewCreator viev) {
		cont.removeAll();
		cont.add(viev.getView(table));
		cont.add(menuElement.getView(table), BorderLayout.SOUTH);
		cont.revalidate();
	}

	// public static PageManager getInstance() {
	// PageManager local = page;
	// if (local == null) {
	// synchronized (PageManager.class) {
	// local = page;
	// if (local == null) {
	// page = local = new PageManager();
	// }
	// }
	// }
	// return page;
	// }
}