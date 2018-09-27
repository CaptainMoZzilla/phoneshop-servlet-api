<%@ page contentType="text/html; charset = UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="cart" type="com.es.phoneshop.cart.Cart" scope="request"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <link href="<c:url value="/resources/style/CartThem/CartThem.css" />" rel="stylesheet">
</head>

<title>Cart</title>

<header>
    <jsp:include page="/WEB-INF/pages/header.jsp"/>
</header>

<body style="background: #C5CAE9;">
    <div style="text-align: center;">
        <h2>Welcome to your cart</h2>
    </div>

    <table style="background: #E8EAF6">
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
        <c:forEach var="product" items="${cart.cartItems}">
            <tr id="${product.product.id}">
                <td><c:out value="${product.product.id}"/></td>
                <td>
                    <a href = "<c:url value = "/products/${product.product.id}" />"><c:out value="${product.product.code}"/></a>
                </td>
                <td><c:out value="${product.product.description}"/></td>
                <td><fmt:formatNumber value = "${product.product.price}"/></td>
                <td><c:out value="${product.product.currency}"/></td>
                <td>
                    <form method="post" name="form${product.product.id}">
                        <input type="text" id="quantity" name="quantity" value="<fmt:formatNumber value = "${product.quantity}"/>" onchange="javascript:document.forms['form${product.product.id}'].submit()">
                        <input type="hidden" name="productId" value="${product.product.id}">
                    </form>

                    <c:if test="${not empty message && (id == product.product.id)}">
                        <label for="quantity" style="color: ${message == "CartItem_edited" ? "green" : "red"};width: 200%;" id="test"> <fmt:message key="message.${message}" /><label>
                    </c:if>
                </td>
                <td>
                    <button type="button" onclick="deleteCartItem(${product.product.id});">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div style="text-align: center;">
        <a href="/phoneshop-servlet-api/checkout" target="_parent"><button style="outline:none; padding:7px 20px; color:#FFF; cursor:pointer; background:#d2232a; border:none; margin:1em auto 0;">To checkout page!</button></a>
    </div>

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
</script>
</html>
