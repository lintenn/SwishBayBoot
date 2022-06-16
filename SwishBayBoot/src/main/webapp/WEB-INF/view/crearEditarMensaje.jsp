<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : crearEditarMensaje
    Created on : 25 abr 2022, 14:44:39
    Author     : angel
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.swishbay.dto.MensajeDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Usuario</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
</head>
<%
    MensajeDTO mensaje = (MensajeDTO)request.getAttribute("mensaje");
%>
<body class="d-flex h-100 text-center text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
    <jsp:include page="cabeceraPrincipal.jsp" />

    <br/>
    <h1 class="mb-2">Datos del grupo</h1>
    <br/>
    <%
        String id = "";
        Integer idGrupo = (Integer)request.getAttribute("idGrupo");
        id = "/guardarMensaje/"+mensaje.getId()+"/"+idGrupo;
    %>
    <form:form modelAttribute="mensaje" method="POST" action="<%=id%>">
        <div class="form-group row justify-content-md-center mb-4">
            <div class="form-group row justify-content-md-center mb-4">
                <label for="inputAsunto" class="col-sm-1 col-form-label">Asunto:</label>
                <div class="col-sm-4">
                    <form:input path="asunto" type="text" maxlength="150" class="form-control" id="inputAsunto" name="asunto" required="" autofocus=""/>
                </div>
                *
            </div>
            <div class="form-group row justify-content-md-center mb-4">
                <label for="inputCuerpo" class="col-sm-1 col-form-label">Cuerpo del mensaje:</label>
                <div class="col-sm-4">
                    <form:input path="contenido" type="text" class="form-control" id="inputCuerpo" name="contenido" required="" autofocus=""/>
                </div>
                *
            </div>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>

            <div class="form-group row justify-content-md-center mt-2">
                <div class="col-sm-10">
                    <form:button type="submit" class="btn btn-lg btn-success fw-bold border-white mx-2"><%= mensaje.getId()==0? "Añadir": "Modificar" %></form:button>
                    <a href="/verMensajes/<%= idGrupo %>" class="btn btn-lg btn-secondary fw-bold border-white mx-2">Cancelar</a>
                </div>
            </div>
        </div>
    </form:form>
    <br/>



    <footer class="mt-5 text-white-50">
        <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
    </footer>
</div>
<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
