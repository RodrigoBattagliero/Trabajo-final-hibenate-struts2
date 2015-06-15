<%-- 
    Document   : docentes
    Created on : 13-Jun-2015, 03:30:54
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tbody>
                <s:iterator value="entities" var="v">
                    <tr>
                        <td><s:property value="#v.nombre" />,<s:property value="v.apellido" /></td>
                        <td><s:property value="#v.documento" /></td>
                        <td><s:property value="#v.residencia" /></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </body>
</html>
