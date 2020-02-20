<%-- 
    Document   : login
    Created on : Feb 19, 2020, 10:53:45 AM
    Author     : tuannnh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/favicon.png">
        <link rel="icon" type="image/png" href="assets/img/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>
            Hana Shop
        </title>
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
              name='viewport' />
        <!--     Fonts and icons     -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
        <!-- CSS Files -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/css/paper-kit.css?v=2.3.0" rel="stylesheet" />
        <link href="assets/css/demo.css" rel="stylesheet" />
    </head>
    <body class="register-page full-screen sidebar-collapse">


        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg bg-white fixed-top nav-down navbar-transparent" color-on-scroll="500">
            <div class="container">
                <div class="navbar-translate">
                    <a class="navbar-brand" href="index.jsp" rel="tooltip"
                       title="Paper Kit 2 PRO" data-placement="bottom" target="_blank">
                        Hana Shop
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation"
                            aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-bar bar1"></span>
                        <span class="navbar-toggler-bar bar2"></span>
                        <span class="navbar-toggler-bar bar3"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" data-nav-image="../assets/img/blurred-image-1.jpg" data-color="orange">
                    <ul class="navbar-nav ml-auto">

                        <c:if test="${sessionScope.USER eq null}">
                            <li class="nav-item">
                                <a href="login.jsp" class="nav-link">
                                    Login </a>
                            </li>
                            <li class="nav-item">
                                <a href="register.jsp" class="nav-link">
                                    Register </a>
                            </li>
                        </c:if>

               

                    </ul>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->
        <div class="wrapper">
            <div class="page-header" style="background-image: url('assets/img/register-bg.jpg');">
                <div class="filter"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-4 col-md-6 col-sm-6 ml-auto mr-auto">
                            <div class="card card-register">
                                <h5 class="card-title text-center text-danger">${requestScope.ERROR_MESSAGE}</h5>
                                <h3 class="card-title text-center">Register</h3>
                                <div class="social">
                                    <c:set var="baseURL" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>

                                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=${baseURL}/Register&response_type=code
                                            &client_id=680564478805-icbg6sucel1ggngf9qt80qkg7jbsduf9.apps.googleusercontent.com&approval_prompt=force" class="btn btn-just-icon btn-google"><i class="fa fa-google"></i></a>

                                </div>
                                <div class="division">
                                    <div class="line l"></div>
                                    <span>or</span>
                                    <div class="line r"></div>
                                </div>
                                <form action="Register" method="POST" class="register-form">
                                    <input type="text" name="txtEmail" class="form-control" placeholder="Email">
                                    <input type="password" name="txtPassword" class="form-control" placeholder="Password">
                                    <input type="password" name="txtConfirm" class="form-control" placeholder="Password">
                                    <input type="submit" class="btn btn-block btn-round" value="Register"/>
                                </form>
                                <div class="login">
                                    <p>Already have an account?
                                        <a href="login.jsp">Login</a>.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="demo-footer text-center">
                    <h6>©
                        <script>
                            document.write(new Date().getFullYear())
                        </script> made with <i class="fa fa-heart heart"></i> by Hung Tuan</h6>
                </div>
            </div>
        </div>






        <!--   Core JS Files   -->
        <script src="assets/js/core/jquery.min.js" type="text/javascript"></script>
        <script src="assets/js/core/popper.min.js" type="text/javascript"></script>
        <script src="assets/js/core/bootstrap.min.js" type="text/javascript"></script>
        <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
        <script src="assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
        <script src="assets/js/vertical-nav.js" type="text/javascript"></script>
        <!--	Plugin for Select, full documentation here: http://silviomoreto.github.io/bootstrap-select -->
        <script src="assets/js/plugins/bootstrap-selectpicker.js" type="text/javascript"></script>
        <!--  for Jasny fileupload -->
        <script src="assets/js/plugins/jasny-bootstrap.min.js"></script>
        <!-- Control Center for Paper Kit: parallax effects, scripts for the example pages etc -->
        <script src="assets/js/paper-kit.js?v=2.3.0" type="text/javascript"></script>
        <!--  Plugin for presentation page - isometric cards  -->
        <script src="assets/js/plugins/presentation-page/main.js"></script>
    </body>
</html>
