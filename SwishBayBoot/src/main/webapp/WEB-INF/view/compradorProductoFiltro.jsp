<%--
    Document   : compradorProductoFiltro
    Created on : May 30, 2022, 9:19:09 PM
    Author     : Miguel Oña Guerrero
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.swishbay.dto.CompradorFiltroDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    CompradorFiltroDTO filtro = (CompradorFiltroDTO) request.getAttribute("filtro");
%>
<p class=" mx-2">Precio máximo</p>
<form:form action="<%=filtro.getGoTo()%>"  method="post" modelAttribute="filtro" class="d-flex">
    <form:hidden path="goTo"/>
    <form:input path="filtroPrecio" type="range" min="0" max="<%=filtro.getMayorPrecio()%>" id="points" class="me-2 mb-3"/>
    <p class="me-2"><%= filtro.getFiltroPrecio() %>€</p>
    <form:input path="mayorPrecio" type="hidden"/>
    <div class="col-sm-4">
        <form:select path="filtroCategoria" class="form-select px-2" id="filtroCategoria" >
            <form:option value="" label="Categoria"/>
            <form:options items="${categorias}" itemLabel="nombre" itemValue="nombre"/>
        </form:select>
    </div>
    <form:input path="filtroTitulo" class="form-control me-2 mx-2" type="search" placeholder="Buscar" />
    <form:button class="btn btn-outline-success">Buscar</form:button>
</form:form>
