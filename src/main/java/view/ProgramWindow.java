package view;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

import view.page.PageManager;
import controller.PaintTableController;

public class ProgramWindow extends JFrame {

	public static final int WIDTH_SIZE = 800;
	public static final int HEIGHT_SIZE = 400;
	public static final String PROGRAM_NAME = "Transmittance";
	public static final Dimension preferredSize = new Dimension(WIDTH_SIZE,
			HEIGHT_SIZE);

	private static final long serialVersionUID = 1L;
	public final PageManager page;

	private static final JMenuBar MB_MENU = new ProgramMenuBar();

	@Override
	public Component add(Component comp) {
		Component compc = getContentPane().add(comp, 0);
		return compc;
	};

	public ProgramWindow() {

		page = new PageManager(getContentPane());
		PaintTableController.getInstance().setIndexPage(page);

		// Toolkit kit = Toolkit.getDefaultToolkit();
		// Dimension screeSize = kit.getScreenSize();

		// int frameXPosition = (int) (screeSize.getWidth() - WIDTH_SIZE) / 2;
		// int frameYPosition = (int) (screeSize.getHeight() - HEIGHT_SIZE) / 2;

		// setBackground(Color.WHITE);
		// setForeground(Color.WHITE);

		// setSize(preferredSize);
		// setLocation(frameXPosition, frameYPosition);

		// setBounds(frameXPosition, frameYPosition, WIDTH_SIZE, HEIGHT_SIZE);
		// setExtendedState(MAXIMIZED_BOTH);
		// setIconImage();

		// setUndecorated(true);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setJMenuBar(MB_MENU);
				setTitle(PROGRAM_NAME);
				setLocationByPlatform(true);
				pack();
				setVisible(true);

			}
		});
	}

	@Override
	public Dimension getPreferredSize() {
		return preferredSize;
	}

	public static void main(String[] args) {
		new ProgramWindow();
	}
}