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
                                    <div class="col-sm-4 ml-auto mr-auto">

                                        <input class="form-control mr-auto" name="txtHistorySearchName" value="${sessionScope.HISTORY_SEARCH_NAME}" type="text" placeholder="Search">
                                        <div class="input-group date form-inline" id="dateStart">
                                            <input id="dateStartInput" type="text" name="txtHistoryDateStart" value="${sessionScope.HISTORY_SEARCH_DATE_START}" class="form-control datetimepicker date-input" onkeypress="return false;">
                                            <div class="input-group-append">
                                                <span class="input-group-text">
                                                    <span class="glyphicon glyphicon-calendar"><i class="fa fa-calendar" aria-hidden="true"></i></span>
                                                </span>
                                            </div>
                                        </div>

                                        <div class="input-group date form-inline" id="dateEnd">
                                            <input type="text" name="txtHistoryDateEnd" value="${sessionScope.HISTORY_SEARCH_DATE_END}" class="form-control datetimepicker date-input" onkeypress="return false;">
                                            <div class="input-group-append">
                                                <span class="input-group-text">
                                                    <span class="glyphicon glyphicon-calendar"><i class="fa fa-calendar" aria-hidden="true"></i></span>
                                                </span>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-info btn-just-icon btn-round"><i
                                                class="nc-icon nc-zoom-split"></i></button>
                                    </div>


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
