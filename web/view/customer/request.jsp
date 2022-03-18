<%-- 
    Document   : insertC
    Created on : Mar 14, 2022, 2:49:43 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "e" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="../js/pagger.js" type="text/javascript"></script>
        <link href="../css/pagger.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        <% Integer pageindex = (Integer) request.getAttribute("pageindex");
            Integer totalpage = (Integer) request.getAttribute("totalpage");
        %>
    </head>
    <body>
        <div id="containertop" class="pagger">  </div>
        <form action="request" method="POST">
            <table border="1">
                <tbody>
                    <tr>
                        <td> </td>
                        <c:forEach items="${requestScope.requests}" var ="r">
                            <td>${r.rname}</td>
                        </c:forEach>
                    </tr>
                    <c:forEach items="${requestScope.customers}" var ="c">
                        <tr>
                            <td>${c.id}
                                <input type="hidden" name="cid" value="${c.id}"/>
                            </td>
                            <c:forEach items="${requestScope.requests}" var ="r">

                                <td><input type="checkbox"
                                           <c:forEach items="${requestScope.cus_res}" var ="cr">
                                               <c:if test="${cr.cid == c.id && cr.rid == r.rid}">
                                                   checked="checked"
                                               </c:if>
                                           </c:forEach>
                                           name="rid" value="check" /></td>
                                </c:forEach>

                        </tr>
                    </c:forEach>

                </tbody>
            </table>


            <div id="containerbot" class="pagger">  </div>
            <input type="submit" value="Save"/>
            <script>
                 pagger("containertop",<%=pageindex%>,<%=totalpage%>, 3);
                 pagger("containerbot",<%=pageindex%>,<%=totalpage%>, 3);
            </script>
            <a href="welcome">Home</a>
        </form>
    </body>
</html>
