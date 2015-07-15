<%-- 
    Document   : RendicionDeCuentasForm
    Created on : 25-May-2015, 23:10:22
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <s:include value="partes/menu.jsp" />
            <ol class="breadcrumb">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Solicitudes devueltas</a></li>
                <li class="active">Rendición de cuentas</li>
            </ol>
            <h1 class="page-header">Rendicion de cuentas</h1>
            <s:form action="LiquidacionesUpdate" theme="simple">
                <div class="form-group">
                    <label>ID</label>
                    <s:textfield name="entity.id" label="Id" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Reconocimiento gasto comida</label>
                    <s:textfield name="entity.reconocimientoGastoComida" label="reconocimientoGastoComida" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Reconocimiento gasto alojamiento</label>
                    <s:textfield name="entity.reconocimientoGastoAlojamiento" label="reconocimientoGastoAlojamiento" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Reconocimiento gasto combustible</label>
                    <s:textfield name="entity.reconocimientoGastoCombustible" label="reconocimientoGastoCombustible" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Reconocimiento gasto pasajes</label>
                    <s:textfield name="entity.reconocimientoGastoPasajes" label="reconocimientoGastoPasajes" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Importe declarado</label>
                    <s:textfield name="entity.importeDeclarado" label="importeDeclarado" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Reconocimiento importe total</label>
                    <s:textfield name="entity.reconocimientoImporteTotal" label="reconocimientoImporteTotal" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Observaciones</label>
                    <s:textarea name="entity.observaciones" label="observaciones" class="form-control" />
                </div>
                <div class="form-group">
                    <s:submit name="Guardar" value="Guardar" class="btn btn-primary" /> 
                </div>
            </s:form>
            
            <s:include value="partes/footer.jsp" />
        </div>
    </body>
</html>
