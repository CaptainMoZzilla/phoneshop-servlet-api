package com.es.phoneshop.web.servlets;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.es.phoneshop.web.tools.RequestTools.*;

public class CartPageServlet extends HttpServlet {
    private CartService cartService = CartService.getInstance();
    private static final String PRODUCT_ID_HEADER = "productId";
    private static final String QUANTITY_HEADER = "quantity";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long productId = Long.valueOf(request.getHeader(PRODUCT_ID_HEADER));

        addOrUpdateCartItem(request, productId, request.getHeader(QUANTITY_HEADER),  false);

        response.sendRedirect(request.getRequestURI());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Long productId = Long.parseLong(request.getHeader(PRODUCT_ID_HEADER));
        Cart cart = cartService.getCart(request);

        cartService.delete(cart, productId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (sessionHasAttributes(request)) {
            setRequestAttributes(request);
            System.out.println(request.getAttribute("id") + " "+request.getAttribute("message"));
        }

        request.setAttribute("cart", cartService.getCart(request));
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }
}
