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
        <title>Hana Shop</title>
    </head>
    <body>
        <%@include file="header.jsp" %>


        <div class="wrapper">
            <div class="main">

                <div class="section section-white section-search">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 col-12 ml-auto  text-center">
                                <form class="d-block form-inline">
                                    <input class="input-xtreme form-control no-border" type="text" placeholder="Search">
                                    <input class="input-xtreme form-control no-border" type="text" placeholder="20/02/2020">


                                    <button type="submit" class="btn btn-info btn-just-icon btn-round"><i
                                            class="nc-icon nc-zoom-split"></i></button>
                                </form>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <h4 class="title">Order ID:</h4>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th class="text-center"></th>
                                            <th>Name</th>
                                            <th class="text-center">Purchase Date</th>
                                            <th class="text-center">Quantity</th>
                                            <th class="text-center">Price</th>
                                            <th class="text-center">Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="text-center">
                                                <div class="img-container" style="max-width: 50px; max-height: 50px;">
                                                    <img src="https://images.thenorthface.com/is/image/TheNorthFace/NF0A2VFL_619_hero" alt="...">
                                                </div>
                                            </td>

                                            <td>Andrew Mike</td>
                                            <td class="text-center">Develop</td>
                                            <td class="text-center">2013</td>
                                            <td class="text-center">€ 99,225</td>
                                            <td class="text-center">1000$</td>
                                        </tr>
                                        <tr>
                                            <td class="text-center">2</td>
                                            <td>John Doe</td>
                                            <td>Design</td>
                                            <td>2012</td>
                                            <td class="text-right">€ 89,241</td>
                                            <td class="td-actions text-right">

                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Edit Product" class="btn btn-success btn-link btn-sm">
                                                    <i class="fa fa-edit"></i>
                                                </button>
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Delete" class="btn btn-danger btn-link btn-sm">
                                                    <i class="fa fa-times"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-md-12">
                                <h4 class="title">Order ID:</h4>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th class="text-center"></th>
                                            <th>Name</th>
                                            <th class="text-center">Purchase Date</th>
                                            <th class="text-center">Quantity</th>
                                            <th class="text-center">Price</th>
                                            <th class="text-center">Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="text-center">
                                                <div class="img-container" style="max-width: 50px; max-height: 50px;">
                                                    <img src="https://images.thenorthface.com/is/image/TheNorthFace/NF0A2VFL_619_hero" alt="...">
                                                </div>
                                            </td>

                                            <td>Andrew Mike</td>
                                            <td class="text-center">Develop</td>
                                            <td class="text-center">2013</td>
                                            <td class="text-center">€ 99,225</td>
                                            <td class="text-center">1000$</td>
                                        </tr>
                                        <tr>
                                            <td class="text-center">2</td>
                                            <td>John Doe</td>
                                            <td>Design</td>
                                            <td>2012</td>
                                            <td class="text-right">€ 89,241</td>
                                            <td class="td-actions text-right">

                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Edit Product" class="btn btn-success btn-link btn-sm">
                                                    <i class="fa fa-edit"></i>
                                                </button>
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Delete" class="btn btn-danger btn-link btn-sm">
                                                    <i class="fa fa-times"></i>
                                                </button>
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
