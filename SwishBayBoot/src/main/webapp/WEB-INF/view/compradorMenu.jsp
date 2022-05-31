<%--
    Document   : compradorMenu
    Created on : May 30, 2022, 9:19:58 PM
    Author     : Miguel Oña Guerrero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String goTo = (String)request.getAttribute("goTo");
    if(goTo == null){
        goTo = "";
    }
%>
<ul class="navbar-nav me-auto mb-2 mb-lg-0">
    <li class="nav-item">
        <a class="nav-link <%=(goTo.equals("/productos")) ? "active" : "" %>" href="productos">Productos</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(goTo.equals("/enPuja")) ? "active" : "" %>" href="enPuja">Pujas Abiertas</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(goTo.equals("/favoritos")) ? "active" : "" %>" href="favoritos">Favoritos</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(goTo.equals("/comprados")) ? "active" : "" %>" href="comprados">Comprados</a>
    </li>
</ul>
