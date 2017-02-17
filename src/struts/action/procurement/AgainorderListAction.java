/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package struts.action.procurement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dbconnection.MysqlCon;
import entity.Againorder;



/** 
 * MyEclipse Struts
 * Creation date: 02-04-2017
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class AgainorderListAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<Againorder> againorderlist=new ArrayList<Againorder>();
		int ids=0;
		try{
			MysqlCon msq=new MysqlCon();
			Connection conn=msq.connection();
			ResultSet rs=msq.query("select againorder.againOrderID,user.userName,againorder.againorderTime,againorder.againOrderState from amc.againorder,amc.user where againorder.userID=user.userID", conn);
		    rs.beforeFirst();
			while(rs.next()){
				ids=ids+1;
				Againorder againorder=new Againorder();
				againorder.setNumber(ids+"");
				againorder.setAgainorderid(rs.getInt(1)+"");
			    againorder.setUsername(rs.getString(2));
				againorder.setAgainordertime(rs.getDate(3)+"");
				againorder.setAgainorderstate(rs.getString(4));
				againorderlist.add(againorder);
			}
			request.setAttribute("againorderlist", againorderlist);
			conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return mapping.findForward("againorderlist");
	}
}