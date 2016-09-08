package view;

import javax.swing.JComponent; 

import entity.Table;

public interface ViewCreator {
	public void setSize(int x, int y);
	public JComponent getView(Table table);
}
