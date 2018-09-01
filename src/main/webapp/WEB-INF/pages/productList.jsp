<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="products" type="java.util.ArrayList<com.es.phoneshop.model.Product>" scope="request"/>

<html>
    <head>
      <title>Product List</title>
      <link href="<c:url value="/resources/tableThem/main.css" />" rel="stylesheet">
      <jsp:include page="header.jsp"/>
    </head>

    <body>
        <center>
          <h2>Product List Page</h2>
        </center>
        <table>
            <thead>
                <tr>
                    <td>Id</td>
                    <td>Code</td>
                    <td>Description</td>
                    <td>Price</td>
                    <td>Currency</td>
                    <td>Stock</td>
                </tr>
              </thead>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><c:out value="${product.id}"/></td>
                    <td>
                      <a href = "<c:url value = "/products/${product.id}" />"><c:out value="${product.code}"/></a>
                    </td>
                    <td><c:out value="${product.description}"/></td>
                    <td><c:out value="${product.price}"/></td>
                    <td><c:out value="${product.currency}"/></td>
                    <td><c:out value="${product.stock}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>

    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>

</html>

