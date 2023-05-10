<%-- 
    Document   : login
    Created on : 17/06/2016, 01:18:46 PM
    Author     : Job Salinas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión</title>
        <link rel='stylesheet' href='assets/css/main.css' />
    </head>
    <body class='landing'>
        <header id='header'>
            <h1 id='logo'><a href='#'></a></h1>
        </header>
        <div id='page-wrapper'>
            <section id='banner'>
                <div class='content'>
                    <header>
                        <h2>AppointmentBook</h2>
                    <h3>Iniciar sesión</h3>
                    <form method='post' action='Login'>
                        <input type='text' name='username' id='username' placeholder='Nombre de usuario' autocomplete='on' required>
                        <input type='password' name='passw' id='passw' placeholder='Contraseña' required>
                        <input class='button special' type='submit' value='Entrar'>
                    </form>
                    </header>
                    <span class='image'><img src='images/logo_muse.png' alt='' /></span>

                <!-- Scripts -->
                                <script src='assets/js/jquery.min.js'></script>
                                <script src='assets/js/jquery.scrolly.min.js'></script>
                                <script src='assets/js/jquery.dropotron.min.js'></script>
                                <script src='assets/js/jquery.scrollex.min.js'></script>
                                <script src='assets/js/skel.min.js'></script>
                                <script src='assets/js/util.js'></script>
                                <!--[if lte IE 8]><script src='assets/js/ie/respond.min.js'></script><![endif]-->
                                <script src='assets/js/main.js'></script>

                </div>
            </section>
        </div>
    </body>
    
    
</html>
