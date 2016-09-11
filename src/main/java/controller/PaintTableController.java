package controller;

import entity.Table;
//import entity.TableViewStory;
//import entity.TablesStory;
import service.table.TableDataFileManager;
import view.PageManager;

public class PaintTableController {

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

	private PageManager indexPage = PageManager.getInstance();
	TableDataFileManager tdfm = new TableDataFileManager();

	public void openStartTable(String fPath) {
		startTable = tdfm.readTable(fPath);
		indexPage.paintStartPage(startTable);
	}

	public void createResultTable() {
	}

	public void nextStage() {
		indexPage.paintGraphResultPage(startTable);
	}

	public void pervStage() {
		indexPage.paintTableResultPage(startTable);
	}

//	public void start() {
//		indexPage.startProgram();
//	}
}
