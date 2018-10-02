package com.es.phoneshop.web.servlets;

import com.es.phoneshop.cart.CartItem;
import com.es.phoneshop.cart.CartService;
import com.es.phoneshop.order.Order;
import com.es.phoneshop.order.OrderService;
import com.es.phoneshop.order.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckoutPageServlet extends HttpServlet {
    private CartService cartService = CartService.getInstance();
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("totalCost", calculateTotalCost(request));
        request.setAttribute("cart", cartService.getCart(request));
        request.getRequestDispatcher("/WEB-INF/pages/checkoutPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = orderService.placeOrder(cartService.getCart(request));

        order.setName(request.getParameter("Name"));
        order.setAddress(request.getParameter("Address"));
        order.setPhone(request.getParameter("Phone"));
        order.setTotalCost(calculateTotalCost(request));

        request.getSession().removeAttribute("cart");

        response.sendRedirect(request.getRequestURI() + "/" + order.getId());
    }

    private Integer calculateTotalCost(HttpServletRequest request) {
        Integer totalCost = 0;
        for(CartItem cartItem : cartService.getCart(request).getCartItems()) {
            totalCost += cartItem.getQuantity() * cartItem.getProduct().getPrice().intValue();
        }

        return totalCost;
    }
}
