<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="<c:url value="/resources/headerThem/header.css" />" rel="stylesheet">
    </head>
    <header>
        <div class="container">
            <nav>
                <ul>
                    <li><a href = "<c:url value = "/products"/>">To product list </a></li>
                    <li><a href = "<c:url value = "/cart"/>">To cart</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <br>
</html>
