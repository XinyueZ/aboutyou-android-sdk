package de.sliceanddice.maryandpaul.lib.requests;

import java.util.List;

public class ProductRequest extends BaseRequest {

    private Products products = new Products();

    public void setIds(List<Long> ids) {
        products.ids = ids;
    }

    private static class Products {

        private List<Long> ids;

    }

}