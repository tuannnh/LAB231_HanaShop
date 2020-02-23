<%-- 
    Document   : review
    Created on : Feb 19, 2020, 2:22:40 PM
    Author     : tuannnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review Payment</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="wrapper">
            <div class="main">

                <div class="section section-white section-search">
                    <div class="container">
                        <form action="CompletePurchase" method="POST">
                            <input type="hidden" name="paymentId" value="${param.paymentId}" />
                            <input type="hidden" name="PayerID" value="${param.PayerID}" />
                            <div class="card card-pricing">
                                <div class="card-body">

                                    <h1 class="card-title">Review Your Payment via Paypal</h1>
                                    <blockquote class="blockquote blockquote-primary mb-0">
                                        <h6 class="card-category">Transaction Details</h6>
                                        <h6>Total: $${TRANSACTION.amount.total}</h6>
                                    </blockquote>

                                    <blockquote class="blockquote blockquote-primary mb-0">
                                        <h6 class="card-category">Payer Information</h6>
                                        <p>Email: ${PAYER.email}</p>
                                    </blockquote>

                                    <blockquote class="blockquote blockquote-primary mb-0">
                                        <h6 class="card-category">Shipping Address</h6>
                                        <p>Recipient Name: ${SHIP_ADDRESS.recipientName}</p>
                                        <p>Line 1: ${SHIP_ADDRESS.line1}</p>
                                        <p>City: ${SHIP_ADDRESS.city}</p>
                                        <p>Country Code: ${SHIP_ADDRESS.countryCode}</p>
                                    </blockquote>
                                    <br />
                                    <a href="cart.jsp" class="btn btn-success btn-round card-link">Back to cart</a>
                                    <input type="submit" class="btn btn-danger btn-round card-link" value="Pay now" />
                                </div>
                            </div>
                        </form>


                    </div>
                </div>

            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
