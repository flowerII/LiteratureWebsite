<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../../public/css/bootstrap.min.css" />
	<script src="../../public/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../public/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
    <%
    String driverName="com.mysql.jdbc.Driver";
	String userName="root";
	String userPwd="suxx";
	String dbName="work";
	String url1="jdbc:mysql://localhost/"+dbName;
	String url2="?user="+userName+"&password="+userPwd;
	String url3="&useUnicode=true&characterEncoding=GB2312";
	String url=url1+url2+url3;
	Class.forName(driverName);
	Connection con=DriverManager.getConnection(url);
	String sql="select * from website_user";
	PreparedStatement prestmt=con.prepareStatement(sql);
	ResultSet rs=prestmt.executeQuery();
    %>
    <table border=1 class="table">
        <tr>
            <td>user_id</td><td>user_account</td><td>user_password</td><td>user_name</td><td>user_description</td><td>user_role</td>
        </tr>
        <% rs.beforeFirst();
           while(rs.next()){
        %>
        <tr align="center">
            <td><%=rs.getString("user_id") %></td>
            <td><%=rs.getString("user_account") %></td>
            <td><%=rs.getString("user_password") %></td>
            <td><%=rs.getString("user_name") %></td>
            <td><%=rs.getString("user_description") %></td>
            <td><%=rs.getString("user_role") %></td>
        </tr>
        <%} %>
    </table>
    <% 
    if(rs!=null)rs.close();
	if(prestmt!=null)prestmt.close();
	if(con!=null)con.close();
    %>
</body>
</html>