package Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Business.BusinessComponentFactory;
import Business.Patient;


/**
 * 
 * @author IwanB
 * Panel used for creating and updating patients
 */
public class PatientDetailPanel extends JPanel implements IPatientSelectionNotifiable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2031054367491790942L;
	
	private Patient currentPatient = null;
	private boolean isUpdatePanel = true;
	
	private JTextField idField; 
	private JTextField nameField;
	private JTextField ageField;
	private JTextField symptomsField;
	private JTextField assignedDoctorField;
	private JTextArea desc;
	
	/**
	 * Panel used for creating and updating patients
	 * @param isUpdatePanel : describes whether panel will be used to either create or update patient
	 */
	public PatientDetailPanel(boolean isUpdatePanel)
	{
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.isUpdatePanel = isUpdatePanel;
	}

	/**
	 * Re-populates panel details with given patient object
	 * @param patient new patient object to populate panel details with
	 */
	public void initPatientDetails(Patient patient) {
		removeAll();	
		if(patient != null)
		{
			currentPatient = patient;
			addAll();
		}
	}

	private void addAll() {
		JPanel lTextFieldPanel = new JPanel();
		BoxLayout lTextFieldLayout = new BoxLayout(lTextFieldPanel, BoxLayout.Y_AXIS);
		lTextFieldPanel.setLayout(lTextFieldLayout);
		
		BorderLayout lPanelLayout = new BorderLayout();	
		lPanelLayout.addLayoutComponent(lTextFieldPanel, BorderLayout.NORTH);

		//create patient text fields
		//application convention is to map null to empty string (if db has null this will be shown as empty string)
		if(currentPatient.getId() > 0) {
			idField = createLabelTextFieldPair(StringResources.getPatientIDLabel(), ""+currentPatient.getId(), lTextFieldPanel);
			idField.setEditable(false);
		}
		nameField = createLabelTextFieldPair(StringResources.getPatientNameLabel(), currentPatient.getName() == null ? "" : currentPatient.getName(), lTextFieldPanel);
		ageField = createLabelTextFieldPair(StringResources.getPatientAgeLabel(), currentPatient.getAge() == 0 ? "" : ""+currentPatient.getAge(), lTextFieldPanel);
		symptomsField = createLabelTextFieldPair(StringResources.getPatientSymptomsLabel(),currentPatient.getSymptoms() == null ? "" : ""+ currentPatient.getSymptoms(), lTextFieldPanel);
		assignedDoctorField = createLabelTextFieldPair(StringResources.getPatientAssignedDoctorLabel(), currentPatient.getAssignedDoctor() == null ? "" : ""+currentPatient.getAssignedDoctor(), lTextFieldPanel);
		add(lTextFieldPanel);
		
		//create patient description text area
		desc = new JTextArea(currentPatient.getAdvice() == null ? "" : currentPatient.getAdvice());
		desc.setAutoscrolls(true);
		desc.setLineWrap(true);
		lPanelLayout.addLayoutComponent(desc, BorderLayout.CENTER);
		add(desc);
		
		//create patient save (create or update button)
		JButton saveButton = createPatientSaveButton();
		lPanelLayout.addLayoutComponent(saveButton, BorderLayout.SOUTH);
		this.add(saveButton);

		setLayout(lPanelLayout);
		updateUI();
	}

	private JButton createPatientSaveButton() {
		JButton saveButton = new JButton(isUpdatePanel ? StringResources.getPatientUpdateButtonLabel() : 
			StringResources.getPatientAddButtonLabel());
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//application convention is to map empty string to null (ie: if app has empty string - this will be null in db)
				currentPatient.setName(nameField.getText().equals("") ? null : nameField.getText() ); 
				currentPatient.setAge(ageField.getText().equals("") ? 0 : Integer.parseInt(ageField.getText()));
				currentPatient.setSymptoms(symptomsField.getText().equals("") ? null : symptomsField.getText());
				currentPatient.setAssignedDoctor(assignedDoctorField.getText().equals("") ? null : assignedDoctorField.getText());
				currentPatient.setAdvice(desc.getText().equals("")  ? null : desc.getText());
				if(isUpdatePanel) {
					BusinessComponentFactory.getInstance().getPatientProvider().updatePatient(currentPatient);
				}
				else {
					BusinessComponentFactory.getInstance().getPatientProvider().addPatient(currentPatient);
				}
			}
		});
		
		return saveButton;
	}

	private JTextField createLabelTextFieldPair(String label, String value, JPanel container) {
		
		JPanel pairPanel = new JPanel();
		JLabel jlabel = new JLabel(label);
		JTextField field = new JTextField(value);
		pairPanel.add(jlabel);
		pairPanel.add(field);

		container.add(pairPanel);

		BorderLayout lPairLayout = new BorderLayout();
		lPairLayout.addLayoutComponent(jlabel, BorderLayout.WEST);
		lPairLayout.addLayoutComponent(field, BorderLayout.CENTER);
		pairPanel.setLayout(lPairLayout);	
		
		return field;
	}

	/**
	 * Implementation of IPatientSelectionNotifiable::patientSelected used to switch patient
	 * displayed on PatientDisplayPanel
	 */
	@Override
	public void patientSelected(Patient patient) {
		initPatientDetails(patient);
	}
	
	/**
	 * Clear patient details panel
	 */
	public void refresh()
	{
		initPatientDetails(null);
	}
}
