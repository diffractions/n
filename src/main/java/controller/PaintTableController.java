package controller;

import service.Crossing;
import service.Fitting;
import service.Spline;
import entity.ModifyTable;
import entity.SimpleTable;
import entity.TwoColTable;
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
	public static final String[] TAMPLETE_HEADERS = new String[] { "X", "Y" };
	public static final String[] RESULT_HEADERS = new String[] { "X", "Y",
			"Min", "Max" };

	private SimpleTable startTable = null;
	private SimpleTable tampleteTable = null;
	private SimpleTable resultTable = null;
	private SimpleTable lastTable = null;

	private PageManager indexPage;

	TableDataFileManager tdfm = new TableDataFileManager();

	public void openStartTable(String fPath) {
		startTable = null;
		tampleteTable = null;
		resultTable = null;
		startTable = tdfm.readTable(fPath);
		fillTamplTable(startTable, 0);
		lastTable = startTable;
		stage = Stage.OPEN;
		showRedactor();
	}

	private void fillTamplTable(SimpleTable table, int colNumb) {
		if (colNumb == 0) {
			tampleteTable = new SimpleTable(table.getTable(), TAMPLETE_HEADERS);
		}
	}

	public void createExtrTable(int i) {
		if (!tampleteTable.hasCollByName("Smoth"))
			addSmoth(0);
		addExtr(i);
//		indexPage.paintRedactorPage(tampleteTable);
	}

	public void createSmothTable(int i) {
		addSmoth(i);
		indexPage.paintRedactorPage(tampleteTable);
	}

	private void addExtr(int i) {
		int[] extrs = new Crossing(i).getExtr(tampleteTable.getTable(), 2);
		int[][] extr = new Crossing(i).separateExtr(extrs,
				tampleteTable.getTable().length);

		double[] res1 = new Spline().getSpline(tampleteTable.getTable(),
				extr[0], 2);
		double[] res2 = new Spline().getSpline(tampleteTable.getTable(),
				extr[1], 2);

		tampleteTable.addCol(res1, "Extr1");
		tampleteTable.addCol(res2, "Extr2");

		resultTable = new SimpleTable(RESULT_HEADERS);

		ModifyTable modExtr1 = new TwoColTable(new String[]{"x", "Extr1"});
		ModifyTable modExtr2 = new TwoColTable(new String[]{"x", "Extr2"});
		
		for (int pos : extrs) {
			modExtr1.addRow(new double[]{tampleteTable.getTable()[pos][0],tampleteTable.getTable()[pos][3]});
			modExtr2.addRow(new double[]{tampleteTable.getTable()[pos][0],tampleteTable.getTable()[pos][4]});
			resultTable.addRow(new double[] { 
					tampleteTable.getTable()[pos][0],
					tampleteTable.getTable()[pos][1],
					tampleteTable.getTable()[pos][3],
					tampleteTable.getTable()[pos][4] });
		}

//		indexPage.paintRedactorPage(tampleteTable);
		indexPage.paintRedactorPage(tampleteTable, modExtr1, modExtr2);

	}

	private void addSmoth(int i) {
		double[] fitt = new Fitting(i).getFitt(startTable.getTable());
		tampleteTable.addCol(fitt, "Smoth");
	}

	public void showRedactor() {
		indexPage.paintRedactorPage(lastTable);
	}

	public void showTable() {
		indexPage.paintTablePage(lastTable);
	}

	public void showGraph() {
		indexPage.paintGraphPage(lastTable);
	}

	public void goNext() {
		if (stage == Stage.OPEN) {
			stage = Stage.EXTR;
			lastTable = tampleteTable;
			indexPage.paintRedactorPage(tampleteTable);
		} else if (stage == Stage.EXTR) {
			stage = Stage.RESULT;
			lastTable = resultTable;
			indexPage.paintRedactorPage(resultTable);
		}
	}

	public void goBack() {
		if (stage == Stage.EXTR) {
			setProgram(null);
			stage = Stage.OPEN;
			lastTable = startTable;
			indexPage.paintRedactorPage(startTable);
		} else if (stage == Stage.RESULT) {
			stage = Stage.EXTR;
			fillTamplTable(startTable, 0);
			lastTable = tampleteTable;
			indexPage.paintRedactorPage(tampleteTable);
		}

	}

}
