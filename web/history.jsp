<%-- 
    Document   : history
    Created on : Feb 19, 2020, 2:41:17 PM
    Author     : tuannnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
    </head>
    <body>
        <%@include file="header.jsp" %>


        <div class="wrapper">
            <div class="main">

                <div class="section section-white section-search">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 col-12 ml-auto  text-center">
                                <form action="SearchHistory" method="POST" class="d-block form-inline">
                                    <input class="input-xtreme form-control no-border" name="txtHistorySearchName" value="${sessionScope.HISTORY_SEARCH_NAME}" type="text" placeholder="Search">
                                    <input class="input form-control " name="txtHistoryDateStart" type="text" value="${sessionScope.HISTORY_SEARCH_DATE_START}" placeholder="">
                                    <input class="input form-control " name="txtHistoryDateEnd" type="text" value="${sessionScope.HISTORY_SEARCH_DATE_END}" placeholder="">
                                    <button type="submit" class="btn btn-info btn-just-icon btn-round"><i
                                            class="nc-icon nc-zoom-split"></i></button>
                                </form>
                            </div>
                        </div>

                        <c:forEach items="${sessionScope.HISTORY_LIST}" var="invoice">
                            <div class="row">
                                <div class="col-md-12">
                                    <h4 class="title">Order ID: <strong>${invoice.id}</strong>  - Purchase Date: <strong>${invoice.purchaseDate}</strong> </h4>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th class="text-center"></th>
                                                <th>Name</th>
                                                <th class="text-center">Quantity</th>
                                                <th class="text-center">Price</th>
                                                <th class="text-center">Subtotal</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${invoice.orderItemList}" var="item">
                                                <tr>
                                                    <td class="text-center">
                                                        <div class="img-container" style="max-width: 50px; max-height: 50px;">
                                                            <img src="${item.productId.imageURL}" alt="...">
                                                        </div>
                                                    </td>

                                                    <td>${item.productId.name}</td>
                                                    <td class="text-center">${item.quantity}</td>
                                                    <td class="text-center">$ ${item.stringPrice}</td>
                                                    <td class="text-center">$ ${item.subTotal}</td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <td colspan="2"></td>
                                                <td></td>
                                                <td class="td-total text-right">
                                                    Total:
                                                </td>
                                                <td class="td-total text-center">
                                                    <small>$ </small>${invoice.total}
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:forEach>



                    </div>
                </div>

            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
