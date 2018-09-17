package com.es.phoneshop.web.servlets;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.ProductDao;
import com.es.phoneshop.web.tools.RequestTools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetailsPageServlet extends HttpServlet {
    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestTools.setRequestAttributes(request, getId(request));

        response.sendRedirect(request.getRequestURI());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("product", productDao.getProduct(getId(request)));
        } catch (IllegalArgumentException e) {
            response.sendError(404);
            return;
        }

        if (request.getSession().getAttribute("error") != null) {
            RequestTools.setRequestErrorsAttributes(request);

        } else if (request.getSession().getAttribute("success") != null) {
            RequestTools.setRequestCorrectAttributes(request);
        }

        request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
    }

    private Long getId(HttpServletRequest request) {
        return Long.valueOf(request.getPathInfo().substring(1));
    }
}
