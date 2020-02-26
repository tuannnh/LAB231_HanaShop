<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <c:set var="baseURL" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
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
        <link href="assets/css/jquery-ui.css rel="stylesheet" />
              <link href="assets/css/paper-kit.css?v=2.3.0" rel="stylesheet" />
        <link href="assets/css/demo.css" rel="stylesheet" />
        <link href="assets/css/sweetalert2.min.css" rel="stylesheet" />
        <link href="assets/css/bootstrap-rating.css" rel="stylesheet" />
        <link href="assets/css/mycss.css" rel="stylesheet" />

    </head>

    <body class="search-page sidebar-collapse">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg fixed-top bg-info nav-down">
            <div class="container">
                <div class="navbar-translate">
                    <a class="navbar-brand" href="${baseURL}/index.jsp" rel="tooltip"
                       title=" Hana Shop" data-placement="bottom">
                        Hana Shop
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation"
                            aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-bar bar1"></span>
                        <span class="navbar-toggler-bar bar2"></span>
                        <span class="navbar-toggler-bar bar3"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" data-color="blue">
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

                        <c:if test="${sessionScope.USER ne null}">
                            <c:if test="${sessionScope.USER.privilege eq 'Admin'}">
                                <li class="dropdown nav-item">
                                    <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false">
                                        Welcome, ${sessionScope.USER} </a>
                                    <div class="dropdown-menu dropdown-menu-right dropdown-danger">
                                        <a href="admin-food.jsp" class="dropdown-item">
                                            Food Management
                                        </a>
                                        <a href="admin-category.jsp" class="dropdown-item">
                                            Category Management
                                        </a>
                                        <a href="admin-coupon.jsp" class="dropdown-item">
                                            Coupon Management
                                        </a>
                                        <a href="Logout" class="dropdown-item">
                                            Logout
                                        </a>
                                    </div>
                                </li>
                            </c:if>

                            <c:if test="${sessionScope.USER.privilege eq 'User'}">
                                <li class="dropdown nav-item">
                                    <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false">
                                        Welcome, ${sessionScope.USER} </a>
                                    <div class="dropdown-menu dropdown-menu-right dropdown-danger">
                                        <a href="history.jsp" class="dropdown-item">
                                            History
                                        </a>
                                        <a href="Logout" class="dropdown-item">
                                            Logout
                                        </a>
                                    </div>
                                </li>
                                <li class="nav-item">
                                    <a class="btn btn-round btn-info" href="cart.jsp">
                                        <i class="nc-icon nc-cart-simple"></i> View Cart
                                    </a>
                                </li>
                            </c:if>

                        </c:if>



                    </ul>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->