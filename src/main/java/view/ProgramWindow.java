package view;

import ga.__TableFileReader;
import ga.__TableShower;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import java.awt.Component;

import controller.PaintTableController;

public class ProgramWindow extends JFrame {

	private static final long serialVersionUID = 1L; 
	private JFrame th;
//	private JPanel panel;

	private ComponentListener listener =  new ComponentListener() {
		@Override
		public void componentResized(ComponentEvent e) {
			if(getContentPane().getComponents().length==1)
				getContentPane().getComponent(0).repaint();
		}
				
		@Override
		public void componentMoved(ComponentEvent e) {
			System.out.println(1);
			if(getContentPane().getComponents().length==1)
				getContentPane().getComponent(0).repaint();
		}
				
		@Override
		public void componentShown(ComponentEvent e) {
			if(getContentPane().getComponents().length==1)
				getContentPane().getComponent(0).repaint();
		}
				
		@Override
		public void componentHidden(ComponentEvent e) {
			if(getContentPane().getComponents().length==1)
				getContentPane().getComponent(0).repaint();
		}
	};

	private ActionListener openFileListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(new File("."));
		file.showOpenDialog(new JFrame());
		PaintTableController.getInstance().openStartTable(file.getSelectedFile().getPath());
		}
	};

	private JMenuBar mb_menu = new JMenuBar();
	private JMenu m_program = new JMenu("PROGRAM");
	private JMenuItem mi_open = m_program.add("OPEN");
	private	JMenuItem paste = m_program.add("Paste");

//	@Override
//	public Component add(Component comp) {
//		if(comp==panel){
//			return super.add(comp);
//		}
//		panel.add(comp);
//		panel.setVisible(false);
//		panel.repaint();
//		panel.setVisible(true);
//		return comp;
//	};


	@Override
	public Component add(Component comp) {
		if(getContentPane().getComponents().length==1)
			getContentPane().remove(0);
		Component compc = getContentPane().add(comp,0);
		getContentPane().setVisible(false);
		getContentPane().repaint();
		getContentPane().setVisible(true);
		return compc;
	};

	ProgramWindow(String programName) {
		super(programName);
		this.th = this;
				mb_menu.add(m_program);
mi_open.addActionListener(openFileListener);
m_program.addSeparator();
paste.setEnabled(false);


		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


			//	setSize(800, 400);
			//	setLayout(new GridLayout(1,1));

					// panel = new JPanel();
					// panel.setSize(getSize().width, getSize().height);
					// th.add(panel);
	
					// final JTable table = new JTable();
					// final JPanel graph = new JPanel();
					// graph.setBorder(BorderFactory.createLineBorder(Color.RED));
					//
					// frame.addComponentListener(
					//
					// new ComponentListener() {
					// @Override
					// public void componentResized(ComponentEvent e) {
					// tableShow.fillGraph(graph, tableReader.getPath());
					// }
					//
					// @Override
					// public void componentMoved(ComponentEvent e) {
					// tableShow.fillGraph(graph, tableReader.getPath());
					// }
					//
					// @Override
					// public void componentShown(ComponentEvent e) {
					// }
					//
					// @Override
					// public void componentHidden(ComponentEvent e) {
					// }
					//
					// });
	
	
					addComponentListener(listener);
					//JMenuBar mb_menu = new JMenuBar();
					//JMenu m_program = new JMenu("PROGRAM");
					//mb_menu.add(m_program);
					//mi_open.addActionListener(openFileListener);
					//JMenuItem mi_open = m_program.add("OPEN");
	
					//mi_open.addActionListener(new ActionListener() {
							//
							//@Override
							//public void actionPerformed(ActionEvent e) {
							//
							//JFileChooser file = new JFileChooser();
							//file.setCurrentDirectory(new File("."));
							//file.showOpenDialog(new JFrame());
							//
							//PaintTableController.getInstance().openStartTable(
							//file.getSelectedFile().getPath());
							//
							// tableShow
							// .setData(tableReader,
							// file.getSelectedFile().getPath());
							//
							// tableShow.setPointSize(tableReader.getPath(), 1, 15,
							// 10, 7);
							// tableShow.setPointColor(tableReader.getPath(),
							// Color.BLACK,
							// Color.YELLOW, Color.RED, Color.BLUE);
							//
							// tableShow.fillTable(table, tableReader.getPath());
							// tableShow.fillGraph(graph, tableReader.getPath());
							//
					//}
					//
					//});*/
				
					//m_program.addSeparator();
					//JMenuItem paste = m_program.add("Paste");
					//paste.setEnabled(false);

				setJMenuBar(mb_menu);
					// frame.add(new JScrollPane(table));
					// frame.add(graph);
				setVisible(true);
			}
		});
	}
}