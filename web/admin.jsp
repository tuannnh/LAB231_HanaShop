<%-- 
    Document   : admincp
    Created on : Feb 19, 2020, 2:22:48 PM
    Author     : tuannnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin management</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="wrapper">
            <div class="main">

                <div class="section section-white section-search">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-11 col-12 ml-auto text-center">
                                <form class="d-block form-inline">
                                    <input class="input-xtreme form-control no-border" type="text" placeholder="Search">

                                    <select class="selectpicker col-2 show-tick" data-style="btn-info">
                                        <option class="select-option">Category 1</option>
                                        <option class="select-option">Category 2</option>
                                        <option class="select-option">Category 3</option>
                                    </select>

                                    <select class="selectpicker col-2 show-tick" data-style="btn-info">
                                        <option class="select-option">Status 1</option>
                                        <option class="select-option">Status 2</option>
                                        <option class="select-option">Status 3</option>
                                    </select>

                                    <button type="submit" class="btn btn-info btn-just-icon btn-round"><i
                                            class="nc-icon nc-zoom-split"></i></button>
                                </form>
                            </div>
                            <div class="col-sm-1 ml-auto">
                                <a class="btn btn-success" href="create-food.jsp">Create Food</a>

                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th class="text-center"></th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Quantity</th>
                                            <th class="text-right">Price</th>
                                            <th class="text-right">Actions</th>
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
                                            <td>Develop</td>
                                            <td>2013</td>
                                            <td class="text-right">€ 99,225</td>
                                            <td class="td-actions text-right">
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="View Profile" class="btn btn-info btn-link btn-sm">
                                                    <i class="fa fa-user"></i>
                                                </button>
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Edit Profile" class="btn btn-success btn-link btn-sm">
                                                    <i class="fa fa-edit"></i>
                                                </button>
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Remove" class="btn btn-danger btn-link btn-sm">
                                                    <i class="fa fa-times"></i>
                                                </button>
                                            </td>
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
                                        <tr>
                                            <td class="text-center">3</td>
                                            <td>Alex Mike</td>
                                            <td> Description of food, longer longer longer longer longer longer longer longer longer longer
                                            </td>
                                            <td>2010</td>
                                            <td class="text-right">€ 92,144</td>
                                            <td class="td-actions text-right">
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="View Profile" class="btn btn-info btn-link btn-sm">
                                                    <i class="fa fa-user"></i>
                                                </button>
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Edit Profile" class="btn btn-success btn-link btn-sm">
                                                    <i class="fa fa-edit"></i>
                                                </button>
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Remove" class="btn btn-danger btn-link btn-sm">
                                                    <i class="fa fa-times"></i>
                                                </button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="text-center">4</td>
                                            <td>Mike Monday</td>
                                            <td>Marketing</td>
                                            <td>2013</td>
                                            <td class="text-right">€ 49,990</td>
                                            <td class="td-actions text-right">
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="View Profile" class="btn btn-info btn-link btn-sm">
                                                    <i class="fa fa-user"></i>
                                                </button>
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Edit Profile" class="btn btn-success btn-link btn-sm">
                                                    <i class="fa fa-edit"></i>
                                                </button>
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Remove" class="btn btn-danger btn-link btn-sm">
                                                    <i class="fa fa-times"></i>
                                                </button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="text-center">5</td>
                                            <td>Paul Dickens</td>
                                            <td>Communication</td>
                                            <td>2016</td>
                                            <td class="text-right">€ 69,201</td>
                                            <td class="td-actions text-right">
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="View Profile" class="btn btn-info btn-link btn-sm">
                                                    <i class="fa fa-user"></i>
                                                </button>
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Edit Profile" class="btn btn-success btn-link btn-sm">
                                                    <i class="fa fa-edit"></i>
                                                </button>
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Remove" class="btn btn-danger btn-link btn-sm">
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
