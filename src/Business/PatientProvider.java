package Business;

import java.util.Vector;

import Data.RepositoryProviderFactory;

/**
 * Encapsulates any business logic to be executed on the app server; 
 * and uses the data layer for data queries/creates/updates/deletes
 * @author IwanB
 *
 */
public class PatientProvider implements IPatientProvider{

	/**
	 * check credentials is in the database and return their doctorId (or 0 if not known)
	 * @param userName : the userName of doctor credentials
	 * @param password : the password of doctor credentials
	 */
	@Override
	public int checkUserCredentials(String userName, String password) {
		return RepositoryProviderFactory.getInstance().getRepositoryProvider().checkUserCredentials(userName, password);
	}

	/**
	 * Update the details for a given patient
	 * @param patient : the patient for which to update details
	 */
	@Override
	public void updatePatient(Patient patient) {
		RepositoryProviderFactory.getInstance().getRepositoryProvider().updatePatient(patient);
	}

	/**
	 * Find the patients associated in some way with a doctor
	 * Patients associated with the doctor id parameter below should be included in the result
	 * @param id
	 * @return
	 */
	@Override
	public Vector<Patient> findPatientsByDoctor(int id) {		
		return RepositoryProviderFactory.getInstance().getRepositoryProvider().findPatientsByDoctor(id);
	}
	
	/**
	 * Add the details for a new patient to the database
	 * @param patient: the new patient to add
	 */
	@Override
	public void addPatient(Patient patient) {
		RepositoryProviderFactory.getInstance().getRepositoryProvider().addPatient(patient);
	}

	/**
	 * Given an expression searchString like 'word' or 'this phrase', this method should return any patients 
	 * that contains this phrase in its title.
	 * @param searchString: the searchString to use for finding patients in the database based on the patient titles
	 * @return
	 */
	@Override
	public Vector<Patient> findPatientsBasedOnName(String searchString) {
		return RepositoryProviderFactory.getInstance().getRepositoryProvider().findPatientsBasedOnName(searchString);

	}

}
