<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../Includes/DBConn.jsp"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Date"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE html>
<html>
  <head>
      
    <title>菜单</title>
   
	
     <SCRIPT LANGUAGE="Javascript" SRC="FusionCharts/FusionCharts.js"></SCRIPT>     
    
    
  </head>
  
  <body >

          <h2 class="sub-header">客户购买情况</h2>
    <%
           String strDataURL="statistics/UserData.jsp";
            
            //Create the chart - Pie 3D Chart with dataURL as strDataURL
            %> 
            <jsp:include page="../Includes/FusionChartsRenderer.jsp" flush="true"> 
                <jsp:param name="chartSWF" value="FusionCharts/FCF_Bar2D.swf" /> 
                <jsp:param name="strURL" value="<%=strDataURL%>" /> 
                <jsp:param name="strXML" value="" /> 
                <jsp:param name="chartId" value="userpurchase" /> 
                <jsp:param name="chartWidth" value="500" /> 
                <jsp:param name="chartHeight" value="300" />
                <jsp:param name="debugMode" value="false" /> 
                <jsp:param name="registerWithJS" value="false" />
            </jsp:include>
        
    
   
    
  </body>
</html>