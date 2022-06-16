<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : notificaciones
    Created on : 14-may-2022, 14:11:58
    Author     : angel
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="es.taw.swishbay.dto.MensajeDTO"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.swishbay.dto.MensajeFiltroDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>SwishBay</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="d-flex h-100 text-center text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
    <jsp:include page="cabecera.jsp" />

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" style="float: right" id="navbarSupportedContent">

                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                    </li>
                </ul>
                <form:form modelAttribute="filtroMensaje" method="post" class="d-flex" action="/NotificacionesVerServlet">
                    <form:select path="filtro" class="form-select px-2 me-2" id="filtroMensajes" name="filtroMensajes">
                        <form:options items="${filtros}" itemValue="filtro" itemLabel="filtro"/>
                    </form:select>
                    <form:input path="busqueda" class="form-control me-2" type="search" placeholder="Buscar" name="filtro" aria-label="Search"/>
                    <form:button class="btn btn-outline-success" type="submit">Buscar</form:button>
                </form:form>
            </div>
        </div>
    </nav>

    <main class="row d-flex justify-content-center mt-4">

        <div class="d-flex justify-content-between">
            <h1>Listado de notificaciones: </h1>
        </div>

        <table class="table table-dark table-striped">
            <tr>
                <th>ASUNTO</th>
                <th>CUERPO DEL MENSAJE</th>
                <th>FECHA</th>
            </tr>
            <%
                List<MensajeDTO> mensajes = (List<MensajeDTO>)request.getAttribute("mensajes");
                if(mensajes != null && mensajes.size() != 0){
                    for (MensajeDTO mensaje : mensajes) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            %>
            <tr>
                <td><%= mensaje.getAsunto()%></td>
                <td><%= mensaje.getContenido() %></td>
                <td><%= format.format(mensaje.getFecha()) %></td>
            </tr>

            <%
                }

            %>
        </table>
        <%
        } else {
        %>
        </table>
        <h1>No hemos encontrado ningún mensaje</h1>
        <%
            }
        %>

    </main>

    <footer class="mt-5 text-white-50">
        <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
    </footer>
</div>
<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
