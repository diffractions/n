package view;

import javax.swing.JFrame;

import service.Updating;
import view.pages.StartPage;
import entity.Table;
import entity.TableStory;

public class IndexPage {

	public static final String PROGRAM_NAME = "Transmittance";
	private static volatile IndexPage page;
	private JFrame frame;
	ViewCreator startPage;
	ViewCreator graphResultPage;
	ViewCreator tableResultPage;

	public static void main(String[] args) {
		IndexPage.getInstance();
		page.startProgram();
	}

	private IndexPage() {
	};

	public void startProgram() {
		if(frame==null){
			frame = new ProgramWindow(PROGRAM_NAME);
			frame.setSize(800, 400);

			startPage = new StartPage(frame.getSize().width, frame.getSize().height);
			paintStartPage(new TableStory() {
	
				@Override
				public Updating getUpdating() {
					return null;
				}
	
				@Override
				public Table getTable() {
					return null;
				}
			});
		}
	}

	public void paintTableResultPage(TableStory story) {
	}

	public void paintStartPage(TableStory story) {
		paintTable(story.getTable(), startPage);

	}

	private void paintTable(Table table, ViewCreator view) {
		frame.add(view.getView(table));
	}

	public void paintGraphResultPage(TableStory story) {
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