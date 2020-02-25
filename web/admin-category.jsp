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
                            <h4 class="text-center text-danger">${requestScope.MESSAGE}</h4>
                            <div class=" ml-auto">

                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#exampleModal">
                                    Create Category
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="create-category" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <form action="CreateCategory" method="POST">

                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Create new Category</h5>
                                                </div>
                                                <div class="modal-body">
                                                    <h5>Category Name:</h5>
                                                    <input class="input-group" type="text" name="txtName" required=""/>
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
                                        <c:forEach  items="${categoryList}" var="category" varStatus="counter">
                                            <tr>
                                        <form action="UpdateCategory" class="btn btn-sm btn-link" method="POST">
                                            <td>${counter.count}</td>
                                            <td>
                                                <input class="input form-control no-border " name="txtName" type="text" value="${category.name}">
                                            </td>
                                            <td class="td-actions text-right">

                                                <input type="hidden" name="txtId" value="${category.id}" />
                                                <button type="submit" data-toggle="tooltip" data-placement="top" title=""
                                                        data-original-title="Update Category" class="btn btn-success btn-link btn-sm">
                                                    <i class="fa fa-edit"></i>
                                                </button>
                                        </form>
                                        <form action="DeleteCategory" class="btn btn-sm btn-link" method="POST">
                                            <input type="hidden" name="txtId" value="${category.id}" />
                                            <button type="submit" data-toggle="tooltip" data-placement="top" title=""
                                                    data-original-title="Remove Category" class="btn btn-danger btn-link btn-sm">
                                                <i class="fa fa-times"></i>
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
