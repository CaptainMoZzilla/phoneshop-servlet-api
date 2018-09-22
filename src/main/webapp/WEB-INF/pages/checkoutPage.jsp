<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="cart" type="com.es.phoneshop.cart.Cart" scope="request"/>

<html>
<header>
    <jsp:include page="/WEB-INF/pages/header.jsp"/>
    <link href="<c:url value="/resources/style/CartThem/CartThem.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/style/CheckoutTheme/CheckoutTheme.css" />" rel="stylesheet">
</header>

<head>
    <title>Checkout page</title>
    <div style="text-align: center;">
        <h2 style="color: #000000;">Checkout</h2>
    </div>
</head>

<body style="background: #C5CAE9;">
    <table style="background: #E8EAF6" id="table">
        <thead>
        <tr>
            <td>Id</td>
            <td>Code</td>
            <td>Description</td>
            <td>Price</td>
            <td>Currency</td>
            <td>Quantity</td>
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
                <td><fmt:formatNumber value = "${cartItem.quantity}"/></td>
            </tr>
        </c:forEach>
    </table>
    <span><h3 style="margin-left: 55%;height: 5%">Total cost: <fmt:formatNumber value = "${totalCost}"/></h3></span>

    <div class=" register-top" style="text-align: center; background: #E8EAF6">
        <form method="post">
            <h3 style="text-align: center">Enter some info</h3>
            <div>
                <span>First Name</span>
                <input type="text" required="true" name="Name">
            </div>
            <div>
                <span>Address</span>
                <input type="text" required="true" name="Address">
            </div>
            <div>
                <span>Phone</span>
                <input type="text" required="true" name="Phone">
            </div>
            <input value="Make order" type="submit">
        </form>
    </div>

</body>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</html>

