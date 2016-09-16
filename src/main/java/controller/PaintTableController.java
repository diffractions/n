package controller;

import service.Crossing;
import entity.Table;
import ga.Program;
import service.table.TableDataFileManager;
import service.Stage;
import view.page.PageManager;
import java.util.Arrays;

public class PaintTableController {

	public Stage getStage() {
		return stage;
	}

	private Stage stage = null;

	private Program program = null;
	public void setProgram(Program program){this.program =program;}
	public Program getProgram(){return program;}

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
		stage = Stage.OPEN;
		showRedactor();
	}

	public void createResultTable(int i) {
		double [][][] d = new Crossing(i).findMinExtr(startTable.getTable());
		for(double [][] g : d)
		{
		for (double [] f  : g)
		{
		System.out.println(f[0] + "\t" + f[1]);
		}
		System.out.println("------------");
		}
		//System.out.println(Arrays.deepToString(new Crossing(i).findMinExtr(startTable.getTable())));
		System.out.println(i);
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
