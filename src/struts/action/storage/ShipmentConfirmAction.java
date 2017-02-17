/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package struts.action.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.action.Result;
import struts.action.ResultBasedAction;
import struts.action.StringResult;
import dbconnection.MysqlCon;
import orm.User;

/** 
 * MyEclipse Struts
 * Creation date: 02-04-2017
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ShipmentConfirmAction extends ResultBasedAction {
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
		String msg="����ʧ��";
		String pickingid=request.getParameter("id");
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		Integer userid=user.getUserId();
		Date time=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String parcelsdate=sdf.format(time);
		MysqlCon msq=new MysqlCon();
		msq.update("update picking set pickingState='�Ѵ���' where pickingID="+pickingid);
		msq.insert("insert into amc.parcels(pickingID,userID,parcelsDate) values("+pickingid+","+userid+",'"+parcelsdate+"')");
		Connection conn=msq.connection();
		ResultSet rs=msq.query("select parcelsID from amc.parcels where parcels.pickingID="+pickingid, conn);
		int parcelsid=0;
		try {
			rs.beforeFirst();
			while(rs.next()){
				parcelsid=rs.getInt(1);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msq.insert("insert into receivable(parcelsID,drawBillDate,payState,userID) values("+parcelsid+",'"+parcelsdate+"','δ����',"+userid+")");
		int receivableid=0;
		conn=msq.connection();
		rs=msq.query("select receivableID from amc.receivable where parcelsID="+parcelsid, conn);
		try {
			rs.beforeFirst();
			while(rs.next()){
				receivableid=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs=msq.query("select pickingdetail.productID,product.productName,product.salePrice,pickingdetail.pickingNum from amc.pickingdetail,amc.product where pickingdetail.productID=product.productID and pickingdetail.pickingID="+pickingid, conn);
		float total=0;
		try {
			rs.beforeFirst();
			while(rs.next()){
				total=total+rs.getFloat(3)*rs.getFloat(4);
				System.out.println("insert into receivabledetail(receivableID,productID,productName,salePrice,saleAmount,totalAccount) values("+receivableid+","+rs.getInt(1)+",'"+rs.getString(2)+"',"+rs.getFloat(3)+","+rs.getFloat(4)+","+rs.getFloat(3)*rs.getFloat(4)+")");
				msq.insert("insert into amc.receivabledetail(receivableID,productID,productName,salePrice,saleAmount,totalAccount) values("+receivableid+","+rs.getInt(1)+",'"+rs.getString(2)+"',"+rs.getFloat(3)+","+rs.getFloat(4)+","+rs.getFloat(3)*rs.getFloat(4)+")");
			}
			conn.close();
			msg="�����ɹ���";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msq.update("update amc.receivable set receivable.totalAccount="+total+" where receivableID="+receivableid);
		StringResult string=new StringResult(msg);
		return string;
	}
}