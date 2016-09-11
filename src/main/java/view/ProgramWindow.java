package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.PaintTableController;

public class ProgramWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private ComponentListener listener = new ComponentListener() {
		@Override
		public void componentResized(ComponentEvent e) {
			if (getContentPane().getComponents().length == 1)
				getContentPane().getComponent(0).repaint();
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			if (getContentPane().getComponents().length == 1)
				getContentPane().getComponent(0).repaint();
		}

		@Override
		public void componentShown(ComponentEvent e) {
			if (getContentPane().getComponents().length == 1)
				getContentPane().getComponent(0).repaint();
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			if (getContentPane().getComponents().length == 1)
				getContentPane().getComponent(0).repaint();
		}
	};

	private ActionListener openFileListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser file = new JFileChooser();
			file.setCurrentDirectory(new File("."));
			file.showOpenDialog(new JFrame());
			PaintTableController.getInstance().openStartTable(
					file.getSelectedFile().getPath());
		}
	};

	private ActionListener nextStageListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PaintTableController.getInstance().nextStage();
		}
	};
	private ActionListener pervStageListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PaintTableController.getInstance().pervStage();
		}
	};

	private JMenuBar mb_menu = new JMenuBar();
	private JMenu m_program = new JMenu("PROGRAM");
	private JMenuItem mi_open = m_program.add("OPEN");
	private JMenuItem paste = m_program.add("Paste");

	private JMenuItem m_back = new JMenuItem("Back");
	private JMenuItem m_next = new JMenuItem("Next");

	// @Override
	// public Component add(Component comp) {
	// if (getContentPane().getComponents().length == 1)
	// getContentPane().remove(0);
	// // Component compc = getContentPane().add(comp, 0);
	// // getContentPane().setVisible(false);
	// // getContentPane().repaint();
	// // getContentPane().setVisible(true);
	// // return compc;
	// return getContentPane().add(comp, 0);
	// };

	public static int WIDTH_SIZE = 800;
	public static int HEIGHT_SIZE = 400;

	public ProgramWindow(String programName) {
		super(programName);

		mb_menu.add(m_program);
		mi_open.addActionListener(openFileListener);
		m_program.addSeparator();
		paste.setEnabled(false);

		mb_menu.add(m_back);
		mb_menu.add(m_next);
		m_back.addActionListener(pervStageListener);
		m_next.addActionListener(nextStageListener);

		addComponentListener(listener);
		setJMenuBar(mb_menu);
		setSize(WIDTH_SIZE, HEIGHT_SIZE);
	}
}