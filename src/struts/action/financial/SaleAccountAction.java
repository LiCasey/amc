/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package struts.action.financial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import orm.Saleaccount;
import dbconnection.MysqlCon;

/** 
 * MyEclipse Struts
 * Creation date: 02-11-2017
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SaleAccountAction extends Action {
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
	 * @throws SQLException 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		MysqlCon mysqlCon = new MysqlCon();
		Connection conn = mysqlCon.connection();
		ResultSet rs = mysqlCon.query("select saleAccountID, receivableID, addTime, totalAccount, staffName from saleaccount,user,staff where user.userID=saleaccount.userID and staff.staffID=user.staffID", conn);
		List saleAccountList = new ArrayList();
		int counter=0;
		do{
			int receivableID = rs.getInt(2);
			Timestamp addTime = rs.getTimestamp(3);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String time = formatter.format(addTime);
			float totalAccount = rs.getFloat(4);
			String userName = rs.getString(5);
			Saleaccount saleaccount = new Saleaccount(receivableID, userName, time, totalAccount);
			saleaccount.setSaleaccountId(++counter);
			saleAccountList.add(saleaccount);
		}while(rs.next());
		
		request.getSession().setAttribute("saleAccountList", saleAccountList);
		
		return mapping.findForward("saleAccount");
	}
}