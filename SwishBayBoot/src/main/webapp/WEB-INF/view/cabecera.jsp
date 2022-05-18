<%-- 
    Document   : cabecera
    Created on : Apr 12, 2022, 3:01:32 PM
    Author     : Miguel Oña Guerrero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsuarioDTO usuario = (UsuarioDTO)session.getAttribute("usuario");
   
    if(usuario == null){
        response.sendRedirect(request.getContextPath());
    }
%>
<header class="mb-auto">
        <a href="CompradorProductosServlet" col-1>
        <img class="float-md-start ms-3" src="https://raw.githubusercontent.com/lintenn/SwishBay/main/img/SwishBay_logo_white.png" alt="" width="15%" height="15%"/>
    </a>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mt-2">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <span class="navbar-text me-2">
          Saldo: <%=usuario.getSaldo()%> €
      </span>
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal">Añadir saldo</a>
        </li>
      </ul>
        <div class="float-md-end">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="NotificacionesVerServlet">Notificaciones</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="LogoutServlet">Cerrar sesión</a>
        </li>
      </ul>
        </div>
      
    </div>
  </div>
</nav>
</header>
  
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <form action="CompradorSaldoServlet" method="POST">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title text-dark" id="exampleModalLabel" >Añadir saldo</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body text-dark">
          <input type="hidden" name="id" value="<%=usuario.getId() %>" />
            <div class="my-2">
                <label for="cantidad" class="form-label">Cantidad que desea añadir: </label>
                <input type="number" min="0" id="cantidad" name="saldo" required>         
            </div>          
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="submit" class="btn btn-primary">Añadir</button>
      </div>
    </div>
  </div>
  </form>
</div>
