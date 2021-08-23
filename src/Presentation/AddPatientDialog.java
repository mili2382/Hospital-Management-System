package Presentation;

import java.awt.BorderLayout;

import javax.swing.JDialog;

import Business.Patient;

/**
 * 
 * @author IwanB
 *
 * AddPatientDialog - used to add a new patient
 * 
 */
public class AddPatientDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 173323780409671768L;
	
	/**
	 * detailPanel: reuse PatientDetailPanel to add patients
	 */
	private PatientDetailPanel detailPanel = new PatientDetailPanel(false);

	public AddPatientDialog()
	{
		setTitle(StringResources.getAppTitle());
		detailPanel.initPatientDetails(getBlankPatient());
		add(detailPanel);
		updateLayout();
		setSize(400, 400);
	}

	private void updateLayout() {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		layout.addLayoutComponent(detailPanel, BorderLayout.CENTER);
	}

	private Patient getBlankPatient() {
		Patient patient = new Patient();
		return patient;
	}
}
