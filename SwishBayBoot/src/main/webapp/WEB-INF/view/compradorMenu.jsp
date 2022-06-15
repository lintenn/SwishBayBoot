<%--
    Document   : compradorMenu
    Created on : May 30, 2022, 9:19:58 PM
    Author     : Miguel Oña Guerrero
--%>
<%@ page import="es.taw.swishbay.dto.CompradorFiltroDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    CompradorFiltroDTO filtro = (CompradorFiltroDTO) request.getAttribute("filtro");
    String goTo;
    if(filtro == null){
        goTo = "";
    }else {
        goTo = filtro.getGoTo();
    }


%>
<ul class="navbar-nav me-auto mb-2 mb-lg-0">
    <li class="nav-item">
        <a class="nav-link <%=(goTo.equals("/comprador/productos")) ? "active" : "" %>" href="/comprador/productos">Productos</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(goTo.equals("/comprador/enPuja")) ? "active" : "" %>" href="/comprador/enPuja">Pujas Abiertas</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(goTo.equals("/comprador/favoritos")) ? "active" : "" %>" href="/comprador/favoritos">Favoritos</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(goTo.equals("/comprador/comprados")) ? "active" : "" %>" href="/comprador/comprados">Comprados</a>
    </li>
</ul>
