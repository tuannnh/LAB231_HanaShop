<%-- 
    Document   : admincp
    Created on : Feb 19, 2020, 2:22:48 PM
    Author     : tuannnh
--%>

<%@page import="entities.Coupon"%>
<%@page import="daos.CouponDAO"%>
<%@page import="entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="daos.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category management</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="wrapper">
            <div class="main">

                <div class="section section-white section-search">
                    <div class="container">
                        <div class="row">
                            <h4 class="text-center text-danger">${requestScope.MESSAGE}</h4>
                            <div class=" ml-auto">

                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#exampleModal">
                                    Create Coupon
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="create-category" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <form action="CreateCoupon" method="POST" class="validate-form">

                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Create new Coupon</h5>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="validate-input" data-validate = "Please enter valid name">
                                                        <h5>Coupon Name:</h5>
                                                        <input class="input-group my-input" type="text" name="txtName"/>
                                                    </div>

                                                    <div class="validate-input" data-validate = "Please enter valid discount">
                                                        <h5>Coupon discount:</h5>
                                                        <input class="input-group my-input" type="text" name="txtDiscount"/>
                                                    </div>

                                                </div>
                                                <div class="modal-footer">
                                                    <div class="left-side">
                                                        <button type="button" class="btn btn-default btn-link" data-dismiss="modal">Cancel</button>
                                                    </div>
                                                    <div class="divider"></div>
                                                    <div class="right-side">
                                                        <button type="submit" class="btn btn-danger btn-link">Create</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>


                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <%
                            CouponDAO cdao = new CouponDAO();
                            List<Coupon> coupons = cdao.getAllCoupons();
                            pageContext.setAttribute("LIST", coupons);
                        %>
                        <div class="row">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Name</th>
                                            <th>Discount</th>
                                            <th>Status</th>
                                            <th class="text-right">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach  items="${LIST}" var="coupon" varStatus="counter">
                                            <tr>
                                                <td>${counter.count}</td>
                                                <td>
                                                    <input class="input form-control no-border " name="txtName" type="text" value="${coupon.coupon}">
                                                </td>
                                                <td>
                                                    ${coupon.discount * 100}%
                                                </td>
                                                <td>
                                                    ${coupon.status.statusName}
                                                </td>

                                                <td class="td-actions text-right">
                                                    <form action="DeleteCoupon" class="btn btn-sm btn-link" method="POST">
                                                        <input type="hidden" name="txtCoupon" value="${coupon.coupon}" />
                                                        <button type="submit" data-toggle="tooltip" data-placement="top" title=""
                                                                data-original-title="Change Coupon Status" class="btn btn-danger btn-link btn-sm">
                                                            <i class="fa fa-edit fa-2x"></i>Change
                                                        </button>
                                                    </form>

                                                </td>
                                            </tr>

                                        </c:forEach>

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
