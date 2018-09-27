package com.es.phoneshop.web.servlets;

import com.es.phoneshop.comparePage.CompareQue;
import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.es.phoneshop.web.tools.RequestTools.*;

public class ProductDetailsPageServlet extends HttpServlet {
    private ProductDao productDao = ArrayListProductDao.getInstance();
    private final static String QUANTITY_PARAMETER = "quantity";
    private CompareQue compareQue = CompareQue.getInstance();
    private String compare = "compare";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.valueOf(getId(request));

        if (request.getParameter(QUANTITY_PARAMETER) != null) {
            addOrUpdateCartItem(request, id, request.getParameter(QUANTITY_PARAMETER), true);

        } else {
            boolean checker = compareQue.addToQue(productDao.getProduct(id));
            request.getSession().setAttribute("compare", checker ? "Added" : "There's such product in compare page");
        }

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

        if (sessionHasAttributes(request)) {
            setRequestAttributes(request);
        } else if (request.getSession().getAttribute(compare) != null) {
            request.setAttribute(compare, request.getSession().getAttribute(compare));
            request.getSession().removeAttribute(compare);
        }

        request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
    }

    private Long getId(HttpServletRequest request) {
        return Long.valueOf(request.getPathInfo().substring(1));
    }
}
