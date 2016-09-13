package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.PaintTableController;

public class ProgramMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu m_program = new JMenu("PROGRAM");
	private JMenuItem mi_open = new JMenuItem("Open");
	private JMenuItem paste = m_program.add("Paste");
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

	ProgramMenuBar() {
		add(m_program);
		mi_open.addActionListener(openFileListener);
		mi_open.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		mi_open.setMnemonic('O');
		m_program.add(mi_open, 0);
		m_program.addSeparator();
		paste.setEnabled(false);
	}
}
