<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="cart" type="com.es.phoneshop.model.Cart" scope="request"/>

<html>
    <head>
        <link href="<c:url value="/resources/tableThem/main.css" />" rel="stylesheet">
    </head>

    <title>Cart</title>

    <header>
        <jsp:include page="/WEB-INF/pages/header.jsp"/>
    </header>

    <body>
        <center>
            <h2>Welcome to your cart</h2>
        </center>
        <table>
            <thead>
            <tr>
                <td>Id</td>
                <td>Code</td>
                <td>Description</td>
                <td>Price</td>
                <td>Currency</td>
            </tr>
            </thead>
            <c:forEach var="cartItem" items="${cart.cartItems}">
                <tr>
                    <td><c:out value="${cartItem.id}"/></td>
                    <td>
                        <a href = "<c:url value = "/products/${cartItem.id}" />"><c:out value="${cartItem.code}"/></a>
                    </td>
                    <td><c:out value="${cartItem.description}"/></td>
                    <td><c:out value="${cartItem.price}"/></td>
                    <td><c:out value="${cartItem.currency}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>

    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</html>

