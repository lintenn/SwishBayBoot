<%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 11/5/22
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@ page import="es.taw.swishbay.entity.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado de clientes</title>
</head>
<body>

<h1>Listado de clientes</h1>


<%
    List<Usuario> usuarios = (List)request.getAttribute("usuarios");
    if (usuarios == null || usuarios.isEmpty() ) {
%>
<h2>No hay clientes</h2>
<%
} else {
%>
<table border="1">
    <tr>
        <th>NAME</th>
    </tr>
    <%
        for (Usuario usuario: usuarios) {
    %>
    <tr>
        <td><%= usuario.getNombre() %></td>
    </tr>

    <%
        }
    %>
</table>
<%
    }
%>
</body>
</html>
