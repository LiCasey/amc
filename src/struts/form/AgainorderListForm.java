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
 * Creation date: 02-05-2017
 * 
 * XDoclet definition:
 * @struts.form name="againorderListForm"
 */
public class AgainorderListForm extends ActionForm {
	
	private String number;
	private String againorderid;
	private String username;
	private String againordertime;
	private String againorderstate;
	
	
	/*
	 * Generated Methods
	 */

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAgainorderid() {
		return againorderid;
	}

	public void setAgainorderid(String againorderid) {
		this.againorderid = againorderid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAgainordertime() {
		return againordertime;
	}

	public void setAgainordertime(String againordertime) {
		this.againordertime = againordertime;
	}

	public String getAgainorderstate() {
		return againorderstate;
	}

	public void setAgainorderstate(String againorderstate) {
		this.againorderstate = againorderstate;
	}

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
}