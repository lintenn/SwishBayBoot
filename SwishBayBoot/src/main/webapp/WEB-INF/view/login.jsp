<%--
    Document   : login
    Created on : 26-mar-2022, 20:00:08
    Author     : Luis
--%>

<%@page import="es.taw.swishbay.dto.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String status = (String) request.getAttribute("status");
    UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");

    if (user != null) {
        String goTo = "misProductos";
        if (user.getRol().getNombre().equals("administrador")) {
            goTo = "usuarios";
        } else if (user.getRol().getNombre().equals("compradorvendedor")) {
            goTo = "misProductos";
        } else if (user.getRol().getNombre().equals("marketing")) {
            goTo = "usuarioCompradorServlet";
        }
        response.sendRedirect(request.getContextPath() + "/" + goTo);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <title>Iniciar sesión</title>

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
                /*background-color: #f5f5f5;*/
            }
            .form-signin {
                width: 100%;
                max-width: 330px;
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="text-center bg-dark text-white">
<main class="form-signin">
    <form method="POST" action="autentica">
        <img class="mb-4" src="https://raw.githubusercontent.com/lintenn/SwishBay/main/img/SwishBay_logo_white.png" alt="" width="120" height="50">
        <h1 class="h3 mb-3 fw-normal">Iniciar sesión</h1>

        <%
            if(status != null){
        %>
        <div class="alert alert-danger"><%=status%></div>
        <% }
        %>

        <div class="form-floating">
            <input type="email" name="correo" class="form-control" id="floatingInput" placeholder="Email address" required="" autofocus="" maxlength="45"/>
            <label for="floatingInput" class="sr-only text-dark">Email</label>
        </div>
        <div class="form-floating">
            <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password" required="" maxlength="45"/>
            <label for="floatingPassword" class="sr-only text-dark">Contraseña</label>
        </div>

        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"/> Recuérdame
            </label>
        </div>
        <nav class="botones" >
            <input type="submit" style="margin-right: 10px" class="w-100 btn btn-lg btn-primary" value="Entrar"/>
            <a href="register" style="margin-left: 10px" class="w-100 btn btn-lg btn-primary">Registrarse</a>
        </nav>

        <p class="mt-5 mb-3 text-muted">© 2022, SwishBay</p>
    </form>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>