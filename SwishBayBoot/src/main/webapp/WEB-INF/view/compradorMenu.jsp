<%--
    Document   : menucomprador
    Created on : May 14, 2022, 9:19:58 PM
    Author     : Miguel Oña Guerrero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String mapping = (String)request.getAttribute("servlet");
    if(mapping == null){
        mapping = "";
    }
%>
<ul class="navbar-nav me-auto mb-2 mb-lg-0">
    <li class="nav-item">
        <a class="nav-link <%=(mapping.equals("/productos")) ? "active" : "" %>" href="/comprador/productos">Productos</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(mapping.equals("/enPuja")) ? "active" : "" %>" href="/comprador/enPuja">Pujas Abiertas</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(mapping.equals("/favoritos")) ? "active" : "" %>" href="/comprador/favoritos">Favoritos</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(mapping.equals("/comprados")) ? "active" : "" %>" href="/comprador/comprados">Comprados</a>
    </li>
</ul>
