/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package struts.action.storage;

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
import entity.Product;

/** 
 * MyEclipse Struts
 * Creation date: 02-07-2017
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ProductListAction extends Action {
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
		List<Product> productlist=new ArrayList<Product>();
		int ids=0;
		boolean isout=true;
		try{
			MysqlCon msq=new MysqlCon();
			Connection conn=msq.connection();
			ResultSet rs=msq.query("select * from amc.product", conn);
			rs.beforeFirst();
			while(rs.next()){
				ids=ids+1;
				Product product=new Product();
				product.setIds(ids+"");
				product.setProductid(rs.getInt(1)+"");
				product.setProductname(rs.getString(2));
				product.setProductsize(rs.getString(3));
				product.setSaleprice(rs.getFloat(4)+"");
				product.setAmount(rs.getFloat(5)+"");
				product.setSafenum(rs.getFloat(7)+"");
				productlist.add(product);
				if(rs.getFloat(5)<rs.getFloat(7)&&rs.getString(8).equals("����")){
					isout=false;
				}
			}
			request.setAttribute("productlist", productlist);
			System.out.println(isout);
			request.setAttribute("isout", isout+"");
			conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("productlist");
	}
}