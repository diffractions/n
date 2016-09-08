package controller;

import service.table.TableDataFileManager;
import view.IndexPage;
import entity.Table;
import entity.TableViewStory;
import entity.TablesStory;

public class PaintTableController {

	public static PaintTableController getInstance() {
		return new PaintTableController();
	}

	public static final String RESULT_TABLE_NAME = "ResultTable";
	public static final String START_TABLE_NAME = "StartTable";
	public static final String[] RESULT_HEADERS = new String[] { "A", "B", "C",
			"D", "E" };

	private TablesStory story = TableViewStory.getInstance();
	private IndexPage indexPage = IndexPage.getInstance();
	TableDataFileManager tdfm = new TableDataFileManager();

	public void openStartTable(String fPath) {
		story.setStory(START_TABLE_NAME, tdfm.readTable(fPath));
		indexPage.paintStartPage(story.getStory(START_TABLE_NAME));
	}

	public void createResultTable() {
		story.setStory(RESULT_TABLE_NAME, new Table(RESULT_HEADERS));
		indexPage.paintGraphResultPage(story.getStory(RESULT_TABLE_NAME));
		indexPage.paintTableResultPage(story.getStory(RESULT_TABLE_NAME));
	}

	public void start() {
		indexPage.startProgram();
	}
}
