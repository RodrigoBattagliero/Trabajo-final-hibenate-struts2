<%-- 
    Document   : menu
    Created on : May 12, 2015, 3:37:37 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="masthead">
                <nav>
                    <ul class="nav nav-justified">
                        <li><s:a action="/Administrativo/index">oficina de gestión de gasto de traslado docente</s:a></li>
                        <li><s:a action="/Administrativo/index">sede interior </s:a> </li>
                        <li><s:a action="/Bedel/index">dirección académica administrativa</s:a></li>
                        <li><s:a action="/RendicionDeCuentas/index">rendición de cuentas</s:a></li>
                        <li><s:a action="/SecretariaAdministrativoFinanciera/index">secretaría administrativo-financiera</s:a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </body>
</html>
