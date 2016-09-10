package view;

import java.awt.Container;
import javax.swing.JFrame;

import view.pages.GraphPage;
import view.pages.StartPage;
import view.pages.TablePage;
import entity.TableStory;

public class IndexPage {

	public static final int HEIGHT = 400;
	public static final int WIDTH = 800;
	public static final String PROGRAM_NAME = "Transmittance";

	private static volatile IndexPage page;
	private JFrame frame;

	private Container cont;
	private ViewCreator startPage;
	private ViewCreator graphResultPage;
	private ViewCreator tableResultPage;

	public static void main(String[] args) {
		IndexPage.getInstance();
		page.startProgram();
	}

	private IndexPage() {
	};

	public void startProgram() {
		if (frame == null) {
			frame = new ProgramWindow(PROGRAM_NAME);
			frame.setSize(WIDTH, HEIGHT);

			cont = frame.getContentPane();

			startPage = new StartPage(cont);
			graphResultPage = new GraphPage(cont);
			tableResultPage = new TablePage(cont);

		}
	}

	public void paintTableResultPage(TableStory story) {
		frame.setContentPane(tableResultPage.getView(story.getTable()));
//		tableResultPage.getView(story.getTable());

	}

	public void paintStartPage(TableStory story) {
		frame.setContentPane(startPage.getView(story.getTable()));
//		startPage.getView(story.getTable());
	}

	public void paintGraphResultPage(TableStory story) {
		frame.setContentPane(graphResultPage.getView(story.getTable()));
//		graphResultPage.getView(story.getTable());
	}

	public static IndexPage getInstance() {
		IndexPage local = page;
		if (local == null) {
			synchronized (IndexPage.class) {
				local = page;
				if (local == null) {
					page = local = new IndexPage();
				}
			}
		}
		return page;
	}
}