<%@page import="cn.swu.edu.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body><center>
	<% Cookie[] cc = request.getCookies();
	   boolean has = false;
	    for(Cookie c:cc){
        	if(c.getName().equals("username")){
        	has=true;
    	}
  	}	  	
	if(has==false){
    	response.sendRedirect("Login.jsp");}
%>
        <form action="query.do" method="post">
                <table>
                    <tr>
                        <td>name:</td>
                        <td><input type="text" name="name"/></td>
                    </tr>
                    <tr>
                        <td>address:</td>
                        <td><input type="text" name="address"/></td>
                    </tr>
                    <tr>
                        <td>phone:</td>
                        <td><input type="text" name="phone"/></td>
                    
                    <tr>
                        <td><input type="submit" value="Query"/></td>
                        <td><a href="">Add new Customer</a></td>
                    </tr>
                </table>
        </form>
        <br><br>
        
        <%
            List<Customer> customers = (List<Customer>) request.getAttribute("customers");
            if(customers != null && customers.size() > 0){
        %>
        <hr>
        <br><br>
        <table border="1" cellpadding="10" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>ADDRESS</th>
                <th>PHONE</th>
                <th>UPDATE/DELETE</th>
            </tr>
            <% for(Customer cust : customers){
            
            %>
            <tr>
                <td><%= cust.getId() %></td>
                <td><%= cust.getName() %></td>
                <td><%= cust.getAddress() %></td>
                <td><%= cust.getPhone() %></td>
                <td>
                    <a href="">Update</a>
                    <a href="delete.do?id=<%=cust.getId() %>" class="delete">Delete</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            }
        %></center>
</body>
</html>

