package com.es.phoneshop.web.tools;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.CartService;
import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.text.ParseException;

public class RequestTools {
    private static final String MESSAGE = "message";
    private static final String ID = "id";
    private static CartService cartService = CartService.getInstance();
    private static ProductDao productDao = ArrayListProductDao.getInstance();

    public static Integer parseIntegerUsingLocale(HttpServletRequest request, String number) throws ParseException {
        //ToDO fix parsing
        NumberFormat numberFormat = NumberFormat.getIntegerInstance(request.getLocale());
        return numberFormat.parse(number).intValue();
    }
    
    public static void addOrUpdateCartItem(HttpServletRequest request, Long id, String quantityString,  boolean add) {
        Integer quantity;
        try {
            quantity = parseIntegerUsingLocale(request,quantityString);
            System.out.println(id+" "+ quantity);
            Cart cart = cartService.getCart(request);

            if (add) {
                cartService.add(cart, productDao.getProduct(id), quantity);
            } else {
                cartService.update(cartService.getCart(request), productDao.getProduct(id), quantity);
            }

            addSessionAttributes(request, "Product_added", id);
        } catch (ParseException e) {
            System.out.println("Incorrect_input");
            addSessionAttributes(request, "Incorrect_input", id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            addSessionAttributes(request, e.getMessage(), id);
        }
    }

    public static void addSessionAttributes(HttpServletRequest request, String message, Long id) {
        request.getSession().setAttribute(MESSAGE, message);
        request.getSession().setAttribute(ID, id.toString());
    }

    public static void setRequestAttributes(HttpServletRequest request) {
        request.setAttribute("message", request.getSession().getAttribute(MESSAGE));
        request.setAttribute("id", request.getSession().getAttribute(ID));

        request.getSession().removeAttribute(MESSAGE);
        request.getSession().removeAttribute(ID);
    }

    public static boolean sessionHasAttributes(HttpServletRequest request) {
        return request.getSession().getAttribute(MESSAGE) != null;
    }
}
