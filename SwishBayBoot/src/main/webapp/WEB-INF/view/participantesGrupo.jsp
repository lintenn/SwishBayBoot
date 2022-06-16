<%--
    Document   : participantesGrupoEditar
    Created on : 20 abr 2022, 12:15:59
    Author     : angel
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.swishbay.dto.UsuarioDTO" %>
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

<main class="row d-flex justify-content-center mt-4">

    <div class="d-flex justify-content-between">
        <h1>Listado de usuarios compradores: </h1>
    </div>

    <table class="table table-dark table-striped">
        <tr>
            <th>NOMBRE</th>
            <th>CORREO</th>
            <th>APELLIDOS</th>
            <th>CIUDAD</th>
            <th>DOMICILIO</th>
            <th>NACIMIENTO</th>
            <th>SEXO</th>
            <th>SALDO</th>
            <th></th>
        </tr>
        <%
            Integer idGrupo = (Integer)request.getAttribute("idGrupo");
            List<UsuarioDTO> usuarios = (List)request.getAttribute("usuarios");
            if(usuarios.size() != 0){
                for (UsuarioDTO usuario : usuarios) {
                    String strFechaNacimiento = DateFormat.getDateInstance(DateFormat.SHORT).format(usuario.getFechaNacimiento());
        %>
        <tr>
            <td><%= usuario.getNombre()%></td>
            <td><%= usuario.getCorreo()%></td>
            <td><%= usuario.getApellidos()%></td>
            <td><%= usuario.getCiudad()%></td>
            <td><%= usuario.getDomicilio()%></td>
            <td><%= strFechaNacimiento %></td>
            <td><%= usuario.getSexo()%></td>
            <td><%= usuario.getSaldo()%></td>
            <td>
                <a href="/borrarUsuarioCompradorVendedorDeUnGrupo/<%= idGrupo %>/<%= usuario.getId() %>" class="btn btn-danger">
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
    }
    else {
    %>
    </table>
    <h1>No hemos encontrado ningún usuario</h1>
    <%
        }
    %>

    <div class="form-group row justify-content-md-center mt-2">
        <div class="col-sm-10">
            <a href="/usuariosCompradorVendedorQueNoSonDeUnGrupo/<%= idGrupo %>" class="btn btn-lg btn-success fw-bold border-white mx-2">Añadir participantes</a>
            <a href="/crearEditarGrupo/<%= idGrupo %>" class="btn btn-lg btn-secondary fw-bold border-white mx-2">Volver</a>
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