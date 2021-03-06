/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package struts.action.procurement;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.action.Result;
import struts.action.ResultBasedAction;
import struts.action.StringResult;

import struts.form.SupplierUpdateForm;

import dbconnection.MysqlCon;


/** 
 * MyEclipse Struts
 * Creation date: 02-06-2017
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SupplierUpdate2Action extends ResultBasedAction {
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
	

	@Override
	public Result execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request) throws Exception {
		String msg="操作错误";
		String supplierid=request.getParameter("id");
		SupplierUpdateForm supplierupdtf=(SupplierUpdateForm)form;
		String suppliername="";
		String suppliercode="";
		String suppliermaster="";
		String supplieraddress="";
		String suppliertel="";
		String supplieremail="";
		String productrange="";
		suppliername=supplierupdtf.getSuppliername();
		suppliercode=supplierupdtf.getSuppliercode();
		suppliermaster=supplierupdtf.getSuppliermaster();
		supplieraddress=supplierupdtf.getSupplieraddress();
		suppliertel= supplierupdtf.getSuppliertel();
		supplieremail=supplierupdtf.getSupplieremail();
		productrange= supplierupdtf.getProductrange();
		
		try {
			MysqlCon msq=new MysqlCon();
			Connection conn=msq.connection();
			msq.update("update supplier set supplierName='"+suppliername+"', supplierCode='"+suppliercode+"', supplierMaster='"+suppliermaster+"', address='"+supplieraddress+"', supplierTel='"+suppliertel+"', email='"+supplieremail+"', productRange='"+productrange+"' where supplierID="+supplierid);
			msg="操作成功";
			conn.close();
			StringResult string=new StringResult(msg);
			return string;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg="数据库错误";
			StringResult string=new StringResult(msg);
			return string;
		}
		
		
	}
}