<%-- 
    Document   : cart
    Created on : Feb 19, 2020, 2:39:39 PM
    Author     : tuannnh
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

                        <div class="col-md-12">
                            <h4 class="title">Your Shopping Cart</h4>
                        </div>
                        <div class="col-md-10 ml-auto mr-auto">
                            <div class="table-responsive">
                                <table class="table table-shopping">
                                    <thead>
                                        <tr>
                                            <th class="text-center"></th>
                                            <th class="text-center">Product</th>
                                            <th class="text-right">Price</th>
                                            <th class="text-right">Quantity</th>
                                            <th class="text-right">Subtotal</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${sessionScope.CART.purchasedItems}" var="item" varStatus="counter">

                                            <tr>
                                                <td>
                                                    <div class="img-container">
                                                        <img src="${item.imageURL}" alt="Agenda">
                                                    </div>
                                                </td>
                                                <td class="td-product">
                                                    <strong>${item.name}</strong>
                                                    <p>${item.description}</p>
                                                </td>
                                                <td class="td-price">
                                                    <small>$</small><label id="${counter.count}-price">${item.stringPrice} </label>
                                                </td>
                                                <td class="td-number td-quantity align-middle">
                                                    <c:if test="${requestScope.ID eq item.id}">
                                                        <p class="text-right text-danger">${requestScope.MESSAGE}<p>
                                                        <p class="text-right text-danger">${item.error}<p>
                                                        </c:if>
                                                    <form id="${counter.count}-form" class="text-right" action="UpdateCart" method="POST">
                                                        <div class="btn-group">

                                                            <input type="button" value="-" class="btn btn-sm btn-border btn-round" onclick="removeItem(${counter.count}, '${item.name}')">
                                                            <input id="${counter.count}-quantity" class="input-quantity text-center" type="text" value="${item.quantity}"/>
                                                            <input type="hidden" name="txtId" value="${item.id}" />
                                                            <input type="submit" value="Update" name="txtAction" hidden="true" />
                                                            <input type="button" value="+" class="btn btn-sm btn-border btn-round" onclick="addItem(${counter.count})">
                                                        </div>
                                                    </form>

                                                </td>
                                                <td class="td-number">
                                                    <label>$ </label><label id="${counter.count}-subtotal">${item.subTotal}</label>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        <tr>
                                            <td colspan="2"></td>
                                            <td></td>
                                            <td class="td-total">
                                                Total
                                            </td>
                                            <td class="td-total">
                                                <small>$</small>${sessionScope.CART.total}
                                            </td>

                                        </tr>
                                        <tr class="tr-actions">
                                            <td colspan="3"></td>
                                            <td colspan="2" class="text-right flex-fill">


                                                <a href="index.jsp"  class="btn btn-info btn-block"><i class="fa fa-chevron-left"></i>
                                                    Continue shopping </a>
                                                <a href="CompletePurchase" class="btn btn-danger btn-block">Complete Purchase <i
                                                        class="fa fa-chevron-right"></i></a>
                                                <a href="AuthorizePaypal" class="btn-paypal btn btn-warning btn-block"><i
                                                        class="fa"></i></a>


                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>


                    </div>
                </div>

            </div>
        </div>

        <%@include file="footer.jsp" %>


    </body>
</html>
