<%-- 
    Document   : pedit
    Created on : Mar 8, 2022, 12:52:51 AM
    Author     : FPTSHOP
--%>

<%@page import="model.Parent"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
              Parent  p = (Parent) request.getAttribute("parent");
        %>
    </head>
    <body>
          <form  action="../parent/edit" method="POST">
            Id: <%=p.getId()%> <input type="hidden" name="pid" value="<%=p.getId()%>" /> <br/>
            Children ID :<input type ="text" name="cid" value="<%=p.getC().getId()%>"/><br/>
            Name: <input type="text" name="name"value="<%=p.getPname()%>"/> <br/>
            Address: <input type="text" name="address"value="<%=p.getAddress()%>"/> <br/>
            Email: <input type="text" name="email"value="<%=p.getEmail()%>"/> <br/>
            Phone Number <input type="text" name="phonenumber"value="<%=p.getPhonenumber()%>"/> <br/>
            Job: <input type="text" name="job"value="<%=p.getJob()%>"/> <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
