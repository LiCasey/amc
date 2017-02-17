/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package struts.action.storage;

import java.io.UnsupportedEncodingException;
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
import entity.PickingDetail;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2017
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class PickingOperationAction extends Action {
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
		String pickingid=request.getParameter("id");
		String pickingstate=request.getParameter("state");
		try {
			pickingstate=new String(pickingstate.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			MysqlCon msq=new MysqlCon();
			Connection conn=msq.connection();
			ResultSet rs=msq.query("select picking.orderID,picking.pickingTime,order.orderDate,customer.customerName,order.receiveMan,order.receiveAddress from amc.order,amc.picking,amc.customer where picking.orderID=order.orderID and order.customerID=customer.customerID and pickingID="+pickingid, conn);
			rs.beforeFirst();
			while(rs.next()){
				request.setAttribute("pickingid", pickingid);
				request.setAttribute("orderid", rs.getInt(1)+"");
				request.setAttribute("pickingtime", rs.getDate(2)+"");
				request.setAttribute("ordertime", rs.getDate(3)+"");
				request.setAttribute("customername", rs.getString(4));
				request.setAttribute("receiveman", rs.getString(5));
				request.setAttribute("receiveaddress", rs.getString(6));
			}
			conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PickingDetail> pickingdetail=new ArrayList<PickingDetail>();
		int id=0;
		try{
			MysqlCon msq=new MysqlCon();
			Connection conn=msq.connection();
			ResultSet rs=msq.query("select pickingdetail.productID,product.productName,product.productSize,supplier.supplierCode,pickingdetail.pickingNum from amc.pickingdetail,amc.product,amc.supplier where pickingdetail.productID=product.productID and product.supplierID=supplier.supplierID and pickingdetail.pickingID="+pickingid, conn);
			rs.beforeFirst();
			while(rs.next()){
				PickingDetail pdetail=new PickingDetail();
				id=id+1;
				pdetail.setIds(id+"");
				pdetail.setProductid(rs.getInt(1)+"");
				pdetail.setProductname(rs.getString(2));
				pdetail.setProductsize(rs.getString(3));
				pdetail.setSuppliercode(rs.getString(4));
				pdetail.setPickingdetailnum(rs.getFloat(5)+"");
				pickingdetail.add(pdetail);
			}conn.close();
		    request.setAttribute("pickingdetail", pickingdetail);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(pickingstate);
		if(pickingstate.equals("δ����")){
			return mapping.findForward("pickingoperation");
		}
		else{
			return mapping.findForward("pickingview");
		}
	}
}