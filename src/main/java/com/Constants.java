package com;

public final class Constants {// что по сути делают константы?
    public static final int MAX_PRODUCT_COUNT_PER_SHOPPING_CART = 10;//  константы публичные тк их и так не изменить?
    public static final int MAX_PRODUCTS_PER_SHOPPING_CART = 20;
    public static final String CURRENT_SHOPPING_CART = "CURRENT_SHOPING_CART" ;

    public enum Cookie {
        SHOPPING_CART("iSCC", 60 * 60 * 24 * 365);// что такое ISCC просто имя? обьекту SHOPPING_CART присваем имя?
        // что за SHOPPING_CART в энуме?
        private final String name;
        private final int ttl;

        Cookie(String name, int ttl) {
            this.name = name;
            this.ttl = ttl;
        }

        public String getName() {
            return name;
        }

        public int getTtl() {
            return ttl;
        }
    }
}
