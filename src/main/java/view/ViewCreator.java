package view;

import javax.swing.JComponent;

import entity.Table;

public interface ViewCreator {
	public void getView(Table table);

	public JComponent getSizedView(int width, int height, Table table);

}
