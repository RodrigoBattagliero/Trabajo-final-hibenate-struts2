<%-- 
    Document   : index
    Created on : 30-May-2015, 22:46:32
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>Tesorería</title>
    </head>
    <body>
        <div class="container">
		
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li class="active">Inicio</li>
            </ol>
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-header">Bienvenido <em><s:property value="#session.user.areas.nombre" ></s:property></em></h1>
                </div>
            </div>
         
            
            <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>
