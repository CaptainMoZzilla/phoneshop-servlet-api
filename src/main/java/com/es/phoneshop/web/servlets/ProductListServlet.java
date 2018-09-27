package com.es.phoneshop.web.servlets;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.Product;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.es.phoneshop.web.tools.RequestTools.*;

public class ProductListServlet extends HttpServlet {
    private ProductDao productDao = ArrayListProductDao.getInstance();
    private static final String FOUND_OBJECTS_NAME = "foundObjects";
    private static final String USER_INPUT = "userInput";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("productId") != null) {
            Long id = Long.valueOf(request.getParameter("productId"));

            addOrUpdateCartItem(request, id, request.getParameter("quantity"), true);
        } else {
            List<Product> foundObjects;
            String line = request.getParameter("search-text");

            foundObjects = productDao.findProducts()
                    .stream()
                    .filter((p) -> (p.getDescription().toLowerCase().contains(line.toLowerCase()) ||
                            p.getCode().toLowerCase().contains(line.toLowerCase())))
                    .collect(Collectors.toList());

            request.getSession().setAttribute(FOUND_OBJECTS_NAME, foundObjects);
            request.getSession().setAttribute(USER_INPUT, line);
        }

        response.sendRedirect(request.getRequestURI());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (sessionHasAttributes(request)) {
            setRequestAttributes(request);
        }

        if (request.getSession().getAttribute(FOUND_OBJECTS_NAME) != null) {
            request.setAttribute(USER_INPUT, request.getSession().getAttribute(USER_INPUT));
            request.setAttribute("products", request.getSession().getAttribute(FOUND_OBJECTS_NAME));

            request.getSession().removeAttribute(FOUND_OBJECTS_NAME);
            request.getSession().removeAttribute(USER_INPUT);
        } else {
            request.setAttribute("products", productDao.findProducts());
        }

        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }
}
