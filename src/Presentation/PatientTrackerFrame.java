package Presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import Business.BusinessComponentFactory;
import Business.Patient;

public class PatientTrackerFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5532618722097754725L;
	
	private AddEntitiesPanel addEntitiesPanel = null;
	private PatientDetailPanel detailPanel = null;
	private PatientSidePanel sidePanel = null;
	
	
	private int loggedInUserId = -1;

	/**
	 * Main patient tracker frame
	 * Logs on the user
	 * Initialises side panel + add entities panel + containing patient list + detail panel
	 */
	public PatientTrackerFrame()
	{
		setTitle(StringResources.getAppTitle());
	    setLocationRelativeTo(null);
	    
	    logOnUser();
	    
	    initialise();
	    
	    setDefaultCloseOperation(EXIT_ON_CLOSE);  
	}
	
	/**
	 *  !!! 
	 *  Only used to simulate logon
	 *  This should really be implemented using proper salted hashing
	 *	and compare hash to that in DB
	 *  really should display an error message for bad login as well
	 *	!!!
	 */
	private void logOnUser() {
		boolean OK = false;
		while (!OK) {		
				String user = (String)JOptionPane.showInputDialog(
									this,
									null,
									StringResources.getEnterUserNameString(),
									JOptionPane.QUESTION_MESSAGE);
				
				JPasswordField jpf = new JPasswordField();
				int okCancel = JOptionPane.showConfirmDialog(
									null,
									jpf,
									StringResources.getEnterPasswordString(),
									JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE);
				
				String password = null;
				if (okCancel == JOptionPane.OK_OPTION) {
					password = new String(jpf.getPassword());
				}

				if (user == null || password == null)
					System.exit(0);
				else
					if (!user.isEmpty() && !password.isEmpty()) {
						loggedInUserId = checkUserCredentials(user, password);
						if (loggedInUserId != 0)
							OK = true;
					}
		}
	}

	private void initialise()
	{
		addEntitiesPanel = new AddEntitiesPanel();	
	    detailPanel = new PatientDetailPanel(true);	    
	    sidePanel = getSidePanel(new PatientListPanel(getAllPatients()));
	    
	    BorderLayout borderLayout = new BorderLayout();
	    borderLayout.addLayoutComponent(addEntitiesPanel, BorderLayout.NORTH);
	    borderLayout.addLayoutComponent(sidePanel, BorderLayout.WEST);
	    borderLayout.addLayoutComponent(detailPanel, BorderLayout.CENTER);
	    
	    JButton  refreshButton = new JButton(StringResources.getRefreshButtonLabel());
	    final PatientTrackerFrame frame = this;
	    refreshButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.refresh(frame.getAllPatients());
			}
		});
	    
	    borderLayout.addLayoutComponent(refreshButton, BorderLayout.SOUTH);
	    
	    this.setLayout(borderLayout);
	    this.add(addEntitiesPanel);
	    this.add(refreshButton);
	    this.add(sidePanel);
	    this.add(detailPanel);
	    this.setSize(600, 300);
	}
	
	private PatientSidePanel getSidePanel(PatientListPanel listPanel)
	{
		final PatientTrackerFrame frame = this;
		listPanel.registerPatientSelectionNotifiableObject(detailPanel);
		return new PatientSidePanel(new ISearchEventListener() {
			@Override
			public void searchClicked(String searchString) {
				frame.refresh(frame.findPatientsByTitle(searchString));
			}
		},listPanel);
	}
	
	private int checkUserCredentials(String userName, String password)
	{
		return BusinessComponentFactory.getInstance().getPatientProvider().checkUserCredentials(userName, password);
	}
	
	private Vector<Patient> getAllPatients()
	{
		return BusinessComponentFactory.getInstance().getPatientProvider().findPatientsByDoctor(loggedInUserId);
	}
	
	private Vector<Patient> findPatientsByTitle(String pSearchString)
	{
		pSearchString = pSearchString.trim();
		if (!pSearchString.equals(""))
			return BusinessComponentFactory.getInstance().getPatientProvider().findPatientsBasedOnName(pSearchString);
		else
			return BusinessComponentFactory.getInstance().getPatientProvider().findPatientsByDoctor(loggedInUserId);
	}
	
	private  void refresh(Vector<Patient> patients)
	{
		if(sidePanel != null && detailPanel!= null)
		{
			sidePanel.refresh(patients);
			detailPanel.refresh();
			sidePanel.registerPatientSelectionNotifiableObject(detailPanel);
		}
	}
	
	
	public static void main(String[] args)
	{
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	PatientTrackerFrame ex = new PatientTrackerFrame();
                ex.setVisible(true);
            }
        });		
	}
}
