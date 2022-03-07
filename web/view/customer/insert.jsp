<%-- 
    Document   : insert
    Created on : Feb 9, 2022, 11:15:17 AM
    Author     : Sap-lap
--%>

 
<%@page import="model.Motel"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            ArrayList<Motel> motels = (ArrayList<Motel>) request.getAttribute("motels");
        %>
    </head>
    <body>
        <form action="insert" method="POST">
            Id: <input type="text" name="id"/> <br/>
            FirstName: <input type="text" name="firstName"/> <br/>
            LastName: <input type="text" name="lastName"/> <br/>
            Gender: <input type="radio" name="gender" value="male"/> Male 
            <input type="radio" name="gender" value="female"/> Female 
            <br/>
            Address: <input type="text" name="address"/> <br/>
            Email: <input type="text" name="email"/> <br/>
            Telephone: <input type="text" name="telephone"/> <br/>
            Dob: <input type="date" name="dob"/> <br/>
            Motel Floor :<select name="mid">
                <% for (Motel m : motels) {
                %>
                <option value="<%=m.getId()%>"><%=m.getFloor()%></option>
                <%}%>
            </select>
            <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
