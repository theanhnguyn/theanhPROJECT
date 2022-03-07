<%-- 
    Document   : search
    Created on : Feb 17, 2022, 10:30:25 PM
    Author     : FPTSHOP
--%>

<%@page import="model.Parent"%>
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
            ArrayList<Motel> motel = (ArrayList<Motel>) request.getAttribute("motel");
            ArrayList<Customer> customers = (ArrayList<Customer>) request.getAttribute("customers");
            ArrayList<Parent> parents = (ArrayList<Parent>) request.getAttribute("parents");
            int mid = (Integer) request.getAttribute("mid");
        %>
        <script>
            function submitForm()
            {
                document.getElementById("searchForm").submit();
            }

            function deleteStudent(id)
            {
                var result = confirm("Are you sure?");
                if (result)
                {
                    window.location.href = "delete?id=" + id;
                }
            }


        </script>
    </head>
    <body>
        <form action="search" method="GET" id="searchForm"> 
            Motel Floor: <select name="mid" onchange="submitForm();">
                <option value="-1" >Please select a motel floor</option>
                <% for (Motel m : motel) {
                %>
                <option 
                    <%=(m.getId() == mid) ? "selected=\"selected\"" : ""%>
                    value="<%=m.getId()%>"><%=m.getFloor()%> </option>
                <%}%>
            </select>
        </form>

        <table border="1px">
            <tr>
                <td>Id</td>
                <td>FirstName</td>
                <td>LastName</td>
                <td>Gender</td>
                <td>Dob</td>
                <td>Address</td>
                <td>Email</td>
                <td>Telephone</td>
                <td>Floor</td>
                <td></td>
            </tr>

            <% for (Customer c : customers) {
            %>
            <tr>
                <td><%=c.getId()%></td>
                <td><%=c.getFirstName()%></td>
                <td><%=c.getLastName()%></td>
                <td><%=c.isGender()%></td>
                <td><%=c.getDob()%></td>
                <td><%=c.getAddress()%></td>
                <td><%=c.getEmail()%></td>
                <td><%=c.getTelephone()%></td>
                <td><%=c.getMotel().getFloor()%></td>
                <td><a href="edit?id=<%=c.getId()%>">Edit</a> 
                    <a href="#" onclick="deleteStudent(<%=c.getId()%>)">Delete</a>
                </td>
            </tr>
            <%}%>
        </table> 
        <table border="1px">
            <tr>
                <td>Id</td>
                <td>ChildreId</td>
                <td>PhoneNumber</td>
                <td>Address</td>
                <td>Job</td>
                <td>Name</td>
                <td>Email</td>
                <td></td>
            </tr>

            <% for (Parent p : parents) {
            %>
            <tr>
                <td><%=p.getId()%></td>
                <td><%=p.getC().getId()%></td>
                <td><%=p.getPhonenumber()%></td>
                <td><%=p.getAddress()%></td>
                <td><%=p.getJob()%></td>
                <td><%=p.getPname()%></td>
                <td><%=p.getEmail()%></td>
                <td>

                </td>
            </tr>
            <%}%>
        </table> 
         
        <a href="pinsert">InsertP</a>  
        <a href="insert">Insert</a>  
        
    </body>
</html>
