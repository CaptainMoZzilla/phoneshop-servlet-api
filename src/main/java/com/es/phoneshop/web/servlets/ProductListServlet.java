package com.es.phoneshop.web.servlets;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.es.phoneshop.web.tools.RequestTools.*;

public class ProductListServlet extends HttpServlet {
    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.valueOf(request.getParameter("productId"));

        addOrUpdateCartItem(request, id, request.getParameter("quantity"), true);

        response.sendRedirect(request.getRequestURI());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (sessionHasAttributes(request)) {
            setRequestAttributes(request);
        }

        request.setAttribute("products", productDao.findProducts());
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }
}
