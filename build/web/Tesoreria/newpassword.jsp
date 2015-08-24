<%-- 
    Document   : newpassword
    Created on : 28-Jul-2015, 02:35:57
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
            <h1 class="page-header">Cambiar contraseña</h1>
            <s:actionerror />
            <s:fielderror />
            <s:actionmessage />
            <div class="row">
               <div class="col-sm-2"></div>
                <div class="col-sm-8">
                    <s:form action="newPassword" theme="simple">
                        <div class="form-group">
                            <label>Contraseña actual</label>
                            <s:password name="password" class="form-control"></s:password>
                        </div>
                        <div class="form-group">
                            <label>Contraseña nueva</label>
                            <s:password name="passwordNueva" class="form-control"></s:password>
                        </div>
                        <div class="form-group">
                            <label>Repetir contraseña nueva</label>
                            <s:password name="passwordNueva2" class="form-control"></s:password>
                        </div>
                        <div class="form-group">
                            <s:submit value="Cambiar contraseña" class="btn btn-primary" />
                        </div>
                    </s:form>
                </div>
                <div class="col-sm-2"></div>
            </div>
            <%@include file="partes/footer.jsp" %>
        </div>
    </body>
</html>
