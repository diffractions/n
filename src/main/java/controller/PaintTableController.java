package controller;
 

import service.Crossing;
import service.Fitting;

import java.util.Arrays;

import entity.ModifyTable;
import entity.SimpleTable;
import entity.Table;
import entity.TwoColTable;
import ga.InitGA;
import ga.Program;
import service.table.TableDataFileManager;
import utils.TableUtils;
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
	public static final String[] RESULT_HEADERS = new String[] { "X", "Min", "Max" };

	private  Table startTable = null;
	private Table tampleteTable = null;
	private Table resultTable = null;
	private Table lastTable = null;

	private PageManager indexPage;

	TableDataFileManager tdfm = new TableDataFileManager();

	private ModifyTable modExtr1;

	private ModifyTable modExtr2;

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

	private void fillTamplTable(Table table, int colNumb) {
		if (colNumb == 0) {
			tampleteTable = new SimpleTable(table.getTable(), TAMPLETE_HEADERS);
		}
	}

	public void createExtrTable(int i) {
		if (!tampleteTable.hasCollByName("Smoth"))
			addSmoth(0);
		findExtr(i);
	}

	public void createSmothTable(int i) {
		addSmoth(i);
		indexPage.paintRedactorPage(tampleteTable);
	}

	private void findExtr(int i) {
		int[] extrs = new Crossing(i).getExtr(tampleteTable.getTable(), 2);  
		modExtr1 = new TwoColTable(new String[] { "x", "Extr" });  
		for (int pos : extrs)
			modExtr1.addRow(new double[] { tampleteTable.getTable()[pos][0], tampleteTable.getTable()[pos][2] }); 
		indexPage.paintRedactorPage(tampleteTable, modExtr1);
	}
	
	public void separateExtr() {
		TableUtils.sort(modExtr1, 0);
		ModifyTable[] extr = TableUtils.separateExtr(modExtr1);
		modExtr1 = extr[0];
		modExtr2 = extr[1];
		indexPage.paintRedactorPage(tampleteTable, modExtr1, modExtr2); 

		resultTable = TableUtils.merge(modExtr1, modExtr2, RESULT_HEADERS);
		System.out.println(Arrays.toString(resultTable.getColl("X")));
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

	public void calculateN() {
		try {
			InitGA.start(resultTable.getTable());
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		
	}



}
