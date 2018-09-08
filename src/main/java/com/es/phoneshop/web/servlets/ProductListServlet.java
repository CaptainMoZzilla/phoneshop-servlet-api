package com.es.phoneshop.web.servlets;

import com.es.phoneshop.model.*;
import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartService;
import com.es.phoneshop.web.tools.RequestTools;
import org.omg.CORBA.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListServlet extends HttpServlet {
    private ProductDao productDao = ArrayListProductDao.getInstance();
    private CartService cartService = CartService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestTools.setRequestAttributes(request, Long.valueOf(request.getParameter("productId")));

        response.sendRedirect(request.getRequestURI());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("error") != null) {
            RequestTools.setRequestErrorsAttributes(request);

        } else if (request.getSession().getAttribute("success") != null) {
            RequestTools.setRequestCorrectAttributes(request);
        }

        request.setAttribute("products", productDao.findProducts());
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }
}
