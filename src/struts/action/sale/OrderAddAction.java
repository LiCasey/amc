/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package struts.action.sale;

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

import entity.Customer;
import entity.Product;
import entity.User;

/** 
 * MyEclipse Struts
 * Creation date: 01-30-2017
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class OrderAddAction extends Action {
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
		// TODO Auto-generated method stub
		List<Customer> customerlist=new ArrayList<Customer>();
		ResultSet rs=null;
		int ids=0;
		try{
			MysqlCon msq=new MysqlCon();
			Connection conn=msq.connection();
		    rs=msq.query("select customerName from customer", conn);
		    rs.beforeFirst();
		    while(rs.next()){
		    	 Customer customer=new Customer();
			     customer.setCustomername(rs.getString(1));
			     customerlist.add(customer);
		    }
		    conn.close();   
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Product> productlist=new ArrayList<Product>();
		rs=null;
		try{
			MysqlCon msq=new MysqlCon();
			Connection conn=msq.connection();
		    rs=msq.query("select productID,productName,productSize,salePrice,amount from product", conn);
		    rs.beforeFirst();
		    while(rs.next()){
		    	ids=ids+1;
		    	Product product=new Product();
		    	product.setIds(ids+"");
		    	product.setProductid(rs.getString(1));
		    	product.setProductname(rs.getString(2));
		    	product.setProductsize(rs.getString(3));
		    	product.setSaleprice(rs.getFloat(4)+"");
		    	product.setAmount(rs.getFloat(5)+"");
		    	productlist.add(product);
		    }
		    conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("customerlist", customerlist);
		request.setAttribute("productlist", productlist);
		return mapping.findForward("orderadd");
	}
}