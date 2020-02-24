<%-- 
    Document   : admincp
    Created on : Feb 19, 2020, 2:22:48 PM
    Author     : tuannnh
--%>

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
                            <div class=" ml-auto">
                                <a class="btn btn-success" href="">Create Category</a>

                            </div>
                        </div>
                        <br/>
                        <%
                            CategoryDAO cdao = new CategoryDAO();
                            List<Category> categories = cdao.getAllCategories();
                            pageContext.setAttribute("categoryList", categories);
                        %>
                        <div class="row">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Name</th>
                                            <th class="text-right">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>

                                                Develop
                                            </td>
                                            <td class="td-actions text-right">
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Update Category" class="btn btn-success btn-link btn-sm">
                                                    <i class="fa fa-edit"></i>
                                                </button>
                                                <button type="button" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Remove Category" class="btn btn-danger btn-link btn-sm">
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
