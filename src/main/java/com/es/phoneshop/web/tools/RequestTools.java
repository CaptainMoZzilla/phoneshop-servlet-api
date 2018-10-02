package com.es.phoneshop.web.tools;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.CartService;
import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class RequestTools {
    private static final String MESSAGE_HEADER = "message";
    private static final String ID_HEADER = "id";
    private static CartService cartService = CartService.getInstance();
    private static ProductDao productDao = ArrayListProductDao.getInstance();

    public static Integer parseIntegerUsingLocale(HttpServletRequest request, String number) throws ParseException {
        NumberFormat decimalFormat = DecimalFormat.getInstance(request.getLocale());
        Integer value = decimalFormat.parse(number).intValue();

        if (decimalFormat.format(value).compareTo(number) != 0) {
            throw new ParseException("Incorrect input", value);
        }
        return value;
    }
    
    public static void addOrUpdateCartItem(HttpServletRequest request, Long id, String quantityString,  boolean add) {
        Integer quantity;
        try {
            quantity = parseIntegerUsingLocale(request,quantityString);
            Cart cart = cartService.getCart(request);
            if (add) {
                cartService.add(cart, productDao.getProduct(id), quantity);
                addSessionAttributes(request, "Product_added", id);
            } else {
                cartService.update(cartService.getCart(request), productDao.getProduct(id), quantity);
                addSessionAttributes(request, "CartItem_edited", id);
            }
        } catch (ParseException e) {
            addSessionAttributes(request, "Incorrect_input", id);
        } catch (IllegalArgumentException e) {
            addSessionAttributes(request, e.getMessage(), id);
        }
    }

    public static void addSessionAttributes(HttpServletRequest request, String message, Long id) {
        request.getSession().setAttribute(MESSAGE_HEADER, message);
        request.getSession().setAttribute(ID_HEADER, id.toString());
    }

    public static void setRequestAttributes(HttpServletRequest request) {
        request.setAttribute(MESSAGE_HEADER, request.getSession().getAttribute(MESSAGE_HEADER));
        request.setAttribute(ID_HEADER, request.getSession().getAttribute(ID_HEADER));

        request.getSession().removeAttribute(MESSAGE_HEADER);
        request.getSession().removeAttribute(ID_HEADER);
    }

    public static boolean sessionHasAttributes(HttpServletRequest request) {
        return request.getSession().getAttribute(MESSAGE_HEADER) != null;
    }
}
