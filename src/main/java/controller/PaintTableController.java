package controller;

import service.Crossing;
import service.Fitting;
import entity.Table;
import ga.Program;
import service.table.TableDataFileManager;
import service.Stage;
import view.page.PageManager;

public class PaintTableController {

	public Stage getStage() {
		return stage;
	}

	private Stage stage = null;

	private Program program = null;

	public void setProgram(Program program) {
		this.program = program;
	}

	public Program getProgram() {
		return program;
	}

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
	public static final String[] TAMPLETE_HEADERS = new String[] { "A", "B" };
	public static final String[] RESULT_HEADERS = new String[] { "A", "B", "C",
			"D", "E" };

	Table startTable = null;
	Table tampleteTable = null;
	Table resultTable = null;

	private PageManager indexPage;

	TableDataFileManager tdfm = new TableDataFileManager();

	public void openStartTable(String fPath) {
		  startTable = null;
		  tampleteTable = null;
		  resultTable = null;
		startTable = tdfm.readTable(fPath);
		stage = Stage.OPEN;
		showRedactor();
	}

	public void createResultTable(Table table) {
		if (tampleteTable == null) {
			tampleteTable = table;
		}
	}

	public void createExtrTable(int i) {
		System.out.println(2);

		Table tableToFoundExtr = tampleteTable == null ? startTable
				: tampleteTable;

		System.out.println(startTable != null ? startTable.getTable() : null);
		System.out.println(tampleteTable != null ? tampleteTable.getTable()
				: null);

		double[][] d = new Crossing(i).getExtr(tableToFoundExtr.getTable()); 

		resultTable = new Table(TAMPLETE_HEADERS);
		for (double[] g : d) { 
			resultTable.addRow(g);
		} 
	}

	public void createSmothTable(int i) {
		System.out.println(1);
		double[][] d = new Fitting(i).getFitt(startTable.getTable());

		tampleteTable = new Table(TAMPLETE_HEADERS);
		for (double[] g : d) {
			tampleteTable.addRow(g);
		}
		indexPage.paintRedactorPage(tampleteTable);
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
		if (stage == Stage.OPEN) {
			stage = Stage.EXTR;
			indexPage.paintRedactorPage(startTable);
		} else if (stage == Stage.EXTR) {
			stage = Stage.RESULT;
			indexPage.paintRedactorPage(resultTable);
		}
	}

	public void goBack() {
		if (stage == Stage.EXTR) {
			setProgram(null);
			stage = Stage.OPEN;
			indexPage.paintRedactorPage(startTable);
		}

	}

}
