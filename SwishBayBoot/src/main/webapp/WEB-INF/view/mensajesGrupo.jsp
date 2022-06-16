<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : mensajes
    Created on : 25 abr 2022, 13:55:52
    Author     : angel
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.swishbay.dto.MensajeDTO" %>
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
    <jsp:include page="cabeceraPrincipal.jsp" />

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" style="float: right" id="navbarSupportedContent">

                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                    </li>
                </ul>
                <%
                    Integer idGrupo = (Integer)request.getAttribute("idGrupo");
                    String action = "/verMensajes/" + idGrupo;
                %>
                <form:form modelAttribute="filtroMensaje" method="post" class="d-flex" action="<%= action %>">
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
            <h1>Listado de mensajes: </h1>
            <a href="/crearEditarMensaje/<%=idGrupo%>" class="btn btn-lg btn-secondary fw-bold border-white">Crear nuevo mensaje</a>
        </div>

        <table class="table table-dark table-striped">
            <tr>
                <th>ASUNTO</th>
                <th>CUERPO DEL MENSAJE</th>
                <th>FECHA</th>
                <th></th>
                <th></th>
            </tr>
            <%
                List<MensajeDTO> mensajes = (List)request.getAttribute("mensajes");
                if(mensajes.size() != 0){
                    for (MensajeDTO mensaje : mensajes) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            %>
            <tr>
                <td><%= mensaje.getAsunto()%></td>
                <td><%= mensaje.getContenido() %></td>
                <td><%= format.format(mensaje.getFecha()) %></td>
                <td><a href="/crearEditarMensaje/<%= mensaje.getId() %>/<%= idGrupo %>" class="btn btn-primary">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                    </svg>
                </a>
                </td>
                <td>
                    <a href="/borrarMensajes/<%= mensaje.getId() %>/<%= idGrupo %>" class="btn btn-danger">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                        </svg>
                    </a>
                </td>
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

        <div class="form-group row justify-content-md-center mt-2">
            <div class="col-sm-10">
                <a href="/grupos" class="btn btn-lg btn-secondary fw-bold border-white">Volver</a>
            </div>
        </div>
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
