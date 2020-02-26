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
                                <div class="col-md-12 form-inline">
                                    <form method="POST" action="Rating">
                                        <h4 class="title">Invoice ID: <strong>${invoice.id}</strong>  - Purchase Date: <strong>${invoice.purchaseDate}</strong> 
                                            - Rating: 
                                            <input type="hidden" name="txtId" value="${invoice.id}"/>
                                            <input type="hidden" name="txtRating" class="rating my-rating" data-filled="fa fa-star fa-2x custom-star" 
                                                   data-empty="fa fa-star-o fa-2x" data-fractions="2" data-step="2" data-stop="10" value=${invoice.rating}/>

                                        </h4>
                                    </form>
                                </div>
                                <div class="table-responsive">
                                    <table class="table">
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
                                                            <img src="${item.product.imageURL}" alt="...">
                                                        </div>
                                                    </td>

                                                    <td class="text-left ">${item.product.name}</td>
                                                    <td class="text-center">${item.quantity}</td>
                                                    <td class="text-center">$ ${item.stringPrice}</td>
                                                    <td class="text-center">$ ${item.subTotal}</td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <hr/>
                            <div class="container col-md-12 form-inline">
                                <div class="col-10 text-right ml-auto">
                                    <p>Coupon:</p>
                                    <p> Discount:</p>
                                    <h5 class="td-total"><strong>Total:</strong></h5>
                                </div>

                                <div class="col-2 text-left ml-auto">
                                    <p> ${invoice.coupon.coupon}‎‎‏‏‎ ‎</p>
                                    <p> ${invoice.coupon.discount*invoice.total}</p>
                                    <h5 class="td-total"><strong><small>$ </small>${invoice.total}</strong></h5>
                                </div>
                            </div>

                        </c:forEach>



                    </div>
                </div>

            </div>
        </div>

        <%@include file="footer.jsp" %>
        <script>

            $(".my-rating").on('change', function () {
                $(this).closest("form").submit();
            });

        </script>
    </body>
</html>
