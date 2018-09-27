package com.es.phoneshop.web.servlets;

import com.es.phoneshop.comparePage.CompareQue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.es.phoneshop.web.tools.RequestTools.addOrUpdateCartItem;
import static com.es.phoneshop.web.tools.RequestTools.sessionHasAttributes;
import static com.es.phoneshop.web.tools.RequestTools.setRequestAttributes;

public class ComparePageServlet extends HttpServlet {
    private CompareQue compareQue = CompareQue.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (sessionHasAttributes(request)) {
            setRequestAttributes(request);
        }

        request.setAttribute("compareQue", compareQue);
        request.getRequestDispatcher("/WEB-INF/pages/comparePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("productId") != null) {
            Long id = Long.valueOf(request.getParameter("productId"));

            addOrUpdateCartItem(request, id, request.getParameter("quantity"), true);
        }
        response.sendRedirect(request.getRequestURI());
    }
}
