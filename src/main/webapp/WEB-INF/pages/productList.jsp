<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="products" type="java.util.ArrayList<com.es.phoneshop.model.Product>" scope="request"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
    <head>
        <link href="<c:url value="/resources/style/PLPTheme/PLPTheme.css" />" rel="stylesheet">
    </head>

    <title>Product List</title>

    <header>
        <jsp:include page="header.jsp"/>
        <div style="text-align: center;">
            <h2 style="color: #000000;">Product List Page</h2>
        </div>
    </header>

    <body style="background: #C5CAE9;">
        <form method="post">
            <input type="text" id="search-text" name="search-text" value="${userInput == null ? null : userInput}" >
            <input type="submit" value="Search">
        </form>

        <c:forEach var="product" items="${products}">

            <form method="post" name="${product.id}">
                <c:set var="tempId" value="${product.id}"/>

                <div class="type${products.indexOf(product)%4 == 0 ? 0 : 1}" style="background: #E8EAF6">
                    <table>
                        <tr>
                            <td>Id</td>
                            <td><c:out value="${product.id}"/></td>
                        </tr>
                        <tr>
                            <td>Code</td>
                            <td>
                                <a href = "<c:url value = "/products/${product.id}" />"><c:out value="${product.code}"/></a>
                            </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td><c:out value="${product.description}"/></td>
                        </tr>
                        <tr>
                            <td>Price</td>
                            <td><fmt:formatNumber value = "${product.price}"/></td>
                        </tr>
                        <tr>
                            <td>Currency</td>
                            <td><c:out value="${product.currency}"/></td>
                        </tr>

                        <tr>
                            <td>Quantity</td>
                            <td><input type="text" value="1" name="quantity" style="width: 40%"/></td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" value="Add to cart" style="width: 200%; border-radius: 10px;"/>
                            </td>
                        </tr>
                         <tr>
                             <td>
                                 <c:if test="${not empty message && (id == tempId)}">
                                     <label style="color: ${message == "Product_added" ? "green" : "red"};width: 200%;" id="test"> <fmt:message key="message.${message}" /><label>
                                 </c:if>

                                 <c:if test="${not empty message && (id != tempId)}">
                                    <br>
                                 </c:if>
                             </td>
                         </tr>
                    </table>
                </div>

                <input type="hidden" value="${product.id}" name="productId">

            <c:if test="${products.indexOf(product)%4 == 3}">
                <div class="clearer"></div>
            </c:if>

            </form>
        </c:forEach>
    </body>

    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>

</html>

