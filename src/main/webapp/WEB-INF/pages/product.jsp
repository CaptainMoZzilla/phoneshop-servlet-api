<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="product" type="com.es.phoneshop.model.Product" scope="request"/>

<html>
    <head>
        <jsp:include page="header.jsp"/>
        <link href="<c:url value="/resources/CartThem/CartThem.css" />" rel="stylesheet">
    </head>

    <body>
        <form method="post">
            <div style="text-align: center;">
                <h2>Info</h2>
            </div>
            <table>
                <tr>
                    <td>Id</td>
                    <td><c:out value="${product.id}"/></td>
                </tr>
                <tr>
                    <td>Code</td>
                    <td><c:out value="${product.code}"/></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><c:out value="${product.description}"/></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><c:out value="${product.price}"/></td>
                </tr>
                <tr>
                    <td>Currency</td>
                    <td><c:out value="${product.currency}"/></td>
                </tr>
                <tr>
                    <td>Stock</td>
                    <td><c:out value="${product.stock}"/></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" value="1" name="quantity" style="width: 40%"></td>
                </tr>
                <tr>
                    <td style="border: 0px solid white; width: 51%">
                        <input type="submit" value="Add to cart" style="width: 200%;" />
                    </td>
                </tr>
                <tr>
                    <td style="border: 0px solid white">
                        <c:if test="${not empty error}">
                            <label style="color: red;width: 200%;"><c:out value="${error}"/><label>
                        </c:if>

                        <c:if test="${not empty success}">
                            <label style="color: green; width: 200%;"><c:out value="${success}"/><label>
                        </c:if>
                    </td>
            </table>
        </form>
    </body>

    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>

</html>