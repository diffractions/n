package view.elements;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TableMenyPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public TableMenyPanel() {
		setSize(800, 70);

		JButton a = new JButton("back");
		add(a);
		add(new JButton("calculate"));
		add(new JButton("result graph"));
		add(new JButton("result table"));
		add(new JButton("next"));
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new TableMenyPanel());
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
