package view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.pages.GraphPage;
import view.pages.StartPage;
import view.pages.TablePage;
import entity.Table;

public class PageManager {

	public static final String PROGRAM_NAME = "Transmittance";

	private static volatile PageManager page;
	private JFrame frame;

	private Container cont;
	private ViewCreator startPage;
	private ViewCreator graphResultPage;
	private ViewCreator tableResultPage;

	public static void main(String[] args) {
		PageManager.getInstance();
	}

	private PageManager() {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame = new ProgramWindow(PROGRAM_NAME);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

				cont = frame.getContentPane();

				startPage = new StartPage();
				graphResultPage = new GraphPage();
				tableResultPage = new TablePage();

			}
		});
	};

	public void paintTableResultPage(Table table) {

		cont.removeAll();
		cont.add(tableResultPage.getView(table));
		cont.revalidate();

	}

	public void paintStartPage(Table table) {

		cont.removeAll();
		cont.add(startPage.getView(table));
		cont.revalidate();
	}

	public void paintGraphResultPage(Table table) {

		cont.removeAll();
		cont.add(graphResultPage.getView(table));
		cont.revalidate();
	}

	public static PageManager getInstance() {
		PageManager local = page;
		if (local == null) {
			synchronized (PageManager.class) {
				local = page;
				if (local == null) {
					page = local = new PageManager();
				}
			}
		}
		return page;
	}
}