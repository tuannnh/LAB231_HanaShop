<%-- 
    Document   : index
    Created on : Feb 19, 2020, 7:05:27 AM
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
                    <h4 class="text-center text-danger">${requestScope.MESSAGE}</h4>
                    <div class="container">
                        <div class="row">

                            <div class="col-md-12 col-12 ml-auto mr-auto text-center">
                                <jsp:useBean id="categoriesBean" class="models.CategoryList" scope="request"/>
                                <c:set var="categoryList" value="${categoriesBean.categories}" scope="request"/>

                                <form action="SearchFood" method="POST" role="search" class="form-inline col-md-12 ml-auto mr-auto text-center">
                                    <div class="input-group no-border col-md-10 mr-auto">
                                        <div class="col-md-6">
                                            <button type="submit" class="btn-link addon-xtreme no-border mb-3" id="basic-addon1"><i
                                                    class="fa fa-search"></i></button>
                                            <input name="txtUserSearch" value="${sessionScope.USER_SEARCH_NAME}" type="text" class="form-control input-xtreme no-border" placeholder="Search here..." />

                                        </div>
                                        <div class="col-md-3 mt-3">
                                            <select name="txtUserCategory" class="selectpicker show-tick" data-style="btn-info">
                                                <option class="select-option" value="0" 
                                                        <c:if test="${sessionScope.USER_SEARCH_CATEGORY eq '0'}">selected</c:if> >All category
                                                        </option>
                                                <c:forEach items="${categoryList}" var="category">
                                                    <option class="select-option" value="${category.id}" 
                                                            <c:if test="${sessionScope.USER_SEARCH_CATEGORY eq category.id}">selected</c:if> >${category}
                                                            </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div id="my-slider" class="slider slider-primary noUi-target noUi-ltr noUi-horizontal noUi-background"></div>
                                    </div>
                                    <input id="priceMin" name="txtUserMin" value="${sessionScope.USER_SEARCH_MIN}" placeholder="1" id="lowest" type="text"
                                           class="form-inline input-group-sm ml-4"/>
                                    <label class="ml-3 mr-3">-</label>
                                    <input id="priceMax" name="txtUserMax" value="${sessionScope.USER_SEARCH_MAX}" placeholder="5000" id="highest" type="text"
                                           class="form-inline input-group-sm"/>
                                </form>
                            </div>
                        </div>
                        <br/>
                        <div class="row  text-center">
                            <c:if test="${sessionScope.USER_PRODUCTS eq null}">
                                <jsp:useBean id="productsBean" class="models.ProductList" scope="request"/>
                                <c:set var="USER_PRODUCTS" value="${productsBean.products}" scope="session"/>
                            </c:if>
                            <c:if test="${requestScope.PAGE eq null}">
                                <c:set var="PAGE" value="1" scope="request"/>
                            </c:if>
                            <c:set var="OFFSET" value="20" scope="request"/>
                            <c:set var="TOTAL_PAGE" value="${Math.ceil(sessionScope.USER_PRODUCTS.size()/OFFSET)}" scope="request"/>
                            <c:set var="USER_SEARCH_LIST" value="${requestScope.USER_PRODUCTS}" scope="session"/>
                            <c:forEach items="${sessionScope.USER_PRODUCTS}" var="product" begin="${(requestScope.PAGE - 1)*OFFSET}" end="${(requestScope.PAGE - 1)*OFFSET + (OFFSET -1)}">

                                <div class="col-md-3 col-sm-4">
                                    <div class="card card-product card-plain">
                                        <div class="card-image">
                                            <img src="${product.imageURL}" alt="Rounded Image" class="img-rounded img-responsive">
                                            <div class="card-body">

                                                <h5 class=""><strong>${product.name}</strong> </h5>
                                                <h5 class="card-title"><strong>$${product.price}</strong></h5>
                                                <hr>
                                                <p>${product.description}</p>
                                                <hr>
                                                <h6>${product.createDate}</h6>
                                                <h6>Category:
                                                    <span class="badge badge-pill badge-default">${product.category}</span>
                                                </h6>
                                                <h6>Quantity: <span class="badge badge-pill badge-info">${product.quantity}</span></h6>
                                                <hr>
                                                <div class="row justify-content-center">
                                                    <form action="AddToCart" method="POST">
                                                        <input type="hidden" name="txtId" value="${product.id}" />
                                                        <input type="hidden" name="callPage" value="index.jsp" />
                                                        <button type="submit" class="btn btn-info btn-center btn-round">Add to Cart &nbsp;<i
                                                                class="fa fa-chevron-right"></i></button>
                                                    </form>

                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </c:forEach>

                        </div>

                        <c:if test="${TOTAL_PAGE ne 0}">
                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-end">

                                    <li class="page-item  <c:if test="${requestScope.PAGE eq '1'}">disabled</c:if>" >
                                        <c:url var="ChangePageLink" value="ChangePage">
                                            <c:param name="txtUserSearch" value="${sessionScope.USER_SEARCH_NAME}"/>
                                            <c:param name="txtUserCategory" value="${sessionScope.USER_SEARCH_CATEGORY}"/>
                                            <c:param name="txtUserMin" value="${sessionScope.USER_SEARCH_MIN}"/>
                                            <c:param name="txtUserMax" value="${sessionScope.USER_SEARCH_MAX}"/>
                                            <c:param name="pageIndex" value="${requestScope.PAGE - 1}"/>
                                        </c:url>

                                        <a class="page-link" href="${ChangePageLink}" tabindex="-1">Previous</a>
                                    </li>

                                    <c:forEach begin="1" end="${TOTAL_PAGE}" varStatus="counter">

                                        <c:url var="ChangePageLink" value="ChangePage">
                                            <c:param name="txtUserSearch" value="${sessionScope.USER_SEARCH_NAME}"/>
                                            <c:param name="txtUserCategory" value="${sessionScope.USER_SEARCH_CATEGORY}"/>
                                            <c:param name="txtUserMin" value="${sessionScope.USER_SEARCH_MIN}"/>
                                            <c:param name="txtUserMax" value="${sessionScope.USER_SEARCH_MAX}"/>
                                            <c:param name="pageIndex" value="${counter.count}"/>
                                        </c:url>

                                        <li class="page-item <c:if test="${requestScope.PAGE eq counter.count}">active</c:if>"><a class="page-link" href="${ChangePageLink}">${counter.count}</a></li>
                                        </c:forEach>
                                    <li class="page-item <c:if test="${requestScope.PAGE eq TOTAL_PAGE}">disabled</c:if>" >
                                        <c:url var="ChangePageLink" value="ChangePage">
                                            <c:param name="txtUserSearch" value="${sessionScope.USER_SEARCH_NAME}"/>
                                            <c:param name="txtUserCategory" value="${sessionScope.USER_SEARCH_CATEGORY}"/>
                                            <c:param name="txtUserMin" value="${sessionScope.USER_SEARCH_MIN}"/>
                                            <c:param name="txtUserMax" value="${sessionScope.USER_SEARCH_MAX}"/>
                                            <c:param name="pageIndex" value="${requestScope.PAGE + 1}"/>
                                        </c:url>

                                        <a class="page-link" href="${ChangePageLink}" tabindex="-1">Next</a>
                                    </li>
                                </ul>
                            </nav>
                        </c:if>
                        <jsp:useBean id="suggestsBean" class="models.SuggestList" scope="request">
                            <jsp:setProperty name="suggestsBean" property="purchasedItems" value="${sessionScope.CART.purchasedItems}"/>
                        </jsp:useBean>
                            <c:set var="USER_SUGGEST" value="${suggestsBean.suggests}" scope="session"/>

                        <c:if test="${USER_SUGGEST.size() > 0}">
                            <div class="col-md-12">
                                <h4 class="title">Recommendation</h4>
                            </div>

                            <div class="row  text-center">
                                <c:forEach items="${USER_SUGGEST}" var="product" begin="0" end="9">

                                    <div class="col-md-2 col-sm-4">
                                        <div class="card card-product card-plain">
                                            <div class="card-image">
                                                <img src="${product.imageURL}" alt="Rounded Image" class="img-rounded img-responsive">
                                                <div class="card-body">

                                                    <p class=""><strong>${product.name}</strong> </p>
                                                    <p class="card-title"><strong>$${product.price}</strong></p>
                                                    <hr>
                                                    <p>${product.description}</p>
                                                    <span>${product.createDate}</span>
                                                    <h6>Category:
                                                        <span class="badge badge-pill badge-default">${product.category}</span>
                                                    </h6>
                                                    <p>Quantity: <span class="badge badge-pill badge-info">${product.quantity}</span></p>
                                                    <div class="row justify-content-center">
                                                        <form action="AddToCart" method="POST">
                                                            <input type="hidden" name="txtId" value="${product.id}" />
                                                            <input type="hidden" name="callPage" value="cart.jsp" />
                                                            <button type="submit" class="btn btn-info btn-center btn-round">Add to Cart &nbsp;<i
                                                                    class="fa fa-chevron-right"></i></button>
                                                        </form>

                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </c:forEach>

                            </div>
                        </c:if>

                    </div>
                </div>

            </div>
        </div>


        <%@include file="footer.jsp" %>
    </body>
</html>
