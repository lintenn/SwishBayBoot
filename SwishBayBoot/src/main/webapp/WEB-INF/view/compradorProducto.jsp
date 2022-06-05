<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : compradorProducto
    Created on : Jun 4, 2022, 9:42:03 PM
    Author     : Miguel Oña Guerrero
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.swishbay.dto.UsuarioDTO" %>
<%@ page import="es.taw.swishbay.dto.ProductoDTO" %>
<%@ page import="es.taw.swishbay.dto.PujaDTO" %>
<%@ page import="es.taw.swishbay.dto.CategoriaDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>SwishBay</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<%
    UsuarioDTO usuario = (UsuarioDTO)session.getAttribute("usuario");
    ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
    CategoriaDTO categoria = (CategoriaDTO) request.getAttribute("categoria");
    String error = (String) request.getAttribute("error");
    error = (error == null) ? "" : error;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
%>
<body class="d-flex h-100 text-center text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
    <jsp:include page="cabecera.jsp" />

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/seller/misProductos">Vender</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <jsp:include page="compradorMenu.jsp"/>
            </div>
        </div>
    </nav>

    <main class="row d-flex justify-content-center mt-2">
        <div class="row col-6 border-bottom">

            <div class="mt-2 col-12 d-flex justify-content-between border-bottom">
                <div class="col-8 d-flex justify-content-start">
                    <h1><%= producto.getTitulo() %></h1>
                    <a href="/comprador/favorito/<%=producto.getId() %>/0" class="col-1 mt-2">
                        <%
                            if(usuario.getFavoritos().contains(producto.getId())){
                        %>
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="orange" class="bi bi-star-fill" viewBox="0 0 16 16">
                            <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
                        </svg>
                        <%
                            } else{
                        %>
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="orange" class="bi bi-star" viewBox="0 0 16 16">
                            <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"/>
                        </svg>
                        <%
                            }
                        %>
                    </a>
                </div>
                <p class="fs-4"><%= categoria.getNombre() %></p>
            </div>

            <div class="col-5 mt-4 d-flex justify-content-start">
                <img src="<%= producto.getFoto() %>" class="card-img-top" style="max-width:90% ;height:90%" >
            </div>

            <div class="col-7 mt-3">
                <div class="d-flex justify-content-center">
                    <p class="fs-2"><%= producto.getDescripcion() %></p>
                </div>
                <div class="d-flex justify-content-center">
                    <p class="fs-4">Precio de salida por <%= producto.getPrecioSalida()%>€</p>
                </div>
                <div class="d-flex justify-content-center">
                    <p class="fs-4">Fin de subasta el <%= formato.format(producto.getFinPuja())%></p>
                </div>
                <div class="d-flex justify-content-center">
                    <p class="fs-4">Vendido por <%= producto.getVendedor().getNombre() + " " + producto.getVendedor().getApellidos()%></p>
                </div>
            </div>

        </div>

        <%
            if(producto.getEnPuja() == 0 && producto.getComprador() == null){
        %>
        <div class="row col-9 d-flex justify-content-center mt-2">
            <h3>Subasta aún no disponible</h3>
        </div>
        <%
            }else if(producto.getComprador() != null && producto.getComprador().getId() == usuario.getId()){
                PujaDTO pujaGanadora = (PujaDTO) request.getAttribute("pujaGanadora");
        %>
        <div class="row col-9 d-flex justify-content-center mt-2">
            <h3>Subasta finalizada</h3>
            <p class="fs-4">Compraste este producto por <%= pujaGanadora.getPrecio() %>€ en la fecha <%= formato.format(pujaGanadora.getFecha()) %></p>
        </div>
        <%
            }else if(producto.getComprador() != null){
        %>
        <div class="row col-9 d-flex justify-content-center mt-2">
            <h3>Subasta finalizada</h3>
            <p class="fs-4">Otro usuario ya compró el producto</p>
        </div>
        <%
            }else{
                List<PujaDTO> pujas = (List) request.getAttribute("pujas");
        %>

        <div class="row col-9 d-flex justify-content-center">

            <div class="mt-2 col-8 d-flex justify-content-between">
                <%
                    if(pujas.isEmpty()){
                %>
                <h2>¡Realiza la primera puja!</h2>
                <%
                }else{
                %>
                <h2>Pujas: </h2>
                <%
                    }
                %>
                <p class="mt-2"><%= error %></p>
                <form:form action="/comprador/pujar" method="post" modelAttribute="puja" class="col-4 d-flex justify-content-center">

                    <form:input type="hidden" value="<%= producto.getId() %>" path="producto"/>
                    <div class="col-8 d-flex justify-content-center">
                        <form:input type="number" min="1" class="form-control" placeholder="Cantidad a pujar" path="precio" required="true"/>
                    </div>
                    <div class="col-4">
                        <form:button class="btn btn-lg btn-success fw-bold border-white mx-2">Pujar</form:button>
                    </div>

                </form:form>
            </div>
            <div class="mt-3 col-8 d-flex justify-content-between">
                <table class="table table-striped table-dark">
                    <thead>
                    <tr>
                        <th scope="col"><p class="fs-4">Pujador</p></th>
                        <th scope="col"><p class="fs-4">Cantidad</p></th>
                        <th scope="col"><p class="fs-4">Fecha</p></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for(PujaDTO puja : pujas){
                    %>
                    <tr>
                        <td><%= puja.getComprador().getNombre() + " " + puja.getComprador().getApellidos() %></td>
                        <td><%= puja.getPrecio() %>€</td>
                        <td><%= formato.format(puja.getFecha()) %></td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>

        </div>

        <%
            }
        %>

    </main>
    <footer class="mt-5 text-white-50">
        <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/SwishBay/" class="text-white">Grupo 10</a>.</p>
    </footer>
</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>