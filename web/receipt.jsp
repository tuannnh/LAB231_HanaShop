<%-- 
    Document   : receipt
    Created on : Feb 19, 2020, 2:39:22 PM
    Author     : tuannnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="wrapper">
            <div class="main">

                <div class="section section-white section-search">
                    <div class="container">

                        <div class="card card-pricing">
                            <div class="card-body text-center">

                                <h1 class="card-title">Thank you for purchasing our products!</h1>
                                <blockquote class="blockquote blockquote-primary mb-0">
                                    <h4 class="card-category card-title">Payment Details</h4>
                                    <hr/>
                                    <h5>Shop: <strong>Hana Shop</strong></h5>
                                    <h5>Customer: <strong>${sessionScope.USER}</strong></h5>
                                    <h5>Total: <strong>$${requestScope.TOTAL} </strong></h5>
                                </blockquote>

                                <br />
                                <a href="index.jsp" class="btn btn-success btn-round card-link">Back to Home</a>
                                <a href="history.jsp" class="btn btn-success btn-round card-link">View History</a>
                            </div>
                        </div>


                    </div>
                </div>

            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
