<%-- 
    Document   : producto
    Created on : 6 abr. 2022, 18:30:04
    Author     : galop
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.util.List"%>
<%@ page import="es.taw.swishbay.dto.ProductoDTO" %>
<%@ page import="es.taw.swishbay.dto.CategoriaDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

    </head>
    <%
       //ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
       String status = (String) request.getAttribute("status");
       List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");

    %>
    
    <body class="d-flex h-100 text-center text-white bg-dark">
        <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
            <jsp:include page="cabecera.jsp" />

            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
              <div class="container-fluid">
                <a class="navbar-brand" href="/comprador/productos">Comprar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link" aria-current="page" href="/seller/misProductos"> Mis productos</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="/seller/misProductosEnPuja">Mis pujas</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="/seller/misProductosVendidos">Productos vendidos</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" href="/productos/nuevo">Añadir/modificar producto</a>
                    </li>
                  
                  </ul>
                </div>
              </div>
            </nav>
            </br>

            <h1 class="mb-1">Datos del producto</h1>
            <form:form action="/productos/crear" method="POST" modelAttribute="producto">
                <div class="form-group row justify-content-md-center mb-4">
                    <div class="col-sm-4">
                        <form:hidden path="id" />
                    </div>
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                    <label class="col-sm-1 col-form-label">Nombre:</label>
                    <div class="col-sm-4">
                        <form:input type="text" class="form-control" path="titulo" maxlength="45" />
                    </div>
                    *
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                    <label class="col-sm-1 col-form-label">Descripción:</label>
                    <div class="col-sm-4">
                        <form:textarea class="form-control" path="descripcion" rows="3" maxlength="80" />
                    </div>
                    &nbsp;
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                    <label class="col-sm-1 col-form-label">Precio:</label>
                    <div class="col-sm-4">
                        <form:input type="number" class="form-control" path="precioSalida" required="true" />
                    </div>
                    *
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                    <label  class="col-sm-1 col-form-label">Foto (URL):</label>
                    <div class="col-sm-4">
                        <form:textarea class="form-control" path="foto" rows="2" />
                    </div>
                    &nbsp;
                </div>

                <div class="form-group row justify-content-md-center mb-3">
                    <label class="col-sm-1 col-form-label">Categoría:</label>
                    <div class="col-sm-4">
                        <form:select class="form-select mb-2" path="categoria">
                            <form:options items="${categorias}" itemLabel="nombre" itemValue="id" />
                        </form:select>
                    </div>
                    &nbsp;
                </div>
                <%
                    if(status != null){
                %>
                <div class="form-group row justify-content-center" style="height: 50px;">
                    <div class=" alert alert-danger col-sm-4"><%=status%></div>
                </div>
                <% }
                %>
                </br>

                <div class="form-group row justify-content-md-center mt-0">
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-lg btn-success fw-bold border-white mx-2">Confirmar</button>
                        <a href="/seller/misProductos" class="btn btn-lg btn-secondary fw-bold border-white mx-2">Cancelar</a>
                    </div>
                </div>

            </form:form>

            <footer class="mt-auto text-white-50 fixed-bottom">
              <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
            </footer>
        </div>

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
