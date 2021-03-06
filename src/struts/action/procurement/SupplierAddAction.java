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

import struts.form.SupplierAddForm;

import dbconnection.MysqlCon;



/** 
 * MyEclipse Struts
 * Creation date: 02-06-2017
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SupplierAddAction extends ResultBasedAction {
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
		String msg="操作失败";
		SupplierAddForm supplieradd=(SupplierAddForm)form;
		String suppliername=supplieradd.getSuppliername();
		String suppliercode=supplieradd.getSuppliercode();
		String suppliermaster=supplieradd.getSuppliermaster();
		String supplieraddress=supplieradd.getSupplieraddress();
		String suppliertel=supplieradd.getSuppliertel();
		String supplieremail=supplieradd.getSupplieremail();
		String productrange=supplieradd.getProductrange();
		
		try {
			MysqlCon msq=new MysqlCon();
			Connection conn=msq.connection();
			ResultSet rs=msq.query("select * from amc.supplier where supplierName='"+suppliername+"'", conn);
			//rs.beforeFirst();
			if(rs!=null){
				msg="已有记录，操作失败";
				StringResult string=new StringResult(msg);
				conn.close();
			    return string;
			}
			else{
			msq.insert("insert into supplier(supplierName,supplierCode,supplierMaster,address,supplierTel,email,productRange) values('"+suppliername+"','"+suppliercode+"','"+suppliermaster+"','"+supplieraddress+"','"+suppliertel+"','"+supplieremail+"','"+productrange+"')");
			
			conn.close();
			msg="操作成功";
			StringResult string=new StringResult(msg);
		    return string;
			}
		} catch (SQLException e) {
			msg="数据库错误";
			// TODO Auto-generated catch block
			e.printStackTrace();
			StringResult string=new StringResult(msg);
		    return string;
		}
	
	}
	}
