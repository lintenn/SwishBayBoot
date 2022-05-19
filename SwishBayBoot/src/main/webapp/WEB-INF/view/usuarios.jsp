<%--
    Document   : listado
    Created on : Mar 21, 2022, 11:58:59 AM
    Author     : Luis
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="es.taw.swishbay.dto.RolUsuarioDTO"%>
<%@page import="es.taw.swishbay.dto.UsuarioDTO"%>
<%@page import="java.util.List"%>
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
            <a class="navbar-brand" href="usuarios">Panel de Administrador</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="usuarios"> Usuarios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ProductoAdminServlet">Productos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="CategoriaServlet">Categorías</a>
                    </li>

                </ul>
                <form method="post" class="d-flex" action="usuarios">
                    <div class="col-sm-5">
                        <select class="form-select px-2" id="filtroRol" name="filtroRol">

                            <%
                                UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
                                List<UsuarioDTO> usuarios = (List)request.getAttribute("usuarios");
                                List<RolUsuarioDTO> roles = (List) request.getAttribute("roles");
                                String filtroRol = (String) request.getAttribute("selected");
                            %>
                            <option <%= (filtroRol==null || filtroRol.equals("Tipo")) ? "selected":"" %> value="Tipo">Seleccionar tipo</option>
                            <%
                                for (RolUsuarioDTO rol : roles){

                            %>
                            <option <%= (filtroRol != null && filtroRol.equals(rol.getNombre())) ? "selected":"" %> value="<%=rol.getNombre()%>"><%= (rol.getNombre().equals("compradorvendedor") ? "comprador/vendedor":rol.getNombre()) %> </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <input class="form-control me-2 mx-2" type="search" placeholder="Buscar por nombre" name="filtro" aria-label="Search">
                    <input class="btn btn-outline-success" type="submit" value="Buscar"/>
                </form>
            </div>
        </div>
    </nav>

    <main class="row d-flex justify-content-center mt-4">

        <div class="d-flex justify-content-between">
            <h1>Listado de usuarios: </h1>
            <a href="UsuarioNuevoEditarServlet" class="btn btn-lg btn-secondary fw-bold border-white">Crear nuevo usuario</a>
        </div>

        <%
            if (usuarios == null || usuarios.isEmpty() ) {
        %>
        <div class="py-5 mb-5">
            <h2>Sin usuarios que mostrar.</h2>
        </div>
        <%
        } else {
        %>

        <table class="table table-dark table-striped">
            <tr>
                <th>NOMBRE</th>
                <th>CORREO</th>
                <th>APELLIDOS</th>
                <th>CIUDAD</th>
                <th>DOMICILIO</th>
                <th>NACIMIENTO</th>
                <th>SEXO</th>
                <th>TIPO</th>
                <th>SALDO</th>
                <th></th>
                <th></th>
            </tr>
            <%
                for (UsuarioDTO usuario : usuarios) {
                    String strFechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").format(usuario.getFechaNacimiento());
            %>
            <tr>
                <td><%= usuario.getNombre()%></td>
                <td><%= usuario.getCorreo()%></td>
                <td><%= usuario.getApellidos()%></td>
                <td><%= usuario.getCiudad()%></td>
                <td><%= usuario.getDomicilio()%></td>
                <td><%= strFechaNacimiento %></td>
                <td><%= usuario.getSexo()%></td>
                <td><%= (usuario.getRol().getNombre().equals("compradorvendedor")) ? "comprador/vendedor" : usuario.getRol().getNombre() %></td>
                <td><%= usuario.getSaldo()%></td>
                <td><a href="UsuarioNuevoEditarServlet?id=<%= usuario.getId() %>" class="btn btn-primary">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                    </svg>
                </a>
                </td>
                <td>
                    <% if (usuario.getId() != user.getId()) { %>
                    <a href="UsuarioBorrarServlet?id=<%= usuario.getId() %>" class="btn btn-danger">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                        </svg>
                    </a>
                    <% } %>
                </td>
            </tr>

            <%
                }
            %>
        </table>

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