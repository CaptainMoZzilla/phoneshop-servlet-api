<%@ page contentType="text/html; charset = UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="cart" type="com.es.phoneshop.cart.Cart" scope="request"/>

<html>
<head>
    <link href="<c:url value="/resources/style/CartThem/CartThem.css" />" rel="stylesheet">
</head>

<title>Cart</title>

<header>
    <jsp:include page="/WEB-INF/pages/header.jsp"/>
</header>

<body>
<div style="text-align: center;">
    <h2>Welcome to your cart</h2>
</div>
<c:out value="${message}"/>
<table>
    <thead>
    <tr>
        <td>Id</td>
        <td>Code</td>
        <td>Description</td>
        <td>Price</td>
        <td>Currency</td>
        <td>Quantity</td>
        <td>Remove</td>
    </tr>
    </thead>
    <c:forEach var="cartItem" items="${cart.cartItems}">
        <tr id="${cartItem.product.id}">
            <td><c:out value="${cartItem.product.id}"/></td>
            <td>
                <a href = "<c:url value = "/products/${cartItem.product.id}" />"><c:out value="${cartItem.product.code}"/></a>
            </td>
            <td><c:out value="${cartItem.product.description}"/></td>
            <td><fmt:formatNumber value = "${cartItem.product.price}"/></td>
            <td><c:out value="${cartItem.product.currency}"/></td>
            <td>
                <input type="text" id="input-field" name="quantity" value="<fmt:formatNumber value = "${cartItem.quantity}"/>" onchange="updateCartItem(${cartItem.product.id},this.value)">
                <c:if test="${not empty message && (id == cartItem.product.id)}">
                    <label style="color: ${message == "Product_added" ? "green" : "red"};width: 200%;" id="test"> <fmt:message key="message.${message}" /><label>
                </c:if>
            </td>
            <td>
                <button type="button" onclick="deleteCartItem(${cartItem.product.id});">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>

<script>
    function deleteCartItem(id) {
        var request = new XMLHttpRequest();

        request.open('DELETE', '/phoneshop-servlet-api/cart');
        request.setRequestHeader('productId', id);
        request.send();

        document.getElementById(id).remove();
    }
    function updateCartItem(id, quantity) {
        var request = new XMLHttpRequest();

        request.open('POST', '/phoneshop-servlet-api/cart');
        request.setRequestHeader('productId', id);
        request.setRequestHeader('quantity', quantity);
        request.send();
    }
</script>
</html>
