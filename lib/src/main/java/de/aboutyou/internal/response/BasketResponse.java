package de.aboutyou.internal.response;

import de.aboutyou.models.Basket;

public class BasketResponse extends CollinsResponse<Basket> {

    private Basket basket;

    @Override
    public Basket get() {
        return basket;
    }

}
