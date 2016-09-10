package view;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.pages.StartPage;
import entity.TableStory;

public class IndexPage {

	public static final int HEIGHT = 400;
	public static final int WIDTH = 800;
	public static final String PROGRAM_NAME = "Transmittance";

	private static volatile IndexPage page;
	private JFrame frame;
	ViewCreator startPage = new StartPage(WIDTH, HEIGHT);
	ViewCreator graphResultPage;
	ViewCreator tableResultPage;

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

			JComponent p = startPage.getView(null);
//			p.setVisible(false);
//			System.out.println("IP : " + p.hashCode());
			
			frame.add(p);
			// frame.add(graphResultPage.getView(null));
			// frame.add(tableResultPage.getView(null));

		}
	}

	public void paintTableResultPage(TableStory story) {
		frame.add(tableResultPage.getView(story.getTable()));

	}

	public void paintStartPage(TableStory story) {
		frame.add(startPage.getView(story.getTable()));
	}

	public void paintGraphResultPage(TableStory story) {
		frame.add(graphResultPage.getView(story.getTable()));
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