<%--
    Document   : categoria
    Created on : 12-abr-2022, 19:55:51
    Author     : Luis
--%>

<%@page import="es.taw.swishbay.dto.CategoriaDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Categoría</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
</head>
<%

    CategoriaDTO categoria = (CategoriaDTO)request.getAttribute("categoria");
    String status = (String) request.getAttribute("status");
%>
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
                        <a class="nav-link" href="usuarios"> Usuarios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="productosAdmin">Productos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="categorias">Categorías</a>
                    </li>

                </ul>
            </div>
        </div>
    </nav>

    <br/>
    <h1 class="mb-2">Datos de la categoría</h1>
    <br/>

    <form  method="POST" action="categoriaGuardar">
        <% if (status != null) {%>
        <div class="form-group row justify-content-center" style="height: 50px;">
            <div class=" alert alert-danger col-sm-4"><%=status%></div>
        </div>
        <% } %>
        <div class="form-group row justify-content-md-center mb-4">
            <div class="col-sm-4">
                <input type="hidden" class="form-control" id="inputId" name="id" value="<%= categoria==null? "": categoria.getId() %>" >
            </div>
        </div>
        <div class="form-group row justify-content-md-center mb-4">
            <label for="inputNombre" class="col-sm-1 col-form-label">Nombre:</label>
            <div class="col-sm-4">
                <input type="text" maxlength="45" class="form-control" id="inputNombre" name="nombre" required="" autofocus="" value="<%= categoria==null? "": categoria.getNombre() %>" >
            </div>
            *
        </div>
        <div class="form-group row justify-content-md-center mb-4">
            <label for="inputDescripcion" class="col-sm-1 col-form-label">Descripción:</label>
            <div class="col-sm-4">
                <textarea class="form-control" name="descripcion" rows="4" maxlength="200"><%= categoria==null? "": categoria.getDescripcion() %></textarea>
            </div>
            &nbsp;
        </div>

        <br/>

        <div class="form-group row justify-content-md-center mt-2">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-lg btn-success fw-bold border-white mx-2"><%= categoria==null? "Añadir": "Modificar" %></button>
                <a href="categorias" class="btn btn-lg btn-secondary fw-bold border-white mx-2">Cancelar</a>
            </div>
        </div>
    </form>
    <br/>



    <footer class="text-white-50">
        <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
    </footer>
</div>
<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>