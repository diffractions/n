package view.page.elements.graph;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.util.Set;
import java.util.HashSet;
import entity.ModifyTable;
import javax.swing.JMenu;


public class GraphPanelManagerPopMenu extends JPopupMenu {



private Set <ModifyTable> list;

       private JMenuItem mi_del ;
        private JMenu mi_add ;

public GraphPanelManagerPopMenu()
{

	list = new HashSet<ModifyTable>();

        mi_del = new JMenuItem("del");
        mi_add = new JMenu("add");
	add(mi_del);
	add(mi_add);

	//System.out.println(getComponentCount());

}

public void addTbale(ModifyTable table){
	list.add(table);
        JMenuItem mi = new JMenuItem(table.getHeaders()[1]);
        mi_add.add(mi);
}





}