package view.page.elements;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;

import view.page.ViewCreator;
import entity.Table;
import entity.ModifyTable; 

public class ResultMenuPanelViewCreator extends AbstractMenuPanelViewCreator
		implements ViewCreator {
 

	public void setB_back(JButton b_back) {
		this.b_back = b_back;
		b_back.setEnabled(true);
	}

	public void setB_next(JButton b_next) {
		this.b_next = b_next;
		b_next.setEnabled(false);
	}

	@Override
	public JComponent getView(Table table, ModifyTable... tables) { 
		return new JPanel();
	}

}
