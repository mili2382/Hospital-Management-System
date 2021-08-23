package Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Business.Patient;

/**
 * Panel encapsulating patient list
 * @author IwanB
 *
 */
public class PatientListPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1013855025757989473L;
	
	private List<IPatientSelectionNotifiable> notifiables = new ArrayList<IPatientSelectionNotifiable>();
	private Vector<Patient> patients;
	
	/**
	 * 
	 * @param patients vector of patients to display in the patient list panel
	 */
	public PatientListPanel(Vector<Patient> patients)
	{
		this.patients = patients;
		this.setBorder(BorderFactory.createLineBorder(Color.black));	
		initList(this.patients);
	}

	private void initList(Vector<Patient> patients) {
		
		final JList<Patient> list = new JList<Patient>(patients);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		JScrollPane listScroller = new JScrollPane(list);
		this.add(listScroller);
		BorderLayout listLayout = new BorderLayout();
		listLayout.addLayoutComponent(listScroller, BorderLayout.CENTER);
		this.setLayout(listLayout);		
		list.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e) {
				for(IPatientSelectionNotifiable notifiable : notifiables)
				{
					Patient selectedPatient = list.getSelectedValue();
					if(selectedPatient != null)
					{
						notifiable.patientSelected(selectedPatient);
					}
				}
			}		
		});
	}
	
	/**
	 * Refresh patient list to display vector of patient objects
	 * @param patients - vector of patient objects to display
	 */
	public void refresh(Vector<Patient> patients)
	{
		this.removeAll();
		this.initList(patients);
		this.updateUI();
		this.notifiables.clear();
	}
	
	/**
	 * Register an object to be notified of a patient selection change
	 * @param notifiable object to invoke when a new patient is selected
	 */
	public void registerPatientSelectionNotifiableObject(IPatientSelectionNotifiable notifiable)
	{
		notifiables.add(notifiable);
	}

}
