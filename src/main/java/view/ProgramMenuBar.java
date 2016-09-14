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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.PaintTableController;

public class ProgramMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu m_program = new JMenu("PROGRAM");
	private JMenuItem mi_open = new JMenuItem("Open");
	private JMenuItem paste = m_program.add("Paste");
	private final JFrame frame;

	private ActionListener openFileListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			FileFilter filter = new FileNameExtensionFilter(".dat", "dat");

			JFileChooser file = new JFileChooser();
			file.setFileFilter(filter);
			file.setAcceptAllFileFilterUsed(false);
			file.setCurrentDirectory(new File("."));
			int result = file.showOpenDialog(frame);
			if (result == JFileChooser.APPROVE_OPTION)
				PaintTableController.getInstance().openStartTable(
						file.getSelectedFile().getPath());

		}
	};

	ProgramMenuBar(JFrame frame) {
		this.frame = frame;
		add(m_program);
		mi_open.addActionListener(openFileListener);
		mi_open.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		mi_open.setMnemonic('O');
		m_program.add(mi_open, 0);
		m_program.addSeparator();
		paste.setEnabled(false);
	}
}
