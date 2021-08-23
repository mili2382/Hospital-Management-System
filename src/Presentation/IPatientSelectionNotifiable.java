package Presentation;

import Business.Patient;

/**
 * 
 * @author IwanB
 * 
 * Used to notify any interested object that implements this interface
 * and registers with PatientListPanel of an PatientSelection
 *
 */
public interface IPatientSelectionNotifiable {
	public void patientSelected(Patient patient);
}
