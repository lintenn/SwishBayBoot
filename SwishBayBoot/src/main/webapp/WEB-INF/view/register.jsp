<%--
    Document   : register
    Created on : 26-mar-2022, 21:20:48
    Author     : Luis
--%>

<%@page import="es.taw.swishbay.dto.CategoriaDTO"%>
<%@page import="es.taw.swishbay.dto.UsuarioDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");
    List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
    String goTo = "usuarioGuardar";

    String status = (String) request.getAttribute("status");

    if (user != null) {
        String redirectTo = "compradorProductosServlet";
        if (user.getRol().getNombre().equals("administrador")) {
            redirectTo = "usuarios";
        } else if (user.getRol().getNombre().equals("compradorvendedor")) {
            redirectTo = "compradorProductosServlet";
        } else if (user.getRol().getNombre().equals("marketing")) {
            redirectTo = "usuarioCompradorServlet";
        }
        response.sendRedirect(request.getContextPath() + "/" + redirectTo);
    }

%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <title>Registrarse</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Favicons -->
    <link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
    <meta name="theme-color" content="#7952b3">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }

            html,
            body {
                height: 100%;
            }
            body {
                display: -ms-flexbox;
                display: flex;
                -ms-flex-align: center;
                align-items: center;
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #212529 !important; /*#f5f5f5;*/
            }
            .form-signin {
                width: 100%;
                max-width: 570px;
                padding: 15px;
                margin: auto;
            }
            .form-signin .checkbox {
                font-weight: 400;
            }
            .form-signin .form-control {
                position: relative;
                box-sizing: border-box;
                height: auto;
                padding: 10px;
                font-size: 16px;
            }
            .form-signin .form-control:focus {
                z-index: 2;
            }
            .form-signin input[type="email"] {
                margin-bottom: -1px;
                border-bottom-right-radius: 0;
                border-bottom-left-radius: 0;
            }
            .form-signin input[type="password"] {
                margin-bottom: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }
        }

        .botones{
            display: flex;
            padding: 0 10px;
        }
    </style>

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body class="text-center text-white">
<main class="form-signin">
    <form method="POST" class="border border-dark p-5" action="<%= goTo %>">
        <img class="mb-4" src="https://raw.githubusercontent.com/lintenn/SwishBay/main/img/SwishBay_logo_white.png" alt="" width="120" height="50">

        <h1 class="h3 mb-3 fw-normal">Registrarse</h1>
        <% if (status != null) {%>
        <div class="alert alert-danger"><%=status%></div>
        <% } %>
        <div class="row g-3">
            <div class="col-sm-6 form-floating">
                <input type="text" maxlength="45" name="nombre" class="form-control" id="inputFirstName" placeholder="Nombre" required="" autofocus=""/>
                <label for="inputFirstName" class="sr-only">Nombre</label>
            </div>
            <div class="col-sm-6 form-floating">
                <input type="text" maxlength="45" name="apellidos" class="form-control" id="floatingInput" placeholder="Apellidos" required=""/>
                <label for="inputLastName" class="sr-only">Apellidos</label>
            </div>
        </div>
        <div class="form-floating">
            <input type="email" maxlength="45" name="correo" class="form-control" id="floatingInput" placeholder="Email" required=""/>
            <label for="inputEmail" class="sr-only">Email</label>
        </div>
        <div class="form-floating">
            <input type="password" minlength="6" maxlength="45" name="password" class="form-control" id="floatingPassword" placeholder="Contraseña" aria-describedby="defaultRegisterFormPasswordHelpBlock" required=""/>
            <label for="inputPassword" class="sr-only">Contraseña</label>
        </div>
        <small id="defaultPasswordFormPhoneHelpBlock" class="form-text text-light mb-4">Mínimo 6 caracteres de longitud</small>
        <div class="form-floating">
            <input type="text" maxlength="100" name="domicilio" class="form-control" id="floatingInput" placeholder="Domicilio"/>
            <label for="inputAddressline" class="sr-only">Domicilio</label>
        </div>
        <div class="form-floating">
            <input type="text" maxlength="45" name="ciudad" class="form-control" id="floatingInput" placeholder="Ciudad"/>
            <label for="inputCity" class="sr-only">Ciudad</label>
        </div>
        <br/>
        <label for="inputBirthdate" class="form-label">Fecha de nacimiento:</label>
        <div class="form-floating">
            <input type="date" name="fechaNacimiento" class="form-control" id="floatingInput" required=""/>
            <label for="inputBirthdate" class="sr-only">Fecha de nacimiento</label>
        </div>
        <br/>
        <label for="inputGender" class="form-label">Sexo:</label>
        <div class="d-flex align-center justify-content-center">
            <div class="form-check mx-1">
                <input id="masc" name="sexo" value="masc" type="radio" class="form-check-input" checked="" required=""/>
                <label class="form-check-label" for="masc">Masculino</label>
            </div>
            <div class="form-check mx-1">
                <input id="fem" name="sexo" value="fem" type="radio" class="form-check-input" required=""/>
                <label class="form-check-label" for="fem">Femenino</label>
            </div>
        </div>
        <br/>
        <label for="inputCategory" class="form-label">Categorías preferidas:</label>
        <%
            for (CategoriaDTO categoria : categorias) {
        %>
        <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="categoria<%=categoria.getId()%>" name="categoria" value="<%= categoria.getId() %>"/>
            <label class="custom-control-label" for="categoria<%=categoria.getId()%>"><%=categoria.getNombre()%></label>
        </div>

        <% } %>

        <br/>
        <nav class="botones">
            <input type="submit" style="margin-right: 10px" class="w-100 btn btn-lg btn-primary" value="Registrarse"/>
            <a href="login" style="margin-left: 10px" class="w-100 btn btn-lg btn-primary">Volver</a>
        </nav>

        <div class="text-center">

            <hr/>

            <p>Al hacer click en
                <em>Registrarse</em> aceptas los
                términos de servicio.
            </p>
        </div>

        <p class="mt-5 mb-3 text-muted">© 2022, SwishBay</p>
    </form>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>