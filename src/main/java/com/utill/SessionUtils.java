package com.utill;
import com.Constants;
import com.entity.ShoppingCart;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SessionUtils {
    public static ShoppingCart getCurrentShoppingCart(HttpServletRequest req) {//получить текущую карту из реквеста
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART);
        //почему не приходит string name или shopingcart а в getAttribute мы вставляем константу?как это работает с констой?
        if (shoppingCart == null) {//если пусто
            shoppingCart = new ShoppingCart();//создаем
            setCurrentShoppingCart(req, shoppingCart);
            //можно ли было не создавать отдельный метод setCurrentShoppingCart?
            // вызвать просто req.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart)?
        }
        return shoppingCart;
    }

    public static boolean isCurrentShoppingCartCreated(HttpServletRequest req) {
        return req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART) != null;//какая связь класса метода и констант?
    }

    public static void setCurrentShoppingCart(HttpServletRequest req, ShoppingCart shoppingCart) {
        req.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);//почему сетаем на реквесте?
    }

    public static void clearCurrentShoppingCart(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute(Constants.CURRENT_SHOPPING_CART);
        //если делаем remove зачем еще снизу сет на 0 и null?
        WebUtils.setCookie(Constants.Cookie.SHOPPING_CART.getName(), null, 0, resp);
    }

    public static Cookie findShoppingCartCookie(HttpServletRequest req) {
        return WebUtils.findCookie(req, Constants.Cookie.SHOPPING_CART.getName());
    }

    public static void updateCurrentShoppingCartCookie(String cookieValue, HttpServletResponse resp) {
        //что это за cookieValue приходит?
        WebUtils.setCookie(Constants.Cookie.SHOPPING_CART.getName(), cookieValue,//cookieValue ?
                Constants.Cookie.SHOPPING_CART.getTtl(), resp);
    }

    private SessionUtils() {
    }//зачем пустой констр?
}
