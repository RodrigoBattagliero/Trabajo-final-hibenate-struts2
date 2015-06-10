<%-- 
    Document   : Login
    Created on : 27-May-2015, 22:18:34
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
            <div class="row">
                <div class="col-sm-12">
                  <s:fielderror />
                  <s:actionerror />  
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-3">
                    <s:form action="LoginAction" theme="simple" class="form-signin">
                        <h2 class="form-signin-heading">Ingresar</h2>
                        <s:textfield name="user" label="Usuario" class="form-control" />
                        <s:password name="password" label="Password" class="form-control" />
                        <s:submit value="Ingresar" class="btn btn-lg btn-primary btn-block" />
                    </s:form>
                </div>
                <div class="col-sm-3"></div>
            </div>
            
            
        </div>
    </body>
</html>
