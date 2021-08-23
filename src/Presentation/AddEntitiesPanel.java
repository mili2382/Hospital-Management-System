package Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author IwanB
 * AddEntitiesPanel is shown at the top of the patient tracker window
 * - this is where all buttons used to add entities like Patient/Project/User should be added
 * 
 */
public class AddEntitiesPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2256207501462485047L;
	

	private JButton addPatientButton = new JButton("Add New Patient");


	public AddEntitiesPanel()
	{
		setBorder(BorderFactory.createLineBorder(Color.black));
		add(addPatientButton);
		updateLayout();
		addPatientButton.addActionListener(new ActionListener() {		
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				AddPatientDialog dialog = new AddPatientDialog();
				dialog.show();
			}
		});	
	}


	private void updateLayout() {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		layout.addLayoutComponent(addPatientButton, BorderLayout.CENTER);
	}
}
