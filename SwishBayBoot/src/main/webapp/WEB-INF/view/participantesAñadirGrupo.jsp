<%--
    Document   : participantesGrupoAñadir
    Created on : 20 abr 2022, 21:21:54
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
                <a href="/añadirParticipanteAGrupo/<%=idGrupo%>/<%=usuario.getId()%>" class="btn btn-success">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"/>
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
            <a href="/usuariosCompradorVendedorDeUnGrupo/<%=idGrupo%>" class="btn btn-lg btn-secondary fw-bold border-white mx-2">Volver</a>
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
