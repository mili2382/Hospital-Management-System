package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

import org.postgresql.ds.PGSimpleDataSource;
import Business.Patient;
import Presentation.IRepositoryProvider;

/**
 * Encapsulates create/read/update/delete operations to PostgreSQL database
 * @author IwanB
 */
public class PostgresRepositoryProvider implements IRepositoryProvider {
	// connection parameters - ENTER YOUR LOGIN AND PASSWORD HERE
	
   //private final String userid = "y20s2c9120_mili2382";
   //private final String passwd = "520569";
   //private final String myHost = "soit-db-pro-2.ucc.usyd.edu.au";
    private final String userid = "postgres";
    private final String passwd = "520569";
    private final String myHost = "jdbc:postgresql://localhost:5432/A3";
    // instance variable for the database connection   
  

	private Connection openConnection() throws SQLException
	{
		
		//PGSimpleDataSource source = new PGSimpleDataSource();
		//source.setServerName(myHost);
		//source.setDatabaseName(userid);
		//source.setUser(userid);
		//source.setPassword(passwd);
		//Connection conn = source.getConnection();
	      Connection conn = null;
	      try {
	        
	         conn = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/A3",
	            "postgres", "520569");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }

	    
	    return conn;
	}

	/**
	 * Validate a user login request
	 * @param userName: the user userName trying to login
	 * @param password: the user password used to login
	 * @return userId for user (return 0 if userName not found or invalid)
	 */
	@Override
	public int checkUserCredentials(String userName, String password) {
	    //TODO - validate a user login request
		// initial reuturnvalue=0 and it equals to userID
		   int returnvalue=0;
	       try
	       {
	          /* prepare a dynamic query statement */
	          PreparedStatement stmt = openConnection().prepareStatement(
	                                    "SELECT PASSWORD, DOCTORID  FROM DOCTORS WHERE USERNAME=? ");
	          stmt.setString(1, userName);
	     
	          /* execute the query and loop through the resultset */
	          ResultSet rset = stmt.executeQuery(); 
	          while ( rset.next() )
	          {
	          if(password.equals(rset.getString("PASSWORD")) )
	          {
	        	        System.out.println("Success Log in");
	                    returnvalue=rset.getInt("DOCTORID");
	                    
	          }
	      
	          }

	          
	          
	                 
	          /* clean up! (NOTE this really belongs in a finally{} block) */
	          stmt.close();
	       }
	       catch (SQLException sqle) 
	       {  
	           /* error handling */
	           System.out.println("SQLException : " + sqle);
	       }
	       
	          return returnvalue;
	        	  
		
	}

	/**
	 * Find associated patients given a doctorId as per the assignment description
	 * @param userId is the doctor id
	 * @return
	 */
	@Override
	public Vector<Patient> findPatientsByDoctor(int userId) {
	
		
		
		// TODO - list all patients associated with a doctor in DB	
		// the following code illustrates the structure of Patient
		
		Vector<Patient> results = new Vector<Patient>();
		
	       try
	       {
	          /* prepare a dynamic query statement */
	          PreparedStatement stmt = openConnection().prepareStatement(
	        		  "SELECT PATIENTID, NAME, AGE, SYMPTOMS, DOCTORID, ADVICE "
	        		  + " FROM doctors join patients on doctors.DOCTORID=patients.ASSIGNED_DOCTOR "
	        				  +"WHERE DOCTORID=?"
	        				  +"ORDER BY NAME");
	          stmt.setInt(1, userId);
	     
	          /* execute the query and loop through the resultset */
	          ResultSet rset = stmt.executeQuery(); 
	          int nr = 0;
	          while ( rset.next() )
	          {
	             nr++;
	             Patient patient = new Patient();
	             patient.setId(rset.getInt("PATIENTID"));
	             patient.setName(rset.getString("NAME"));
	             patient.setAge(rset.getInt("AGE"));
	             patient.setSymptoms(rset.getString("SYMPTOMS"));
	             patient.setAssignedDoctor(rset.getString("DOCTORID"));
	             patient.setAdvice(rset.getString("ADVICE"));
	             results.add(patient); 
	          }
	          
	              
	          if ( nr == 0 )
	             System.out.println("No entries found.");
	                 
	          /* clean up! (NOTE this really belongs in a finally{} block) */
	          stmt.close();
	       }
	       catch (SQLException sqle) 
	       {  
	           /* error handling */
	           System.out.println("SQLException : " + sqle);
	       }
	       
	       
		return results;
	}

	/**
	 * Find a list of patients based on the searchString provided as parameter
	 * @param searchString: see assignment description search specification
	 * @return
	 */
	@Override
	public Vector<Patient> findPatientsBasedOnName(String searchString) {
		Vector<Patient> results = new Vector<Patient>();

		// TODO - find a list of patients in DB based on search input
	       try
	       {
	          /* prepare a dynamic query statement */
	          PreparedStatement stmt = openConnection().prepareStatement(
	        		  "SELECT PATIENTID, NAME, AGE, SYMPTOMS, DOCTORID, ADVICE "
	        		  + " FROM doctors join patients on doctors.DOCTORID=patients.ASSIGNED_DOCTOR "
	        				  +"WHERE NAME iLIKE ? or FIRSTNAME iLIKE ? OR LASTNAME iLIKE ? "
	        				  +"ORDER BY NAME");
	          stmt.setString(1, "%"+searchString+"%");
	          stmt.setString(2, "%"+searchString+"%");
	          stmt.setString(3, "%"+searchString+"%");
	     
	          /* execute the query and loop through the resultset */
	          ResultSet rset = stmt.executeQuery(); 
	          int nr = 0;
	          while ( rset.next() )
	          {
	             nr++;
	             Patient patient = new Patient();
	             patient.setId(rset.getInt("PATIENTID"));
	             patient.setName(rset.getString("NAME"));
	             patient.setAge(rset.getInt("AGE"));
	             patient.setSymptoms(rset.getString("SYMPTOMS"));
	             patient.setAssignedDoctor(rset.getString("DOCTORID"));
	             patient.setAdvice(rset.getString("ADVICE"));
	             results.add(patient); 
	          }
	          
	              
	          if ( nr == 0 )
	             System.out.println("No entries found.");
	                 
	          /* clean up! (NOTE this really belongs in a finally{} block) */
	          stmt.close();
	       }
	       catch (SQLException sqle) 
	       {  
	           /* error handling */
	           System.out.println("SQLException : " + sqle);
	       }

		          return results;
	}

	/**
	 * Add the details for a new patient into the Database
	 * @param patient: the new patient to add
	 */
	@Override
	public void addPatient(Patient patient) {
	    //TODO - add a new patient into DB
            String s1="-";    
	       try
	       {
	          /* prepare a dynamic query statement */
	          PreparedStatement stmt = openConnection().prepareStatement(
	        		  "INSERT INTO PATIENTS(NAME ,AGE ,SYMPTOMS ,ASSIGNED_DOCTOR ) VALUES(?,?,?,?) ");
	          if (patient.getAssignedDoctor().equals(s1))
                    patient.setAssignedDoctor("1");
	          
	          stmt.setString(1, patient.getName());
	          stmt.setInt(2, patient.getAge());
	          stmt.setString(3, patient.getSymptoms());
	          stmt.setInt(4, Integer.parseInt(patient.getAssignedDoctor()));
	          
	     
	          /* execute the query and loop through the resultset */
	          ResultSet rset = stmt.executeQuery(); 
	   
	          
	                 
	          /* clean up! (NOTE this really belongs in a finally{} block) */
	          stmt.close();
	       }
	       catch (SQLException sqle) 
	       {  
	           /* error handling */
	           System.out.println("SQLException : " + sqle);
	       }
		
		
	}

	/**
	 * Update the details for a given patient
	 * @param patient: the patient for which to update details
	 */
	@Override
	public void updatePatient(Patient patient) {
		//TODO - update the patient in DB
		         String s1="-";    

	       try
	       {
	          /* prepare a dynamic query statement */
	          PreparedStatement stmt = openConnection().prepareStatement(
	        		  "UPDATE PATIENTS SET NAME =?, AGE=?, SYMPTOMS =?,ASSIGNED_DOCTOR =?, ADVICE =? "+
	          "WHERE PATIENTID=?");
		      if (patient.getAssignedDoctor().equals(s1))
	              patient.setAssignedDoctor("1");
	          stmt.setString(1, patient.getName());
	          stmt.setInt(2, patient.getAge());
	          stmt.setString(3, patient.getSymptoms());
	          stmt.setInt(4, Integer.parseInt(patient.getAssignedDoctor()));
	          stmt.setString(5, patient.getAdvice());
	          stmt.setInt(6, patient.getId());
	          
	     
	          /* execute the query and loop through the resultset */
	          ResultSet rset = stmt.executeQuery(); 
	   
	          
	                 
	          /* clean up! (NOTE this really belongs in a finally{} block) */
	          stmt.close();
	       }
	       catch (SQLException sqle) 
	       {  
	           /* error handling */
	           System.out.println("SQLException : " + sqle);
	       }
		
	}
}
