package com.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.Constants;

import javax.validation.ValidationException;
// то что здесь должно авт-ки сохранятся в куки чтобы потом восстановить?
// это корзина над корзиной пользователя потомучто мы будем ее сохранять в сессию?
// ShoppingCart - состояние корзины что набросает пользователь?
// покупки пользователя где лежат? он фактически добавляет в ShoppingCart или ShoppingCartItem? в чем разница между ники
public class ShoppingCart implements Serializable {//
    private static final long serialVersionUID = 1535770438453611801L;
    private Map<Integer, ShoppingCartItem> products = new HashMap<>();//тут храним ключ int и значение обьект ShoppingCartItem
    private int totalCount = 0;// зачем эта переменная?

    public void addProduct(int idProduct, int count) {// почему сюда не принять ShoppingCartItem?
        validateShoppingCartSize(idProduct);//проверка на 20 максимум
        ShoppingCartItem shoppingCartItem = products.get(idProduct);//из мапы по id получаем обьект ShoppingCartItem
        if (shoppingCartItem == null) {
            validateProductCount(count);
            shoppingCartItem = new ShoppingCartItem(idProduct, count);
            products.put(idProduct, shoppingCartItem);//ложим в мапу
        } else {//если shoppingCartItem не пуста
            validateProductCount(count + shoppingCartItem.getCount());//проверка кол-ва макс 10(кол-во котор пришло+то что лежит уже)
            shoppingCartItem.setCount(shoppingCartItem.getCount() + count);//если проверка на 10 прошла устанавливаем новое значение колва в shoppingCartItem
        }
        refreshStatistics();
    }

    public void removeProduct(Integer idProduct, int count) {//получаем id prod и колво
        ShoppingCartItem shoppingCartItem = products.get(idProduct);//из мапы по id получаем обьект ShoppingCartItem
        if (shoppingCartItem != null) {// если в shoppingCartItem есть чтото(!null)
            if (shoppingCartItem.getCount() > count) {//если колво товара больше того что удаляем
                shoppingCartItem.setCount(shoppingCartItem.getCount() - count);// получается суть чтобы не удалить все сразу
                // (возможность удалять по одному?)
                // как бы добавил лишнего и удалил потом? в int count может придти сразу 3?
            } else {
                products.remove(idProduct);
            }
            refreshStatistics();
        }
    }

    public Collection<ShoppingCartItem> getItems() {
        return products.values();
    }
    //что делает эта коллекция?зачем она нужна? почему только метод?где сама коллекция и имя?
    //как сюда попадет - сохранится чтото?
    //вижу что он работает с ней в сервлете

    public int getTotalCount() {
        return totalCount;
    }
// почему он не пользуется stream api, lambda Predicate Suplier Consumer?
    private void validateProductCount(int count) {//10 макс
        if(count > Constants.MAX_PRODUCT_COUNT_PER_SHOPPING_CART){
            throw new ValidationException("Limit for product count reached: count="+count);
            // в исходном коде он создавал ValidationException
            // я взял из java библиотеки, норм?
        }
    }
    // почему он не пользуется stream api, lambda Predicate Suplier Consumer?
    private void validateShoppingCartSize(int idProduct){//
        if(products.size() > Constants.MAX_PRODUCTS_PER_SHOPPING_CART ||//если мапа больше 20(это логично) или
                //размер мапы равен 20 и в ней нет ключей-id продуктов (не логично причем тут ключи)?
                (products.size() == Constants.MAX_PRODUCTS_PER_SHOPPING_CART && !products.containsKey(idProduct))) {
            throw new ValidationException("Limit for ShoppingCart size reached: size="+products.size());
        }
    }

    private void refreshStatistics() {//зачем нужно обновлять статистику?
        totalCount = 0;
        for (ShoppingCartItem shoppingCartItem : getItems()) {
         // мы for each можем вызвать на методе?)
            totalCount += shoppingCartItem.getCount();
        }
    }

    @Override
    public String toString() {
        return String.format("ShoppingCart [products=%s, totalCount=%s]", products, totalCount);
    }
}