/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 02-10-2017
 * 
 * XDoclet definition:
 * @struts.form name="receivableReminderForm"
 */
public class ReceivableReminderForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** userID property */
	private int userID;

	/** receivableID property */
	private int receivableID;
	
	private int currentUser;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/** 
	 * Returns the userID.
	 * @return int
	 */
	public int getUserID() {
		return userID;
	}

	/** 
	 * Set the userID.
	 * @param userID The userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/** 
	 * Returns the receivableID.
	 * @return int
	 */
	public int getReceivableID() {
		return receivableID;
	}

	/** 
	 * Set the receivableID.
	 * @param receivableID The receivableID to set
	 */
	public void setReceivableID(int receivableID) {
		this.receivableID = receivableID;
	}

	public int getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(int currentUser) {
		this.currentUser = currentUser;
	}
}