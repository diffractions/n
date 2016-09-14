package view.page.elements;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.page.ViewCreator;
import entity.Table;
import ga.Program;

public class StartMenuPanelViewCreator implements ViewCreator {

	private final JButton [] buttons;
	public StartMenuPanelViewCreator(JButton ... buttons){this.buttons =buttons;for(JButton button :buttons )button.setEnabled(false);}
	@Override
	public JPanel getView(final Table table) {
		JPanel panel = null;
		if (table != null) {
 			panel = new JPanel();
			JRadioButton transmittance = new JRadioButton("Transmittance", false);
			JRadioButton reflection = new JRadioButton("Reflection", false);
			ButtonGroup startGroup = new ButtonGroup(); 
			startGroup.add(transmittance);
			startGroup.add(reflection);
			transmittance.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					for(JButton button :buttons ){button.setEnabled(false);
					button.revalidate();}
					table.setProgram(Program.Transmittance);
				}
			});
			reflection.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					for(JButton button :buttons ){button.setEnabled(false);
					button.revalidate();}
					table.setProgram(Program.Reflection);
				}
			});
			
			panel.add(transmittance);
			panel.add(reflection);
		}
		return panel;
	}

	private void paintAxis(Table table) {

	}

}


