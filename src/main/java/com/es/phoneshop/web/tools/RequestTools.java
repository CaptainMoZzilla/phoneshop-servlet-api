package com.es.phoneshop.web.tools;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.ProductDao;
import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartService;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.text.ParseException;

public class RequestTools {
    private static CartService cartService = CartService.getInstance();
    private static ProductDao productDao = ArrayListProductDao.getInstance();

    public static void setRequestAttributes(HttpServletRequest request, Long id) {
        Integer quantity;
        NumberFormat numberFormat = NumberFormat.getIntegerInstance(request.getLocale());

        try {
            quantity = numberFormat.parse(request.getParameter("quantity")).intValue();
            try {
                Cart cart = cartService.getCart(request);
                cartService.addToCart(cart, productDao.getProduct(id), quantity);

                request.getSession().setAttribute("success", "Successfully");
                request.getSession().setAttribute("id", id.toString());
            } catch (IllegalArgumentException e) {
                setSessionErrorsAttributes(request,  e.getMessage(), id);
            }

        } catch (ParseException e) {
            setSessionErrorsAttributes(request,  "NaN", id);
        }
    }
    private static void setSessionErrorsAttributes(HttpServletRequest request, String message, Long id) {
        request.getSession().setAttribute("error", message);
        request.getSession().setAttribute("id", id.toString());
    }

    public static void setRequestErrorsAttributes(HttpServletRequest request) {
        request.setAttribute("error", request.getSession().getAttribute("error"));
        request.setAttribute("id", request.getSession().getAttribute("id").toString());

        request.getSession().removeAttribute("error");
        request.getSession().removeAttribute("id");
    }

    public static void setRequestCorrectAttributes(HttpServletRequest request) {
        request.setAttribute("success", "Successfully");
        request.setAttribute("id", request.getSession().getAttribute("id"));

        request.getSession().removeAttribute("success");
        request.getSession().removeAttribute("id");
    }
}
