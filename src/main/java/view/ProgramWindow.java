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
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.FlowLayout;

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

	private JMenuBar mb_menu = new JMenuBar();
	private JMenu m_program = new JMenu("PROGRAM");
	private JMenuItem mi_open = m_program.add("OPEN");
	private JMenuItem paste = m_program.add("Paste");

//	 @Override
//	 public Component add(Component comp) {
//	 if (getContentPane().getComponents().length == 1)
//	 getContentPane().remove(0);
//	 // Component compc = getContentPane().add(comp, 0);
//	 // getContentPane().setVisible(false);
//	 // getContentPane().repaint();
//	 // getContentPane().setVisible(true);
//	 // return compc;
//	 return getContentPane().add(comp, 0);
//	 };

	ProgramWindow(String programName) {
		super(programName);

		mb_menu.add(m_program);
		mi_open.addActionListener(openFileListener);
		m_program.addSeparator();
		paste.setEnabled(false);
		

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				addComponentListener(listener);
				setJMenuBar(mb_menu);
				setVisible(true);
			}
		});
	}
}