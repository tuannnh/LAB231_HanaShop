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
        <title>Food management</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="wrapper">
            <div class="main">

                <div class="section section-white section-search">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-10 ml-auto text-center">
                                <form action="AdminSearch" method="Post" class="d-block form-inline">
                                    <input name="txtAdminSearch" value="${sessionScope.ADMIN_SEARCH_NAME}" type="text" class="input-xtreme form-control no-border" placeholder="Search">
                                    <jsp:useBean id="categoriesBean" class="models.CategoryList" scope="request"/>
                                    <c:set var="categoryList" value="${categoriesBean.categories}" scope="request"/>
                                    <select name="txtAdminCategory" class="selectpicker col-md-3 show-tick" data-style="btn-info">
                                        <option class="select-option" value="0" 
                                                <c:if test="${sessionScope.ADMIN_SEARCH_CATEGORY eq '0'}">selected</c:if> >All category
                                                </option>
                                        <c:forEach items="${categoryList}" var="category">
                                            <option class="select-option" value="${category.id}" 
                                                    <c:if test="${sessionScope.ADMIN_SEARCH_CATEGORY eq category.id}">selected</c:if> >${category}
                                                    </option>
                                        </c:forEach>
                                    </select>

                                    <select name="txtAdminStatus" class="selectpicker col-3 show-tick" data-style="btn-info">
                                        <option class="select-option" value="All" 
                                                <c:if test="${sessionScope.ADMIN_SEARCH_STATUS eq 'All'}">selected</c:if> >All status
                                                </option>
                                                <option class="select-option" value="Inactive" 
                                                <c:if test="${sessionScope.ADMIN_SEARCH_STATUS eq 'Inactive'}">selected</c:if> >Inactive
                                                </option>
                                                <option class="select-option" value="Active" 
                                                <c:if test="${sessionScope.ADMIN_SEARCH_STATUS eq 'Active'}">selected</c:if> >Active
                                                </option>

                                        </select>



                                        <button type="submit" class="btn btn-info btn-just-icon btn-round"><i
                                                class="nc-icon nc-zoom-split"></i></button>
                                    </form>
                                </div>
                                <div class="col-md-2 mt-3">
                                <c:url var="CreateFoodLink" value="create-food.jsp">
                                    <c:param name="txtAdminSearch" value="${sessionScope.ADMIN_SEARCH_NAME}"/>
                                    <c:param name="txtAdminCategory" value="${sessionScope.ADMIN_SEARCH_CATEGORY}"/>
                                    <c:param name="txtAdminStatus" value="${sessionScope.ADMIN_SEARCH_STATUS}"/>
                                    <c:param name="searchPageIndex" value="${requestScope.PAGE}"/>
                                </c:url>

                                <a class="btn btn-success" href="${CreateFoodLink}">Create Food</a>

                            </div>
                        </div>
                        <br/>

                        <c:if test="${sessionScope.ADMIN_PRODUCTS eq null}">
                            <jsp:useBean id="productsBean" class="models.ProductList" scope="request"/>
                            <c:set var="USER_PRODUCTS" value="${productsBean.productsAdmin}" scope="session"/>
                        </c:if>

                        <c:if test="${requestScope.PAGE eq null}">
                            <c:set var="PAGE" value="1" scope="request"/>
                        </c:if>

                        <c:set var="OFFSET" value="20" scope="request"/>
                        <c:set var="TOTAL_PAGE" value="${Math.ceil(sessionScope.ADMIN_PRODUCTS.size()/OFFSET)}" scope="request"/>
                        <c:set var="ADMIN_SEARCH_LIST" value="${requestScope.ADMIN_PRODUCTS}" scope="session"/>

                        <div class="row">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th class="text-center"></th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Quantity</th>
                                            <th>Status</th>
                                            <th>Create Date</th>
                                            <th >Price</th>
                                            <th class="text-right">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${sessionScope.ADMIN_PRODUCTS}" var="product" begin="${(requestScope.PAGE - 1)*OFFSET}" end="${(requestScope.PAGE - 1)*OFFSET + (OFFSET-1)}">
                                            <tr>
                                                <td class="text-center">
                                                    <div class="img-container" style="max-width: 50px; max-height: 50px;">
                                                        <img src="${product.imageURL}" alt="...">
                                                    </div>
                                                </td>

                                                <td>${product.name}</td>
                                                <td>${product.description}</td>
                                                <td>${product.quantity}</td>
                                                <td>${product.status}</td>
                                                <td>${product.createDate}</td>
                                                <td>$${product.price}</td>
                                                <td class="td-actions text-right">
                                                    <form action="ViewFood" class="btn btn-sm btn-link" method="POST">
                                                        <input type="hidden" name="txtId" value="${product.id}" />
                                                        <button type="submit" data-toggle="tooltip" data-placement="top" title=""
                                                                data-original-title="Edit Profile" class="btn btn-success btn-link btn-sm">
                                                            <i class="fa fa-edit"></i>
                                                        </button>
                                                    </form>

                                                    <form action="DeleteFood" class="btn btn-sm btn-link" method="POST">
                                                        <input type="hidden" name="txtId" value="${product.id}" />
                                                        <button type="submit" data-toggle="tooltip" data-placement="top" title=""
                                                                data-original-title="Remove" class="btn btn-danger btn-link btn-sm">
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

                        <c:if test="${TOTAL_PAGE ne 0}">
                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-end">

                                    <li class="page-item  <c:if test="${requestScope.PAGE eq '1'}">disabled</c:if>" >
                                        <c:url var="ChangePageAdminLink" value="ChangePageAdmin">
                                            <c:param name="txtSearch" value="${sessionScope.ADMIN_SEARCH_NAME}"/>
                                            <c:param name="txtCategory" value="${sessionScope.ADMIN_SEARCH_CATEGORY}"/>
                                            <c:param name="txtStatus" value="${sessionScope.ADMIN_SEARCH_STATUS}"/>
                                            <c:param name="pageIndex" value="${requestScope.PAGE - 1}"/>
                                        </c:url>
                                        <a class="page-link" href="${ChangePageAdminLink}" tabindex="-1">Previous</a>
                                    </li>

                                    <c:forEach begin="1" end="${TOTAL_PAGE}" varStatus="counter">

                                        <c:url var="ChangePageAdminLink" value="ChangePageAdmin">
                                            <c:param name="txtSearch" value="${sessionScope.ADMIN_SEARCH_NAME}"/>
                                            <c:param name="txtCategory" value="${sessionScope.ADMIN_SEARCH_CATEGORY}"/>
                                            <c:param name="txtStatus" value="${sessionScope.ADMIN_SEARCH_STATUS}"/>
                                            <c:param name="pageIndex" value="${counter.count}"/>
                                        </c:url>

                                        <li class="page-item <c:if test="${requestScope.PAGE eq counter.count}">active</c:if>"><a class="page-link" href="${ChangePageAdminLink}">${counter.count}</a></li>
                                        </c:forEach>
                                    <li class="page-item <c:if test="${requestScope.PAGE eq TOTAL_PAGE}">disabled</c:if>" >
                                        <c:url var="ChangePageAdminLink" value="ChangePageAdmin">
                                            <c:param name="txtSearch" value="${sessionScope.ADMIN_SEARCH_NAME}"/>
                                            <c:param name="txtCategory" value="${sessionScope.ADMIN_SEARCH_CATEGORY}"/>
                                            <c:param name="txtStatus" value="${sessionScope.ADMIN_SEARCH_STATUS}"/>
                                            <c:param name="pageIndex" value="${requestScope.PAGE + 1}"/>
                                        </c:url>

                                        <a class="page-link" href="${ChangePageAdminLink}">Next</a>
                                    </li>
                                </ul>
                            </nav>
                        </c:if>

                    </div>
                </div>

            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
