package Presentation;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;

import Business.Patient;

/**
 * 
 * Represents patient list panel of patient tracker that includes
 * both the search box/button and text field; AND the patient list
 * @author IwanB
 *
 */
public class PatientSidePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2693528613703066603L;

	private PatientListPanel mListPanel;
	
	/**
	 * Represents left panel of patient tracker that includes
	 * both the search box/button and text field; AND the patient list
	 * 
	 * @param searchEventListener : used to retrieve user search query in search box
	 * @param listPanel : patient list panel
	 */
	public PatientSidePanel(ISearchEventListener searchEventListener, PatientListPanel listPanel)
	{
		mListPanel = listPanel;
		PatientSearchComponents searchComponents = new PatientSearchComponents(searchEventListener);
	
		add(searchComponents);
		add(listPanel);
		
		BorderLayout layout = new BorderLayout();
		layout.addLayoutComponent(searchComponents, BorderLayout.NORTH);
		layout.addLayoutComponent(listPanel, BorderLayout.CENTER);
		setLayout(layout);
	}
	
	public void refresh(Vector<Patient> patients)
	{
		mListPanel.refresh(patients);
	}
	
	public void registerPatientSelectionNotifiableObject(IPatientSelectionNotifiable notifiable)
	{
		mListPanel.registerPatientSelectionNotifiableObject(notifiable);
	}
}
