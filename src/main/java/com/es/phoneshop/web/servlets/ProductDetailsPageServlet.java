package com.es.phoneshop.web.servlets;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetailsPageServlet extends HttpServlet {
    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("product",productDao.getProduct(Long.valueOf(request.getPathInfo().substring(1))));
        } catch (IllegalArgumentException e) {
            response.sendError(404);
            return;
        }

        request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
    }
}
