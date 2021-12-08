package com.utill;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WebUtils {
    private WebUtils() {// зачем пустой конструктор?
    }

    public static void setCookie(String name, String value, int age, HttpServletResponse response) {
        //кто и где отправит сюда значения эти? мы в ручную?
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);// а если не ставить что будет? атаки xss через js?
        cookie.setMaxAge(age);// по умолчанию чтото стоять будет?
        cookie.setPath("/"); // как это работает? vk.com/ все что после слэша любые урл будут получать куки?
        response.addCookie(cookie);
    }


    public static Cookie findCookie(HttpServletRequest request, String cookieName) {
        //cookieName откуда оно приходит?мы по equal его сравнивать будем(откуда знаем имя куки? я думал все данные тянем из requesta)
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    if (cookie.getValue() != null && !"".equals(cookie.getValue())) {// "" это мы сравниваем не пустое ли значение со значением куки?
                        // как может оказаться "пустота"в значении куки если имя пройдет проверку?
                        return cookie;
                    }
                }

            }
        }
        return null;
    }
}
