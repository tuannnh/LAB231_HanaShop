

<%@page import="daos.CategoryDAO"%>
<%@page import="entities.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Food</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="wrapper mt-5">

            <div class="main">
                <div class="section">
                    <div class="container">
                        <h3>Add Product</h3>

                        <form action="CreateFoodServlet" method="POST" class="form-group" enctype="multipart/form-data">
                            <div>
                                <div class="row">
                                    <div class="col-md-5 col-sm-5">
                                        <h6>Product Image</h6>
                                        <div class="fileinput fileinput-new text-center" data-provides="fileinput">
                                            <div class="fileinput-new thumbnail img-no-padding" style="max-width: 370px; max-height: 250px;">
                                                <!--<img src="assets/img/image_placeholder.jpg" alt="...">-->
                                                <img src="images/mac.png" alt="...">
                                            </div>
                                            <div class="fileinput-preview fileinput-exists thumbnail img-no-padding"
                                                 style="max-width: 370px; max-height: 250px;"></div>
                                            <div>
                                                <span class="btn btn-outline-default btn-round btn-file">
                                                    <span class="fileinput-new">Select image</span>
                                                    <span class="fileinput-exists">Change</span>
                                                    <input type="file" name="image">
                                                </span>
                                                <a href="#" class="btn btn-link btn-danger fileinput-exists" data-dismiss="fileinput"><i
                                                        class="fa fa-times"></i> Remove</a>
                                            </div>
                                        </div>

                                        <br/>
                                        <strong>Categories</strong>
                                        <%
                                            CategoryDAO dao = new CategoryDAO();
                                            List<Category> categories = dao.getAllCategories();
                                            pageContext.setAttribute("categoryList", categories);
                                        %>
                                        <select name="txtCategory" class="selectpicker col-md-5 show-tick" data-style="btn-info">
                                            <c:forEach items="${categoryList}" var="category">
                                                <option class="select-option" value="${category.id}">${category} + ${category.id}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                    <div class="col-md-7 col-sm-7">
                                        <div class="form-group">
                                            <h6>Name
                                                <span class="icon-danger">*</span>
                                            </h6>
                                            <input type="text" class="form-control border-input" name="txtName" placeholder="enter the product name here...">
                                        </div>

                                        <div class="row price-row">
                                            <div class="col-md-6">
                                                <h6>Price
                                                    <span class="icon-danger">*</span>
                                                </h6>
                                                <div class="input-group border-input">
                                                    <input name="txtPrice" type="text" value="" placeholder="enter price" class="form-control border-input">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text"><i class="fa fa-usd"></i></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <h6>Quantity
                                                    <span class="icon-danger">*</span>
                                                </h6>
                                                <div class="input-group border-input">
                                                    <input name="txtQuantity" type="text" value="" placeholder="enter quantity" class="form-control border-input">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text"></span>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="form-group mt-3">
                                            <h6>Description
                                                <span class="icon-danger">*</span>
                                            </h6>
                                            <textarea name="txtDescription" class="form-control textarea-limited" placeholder="Description about food" rows="13"
                                                      maxlength="150"></textarea>
                                            <h5>
                                                <small>
                                                    <span id="textarea-limited-message" class="pull-right">150 characters left</span>
                                                </small>
                                            </h5>
                                        </div>

                                    </div>
                                </div>
                                <div class="row buttons-row mt-5">
                                    <div class="col-md-6 col-sm-5">
                                        <a class="btn btn-outline-danger btn-block btn-round" href="admin.jsp">Cancel</a>
                                    </div>
                                    <div class="col-md-6 col-sm-5">
                                        <input type="submit" value="Save" class="btn btn-outline-primary btn-block btn-round"/>
                                    </div>
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
