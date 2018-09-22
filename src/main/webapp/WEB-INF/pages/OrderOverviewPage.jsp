<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Order info</title>
    <link href="<c:url value="/resources/style/CartThem/CartThem.css" />" rel="stylesheet">
</head>

<body style="background: #C5CAE9;">
    <div style="text-align: center;">
        <h2>Order is accepted!</h2>
    </div>
    <table style="background: #E8EAF6">
        <tr>
            <td>Name</td>
            <td><c:out value="${order.getName()}"/></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><c:out value="${order.getAddress()}"/></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><c:out value="${order.getPhone()}"/></td>
        </tr>
        <tr>
            <td>Total cost</td>
            <td><fmt:formatNumber value = "${order.getTotalCost()}"/></td>
        </tr>
    </table>
</body>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</html>