package controller;

import entity.Table;
import service.table.TableDataFileManager;
import view.page.PageManager;

public class PaintTableController {

	public PageManager getIndexPage() {
		return indexPage;
	}

	public void setIndexPage(PageManager indexPage) {
		this.indexPage = indexPage;
	}

	private static volatile PaintTableController page;

	public static PaintTableController getInstance() {

		PaintTableController local = page;
		if (local == null) {
			synchronized (PageManager.class) {
				local = page;
				if (local == null) {
					page = local = new PaintTableController();

				}
			}
		}
		return page;

	}

	public static final String RESULT_TABLE_NAME = "ResultTable";
	public static final String START_TABLE_NAME = "StartTable";
	public static final String[] RESULT_HEADERS = new String[] { "A", "B", "C",
			"D", "E" };

	Table startTable = null;
	Table resultTable = null;

	private PageManager indexPage;

	TableDataFileManager tdfm = new TableDataFileManager();

	public void openStartTable(String fPath) {
		startTable = tdfm.readTable(fPath);
		showRedactor();
	}

	public void createResultTable() {
	}

	public void showRedactor() {
		indexPage.paintRedactorPage(startTable);
	}

	public void showTable() {
		indexPage.paintTablePage(startTable);
	}

	public void showGraph() {
		indexPage.paintGraphPage(startTable);
	}

	public void goNext() {
		// indexPage.paintGraphPage(startTable);
	}

	public void goBack() {
		// indexPage.paintGraphPage(startTable);
	}

}
