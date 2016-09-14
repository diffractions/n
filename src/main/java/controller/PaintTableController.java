package controller;

import entity.Table;
import service.table.TableDataFileManager;
import service.Stage;
import view.page.PageManager;
import ga.Program;

public class PaintTableController {

	private Program program = null;
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
		stage = Stage.OPEN;
	}

	public void createResultTable() {
		System.out.println(111);
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


	private Stage stage = null;

	public void goNext() {
		if(stage==Stage.OPEN){
			indexPage.paintRedactorPage(startTable);
			stage=Stage.EXTR;
		}
	}

	public void goBack() {
		if(stage==Stage.EXTR){
			startTable.setProgram(null);
			indexPage.paintRedactorPage(startTable);
			stage=Stage.OPEN;
		}
	}

}
