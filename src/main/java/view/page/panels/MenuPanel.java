package view.page.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import controller.PaintTableController;
import entity.Table;
import entity.ModifyTable;
import view.page.ViewCreator;
import view.page.elements.menu.GraphStyleViewCreator;

public class MenuPanel implements ViewCreator {

	private JPanel main_panel = new JPanel();
	private JPanel top_panel = new JPanel();
	private JPanel but_panel = new JPanel();

	private GridLayout main_gl = new GridLayout(2, 1, 0, 2);
	private GridLayout top_gl = new GridLayout(1, 3, 1, 0);
	private GridLayout but_gl = new GridLayout(1, 2, 1, 0);

	private JButton b_both = new JButton("both");
	private JButton b_table = new JButton("table");
	private JButton b_graph = new JButton("graph");
	private JButton b_back = new JButton("back");
	private JButton b_next = new JButton("next");

	private ActionListener tableListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PaintTableController.getInstance().showTable();
		}
	};
	private ActionListener graphListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PaintTableController.getInstance().showGraph();
		}
	};
	private ActionListener bothListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PaintTableController.getInstance().showRedactor();
		}
	};
	private ActionListener nextListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PaintTableController.getInstance().goNext();
		}
	};
	private ActionListener backListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PaintTableController.getInstance().goBack();
		}
	};

	public MenuPanel() {

		main_panel.setPreferredSize(new Dimension(210, 40));
		main_panel.setLayout(main_gl);

		main_panel.add(top_panel);
		main_panel.add(but_panel);

		top_panel.setLayout(top_gl);
		top_panel.add(b_table);
		top_panel.add(b_graph);
		top_panel.add(b_both);

		but_panel.setLayout(but_gl);
		but_panel.add(b_back);
		but_panel.add(b_next);

		b_graph.addActionListener(graphListener);
		b_table.addActionListener(tableListener);
		b_both.addActionListener(bothListener);

		b_back.addActionListener(backListener);
		b_next.addActionListener(nextListener);
	}

	@Override
	public JComponent getView(Table table, ModifyTable... tables) {

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));

//		JPanel p1 = new JPanel();
//		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
//		p1.add(PaintTableController.getInstance().getStage()
//				.getMenuViewCreator(b_back, b_next).getView(table));

		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p2.add(main_panel);

		
JPanel p0 = new JPanel();
p0.setLayout(new GridLayout(2,1));
		
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(PaintTableController.getInstance().getStage()
				.getMenuViewCreator(b_back, b_next).getView(table));
		

p0.add(p1);
p0.add(new GraphStyleViewCreator().getView(table, tables));
 
p.add(p0);


//		p.add(p1);
		p.add(p2);

		return p;
	}

	// JToolBar n = new JToolBar("Test");
	// n.add(new JSlider(SwingConstants.HORIZONTAL, 100, 1000, 550));
	// n.setToolTipText("Test Slider and TipTxt");
	// add(n);
}
