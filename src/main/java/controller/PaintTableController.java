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
	//public static final String[] TAMPLETE_HEADERS = new String[] { "X", "Y", "Smoth", "Max", "Min" };
	public static final String[] TAMPLETE_HEADERS = new String[] { "X", "Y" };
	//public static final String[] RESULT_HEADERS = new String[] { "A", "B", "C",
	//		"D", "E" };

	private Table startTable = null;
	private Table tampleteTable = null;
	//private Table resultTable = null;
	private Table lastTable = null;

	private PageManager indexPage;

	TableDataFileManager tdfm = new TableDataFileManager();

	public void openStartTable(String fPath) {
		  startTable = null;
		  tampleteTable = null;
		//  resultTable = null;
		startTable = tdfm.readTable(fPath);
		fillTamplTable(startTable,0);
		lastTable = startTable;
		stage = Stage.OPEN;
		showRedactor();
	}

	private void fillTamplTable(Table table, int colNumb) {
		if (colNumb == 0) {
			tampleteTable = new Table(TAMPLETE_HEADERS);
			for(double[] row : table.getTable()){
				double[] addRow = new double [tampleteTable.getCollCount()];
				addRow[0] = row[0];
				addRow[1] = row[1];
 				tampleteTable.addRow(addRow);
			}
			
		}
		
	}

	public void createExtrTable(int i) {
		//System.out.println(2);

		//Table tableToFoundExtr = tampleteTable == null ? startTable
		//		: tampleteTable;

		//System.out.println(startTable != null ? startTable.getTable() : null);
		//System.out.println(tampleteTable != null ? tampleteTable.getTable()
		//	: null);

		//double[][] d = new Crossing(i).getExtr(startTable.getTable()); 

		//resultTable = new Table(TAMPLETE_HEADERS);
		
		/*for (double[] g : d) { 
			resultTable.addRow(g);
		} */


		if(!tampleteTable.hasColl("Smoth")) addSmoth(0);
		addExtr(i);
		indexPage.paintRedactorPage(tampleteTable);
	}

	public void createSmothTable(int i) {
		addSmoth(i);
		indexPage.paintRedactorPage(tampleteTable);
	}

	private void addExtr(int i){
	 	double[][] extr = new Crossing(i).getExtr(tampleteTable.getTable(), 2); 
		//tampleteTable.addCol(extr,"Extr");
	}

	private void addSmoth(int i){
	 	double[] fitt = new Fitting(i).getFitt(startTable.getTable());
		tampleteTable.addCol(fitt,"Smoth");
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
			lastTable =startTable; 
			indexPage.paintRedactorPage(startTable);
		} else if (stage == Stage.EXTR) {
			stage = Stage.RESULT;
			//lastTable =resultTable; 
			//indexPage.paintRedactorPage(resultTable);
		}
	}

	public void goBack() {
		if (stage == Stage.EXTR) {
			setProgram(null);
			stage = Stage.OPEN;
			lastTable =startTable; 
			indexPage.paintRedactorPage(startTable);
		} else if (stage == Stage.RESULT){
			stage = Stage.EXTR;
			lastTable = startTable; 
			indexPage.paintRedactorPage(startTable);
		}

	}

}
