package de.sliceanddice.maryandpaul.lib.internal.response;

import de.sliceanddice.maryandpaul.lib.models.Basket;

public class BasketAddResponse implements CollinsResponse<Basket> {

    private Basket basket;

    @Override
    public Basket get() {
        return basket;
    }

}
