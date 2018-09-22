package com.es.phoneshop.web.servlets;

import com.es.phoneshop.order.Order;
import com.es.phoneshop.order.OrderService;
import com.es.phoneshop.order.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderOverviewPageServlet extends HttpServlet {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = getId(request);
        Order order = orderService.getOrder(id);

        if (order == null) {
            response.sendError(404);
            return;
        }

        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/pages/OrderOverviewPage.jsp").forward(request, response);
    }

    private String getId(HttpServletRequest request) {
        return request.getPathInfo().substring(1);
    }
}
