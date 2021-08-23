package Presentation;

import java.util.Vector;

import Business.Patient;

/**
 * Encapsulates create/read/update/delete operations to database
 * @author IwanB
 *
 */
public interface IRepositoryProvider {
	/**
	 * check credentials is in the database and return their doctorId (or 0 if not known)
	 * @param userName : the userName of doctor credentials
	 * @param password : the password of doctor credentials
	 */
	public int checkUserCredentials(String userName, String password);
	
	/**
	 * Update the details for a given patient
	 * @param patient : the patient for which to update details
	 */
	public void updatePatient(Patient patient);
	
	/**
	 * Find the doctor associated patients as per the assignment description
	 * @param id the doctor id
	 * @return
	 */
	public Vector<Patient>  findPatientsByDoctor(int id);
	
	/**
	 * Find the associated patients based on the searchString provided as the parameter
	 * @param searchString: see assignment description search specification
	 * @return
	 */
	public Vector<Patient> findPatientsBasedOnName(String searchString);	
	
	/**
	 * Add the details for a new patient to the database
	 * @param patient: the new patient to add
	 */
	public void addPatient(Patient patient);
}
