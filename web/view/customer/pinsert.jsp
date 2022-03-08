<%-- 
    Document   : pinsert
    Created on : Mar 4, 2022, 11:05:50 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="../parent/insert" method="POST">
            Id: <input type="text" name="pid"/> <br/>
            Children ID :<input type ="text" name="cid"><br/>
            Name: <input type="text" name="name"/> <br/>
            Address: <input type="text" name="address"/> <br/>
            Email: <input type="text" name="email"/> <br/>
            Phone Number <input type="text" name="phonenumber"/> <br/>
            Job: <input type="text" name="job"/> <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
