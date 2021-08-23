package Business;

import java.util.Vector;

/**
 * Encapsulates any business logic to be executed on the app server; 
 * and uses the data layer for data queries/creates/updates/deletes
 * @author IwanB
 *
 */
public interface IPatientProvider {

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
	 * Find the patients associated in some way with a user
	 * Patients which have the id parameter below in any one or more of the
	 * creator, resolver, or verifier fields should be included in the result
	 * @param id
	 * @return
	 */
	public Vector<Patient>  findPatientsByDoctor(int id);
	
	/**
	 * Given an expression searchString like 'word' or 'this phrase', this method should return any patients 
	 * that contains this phrase in its name. 
	 * @param searchString: the searchString to use for finding patients in the database based on the patient names
	 * @return
	 */
	public Vector<Patient> findPatientsBasedOnName(String searchString);	
	
	/**
	 * Add the details for a new patient to the database
	 * @param patient: the new patient to add
	 */
	public void addPatient(Patient patient);
}
