<%@ page contentType="text/html; charset = UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="compareQue" type="com.es.phoneshop.comparePage.CompareQue" scope="request"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <link href="<c:url value="/resources/style/CartThem/CartThem.css" />" rel="stylesheet">
</head>

<title>Compare page</title>

<header>
    <jsp:include page="/WEB-INF/pages/header.jsp"/>
</header>

<body style="background: #C5CAE9;">
    <div style="text-align: center;">
        <h2>Compare page</h2>
    </div>
<c:if test="${compareQue.productQue.size() > 0}">
    <table style="background: #E8EAF6">
        <tr>
            <td style="border: white"></td>
            <c:forEach var="product" items="${compareQue.productQue}">
                <td><c:out value="Product${product.id}"/></td>
            </c:forEach>
        </tr>
        <tr>
            <td>Id</td>
            <c:forEach var="product" items="${compareQue.productQue}">
                <td><c:out value="${product.id}"/></td>
            </c:forEach>
        </tr>
        <tr>
            <td>Code</td>
            <c:forEach var="product" items="${compareQue.productQue}">
                <td>
                <a href = "<c:url value = "/products/${product.id}" />"><c:out value="${product.code}"/></a>
                </td>
            </c:forEach>
        </tr>
        <tr>
            <td>Desc</td>
            <c:forEach var="product" items="${compareQue.productQue}">
                <td><c:out value="${product.description}"/></td>
            </c:forEach>
        </tr>
        <tr>
            <td>Price</td>
            <c:forEach var="product" items="${compareQue.productQue}">
                <td><fmt:formatNumber value = "${product.price}"/></td>
            </c:forEach>
        </tr>
        <tr>
            <td>Currency</td>
            <c:forEach var="product" items="${compareQue.productQue}">
                <td><c:out value="${product.currency}"/></td>
            </c:forEach>
        </tr>
        <tr>
            <td></td>
            <c:forEach var="product" items="${compareQue.productQue}">
            <td>
                <form method="post" name="form${product.id}">
                    <input type="text" id="quantity" name="quantity">
                    <input type="submit" value="Add to cart">
                    <input type="hidden" name="productId" value="${product.id}">
                </form>
                <c:if test="${not empty message && (id == product.id)}">
                    <label style="color: ${message == "Product_added" ? "green" : "red"};width: 200%;" id="test"> <fmt:message key="message.${message}" /><label>
                </c:if>
            </td>
            </c:forEach>
        </tr>

    </table>
</c:if>
<div style="text-align: center;">
    <a href="/phoneshop-servlet-api/checkout" target="_parent"><button style="outline:none; padding:7px 20px; color:#FFF; cursor:pointer; background:#d2232a; border:none; margin:1em auto 0;">To checkout page!</button></a>
</div>

</body>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</html>
