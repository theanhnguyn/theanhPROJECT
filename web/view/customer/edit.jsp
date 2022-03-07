<%-- 
    Document   : edit
    Created on : Feb 11, 2022, 11:09:48 AM
    Author     : SAP-LAP-FPT
--%>

<%@page import="model.Customer"%>
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
            Customer c = (Customer) request.getAttribute("customer");
        %>
    </head>
    <body>
        <form action="edit" method="POST">
            Id: <%=c.getId()%> <input type="hidden" name="id" value="<%=c.getId()%>" /> <br/>
            FirstName: <input type="text" name="firstname" value="<%=c.getFirstName()%>"/> <br/>
            LastName: <input type="text" name="lastname" value="<%=c.getLastName()%>"/> <br/>
            Gender: <input type="radio" 
                           <%if (c.isGender()) {%>
                           checked="checked"
                           <%}%>
                           name="gender" value="male"/> Male 
            <input type="radio" 
                   <%=!c.isGender() ? "checked=\"checked\"" : ""%>
                   name="gender" value="female"/> Female 
            <br/>
            Dob: <input type="date" name="dob" value="<%=c.getDob()%>" /> <br/>
            Address: <input type="text" name="address" value="<%=c.getAddress()%>"/> <br/>
            Email <input type="text" name="email" value="<%=c.getEmail()%>"/> <br/>
            Telephone <input type="number" name="telephone" value="<%=c.getTelephone()%>"/> <br/>
            Motel Floor: <select name="mid">
                <% for (Motel m : motels) {
                %>
                <option
                    <%=(m.getId() == c.getMotel().getId()) ? "selected=\"selected\"" : ""%>
                    value="<%=m.getId()%>"><%=m.getFloor()%></option>
                <%}%>
            </select>
            <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
