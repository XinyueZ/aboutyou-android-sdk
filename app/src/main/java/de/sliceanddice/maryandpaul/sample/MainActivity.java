package de.sliceanddice.maryandpaul.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.sliceanddice.maryandpaul.lib.ShopApiClient;
import de.sliceanddice.maryandpaul.lib.enums.Direction;
import de.sliceanddice.maryandpaul.lib.enums.Endpoint;
import de.sliceanddice.maryandpaul.lib.enums.FacetGroup;
import de.sliceanddice.maryandpaul.lib.enums.ProductFields;
import de.sliceanddice.maryandpaul.lib.enums.ProductFilter;
import de.sliceanddice.maryandpaul.lib.enums.Sortby;
import de.sliceanddice.maryandpaul.lib.enums.Type;
import de.sliceanddice.maryandpaul.lib.models.Autocomplete;
import de.sliceanddice.maryandpaul.lib.models.Basket;
import de.sliceanddice.maryandpaul.lib.models.Category;
import de.sliceanddice.maryandpaul.lib.models.CategoryTree;
import de.sliceanddice.maryandpaul.lib.models.Facet;
import de.sliceanddice.maryandpaul.lib.models.OrderLine;
import de.sliceanddice.maryandpaul.lib.models.Product;
import de.sliceanddice.maryandpaul.lib.models.ProductSearch;
import de.sliceanddice.maryandpaul.lib.request.AutocompleteRequest;
import de.sliceanddice.maryandpaul.lib.request.BasketAddRequest;
import de.sliceanddice.maryandpaul.lib.request.CategoriesRequest;
import de.sliceanddice.maryandpaul.lib.request.FacetsRequest;
import de.sliceanddice.maryandpaul.lib.request.ProductsRequest;
import de.sliceanddice.maryandpaul.lib.request.ProductSearchRequest;

public class MainActivity extends Activity {

    private static final String APP_ID = "110";
    private static final String APP_PASSWORD = "ed8272cc4d993378f595d112915920bb";

    private ShopApiClient mShopApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mShopApiClient = new ShopApiClient(APP_ID, APP_PASSWORD, Endpoint.STAGE, new Logger());
    }

    @OnClick(R.id.auth)
    public void auth() {
        mShopApiClient.authenticate(this, Arrays.asList("firstname", "lastname", "id", "email"), new ShopApiClient.AuthenticationCallback(){
            @Override
            public void onSuccess(String accessToken) {
                Toast.makeText(MainActivity.this, accessToken, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.category_tree)
    public void categoryTree() {
        mShopApiClient.requestCategoryTree(new ApiCallback<CategoryTree>());
    }

    @OnClick(R.id.categories)
    public void categories() {
        CategoriesRequest categoriesRequest = new CategoriesRequest.Builder()
                .filterByCategoryIds(Arrays.asList(19534l))
                .build();

        mShopApiClient.requestCategories(categoriesRequest, new ApiCallback<List<Category>>());
    }

    @OnClick(R.id.facets)
    public void facets() {
        FacetsRequest facetRequest = new FacetsRequest.Builder()
                .filterByFacetGroup(Arrays.asList(FacetGroup.SIZE))
                .build();

        mShopApiClient.requestFacets(facetRequest, new ApiCallback<List<Facet>>());
    }

    @OnClick(R.id.facettypes)
    public void facettypes() {
        mShopApiClient.requestFacetTypes(new ApiCallback<List<FacetGroup>>());
    }

    @OnClick(R.id.autocomplete)
    public void autocomplete() {
        AutocompleteRequest autocompleteRequest = new AutocompleteRequest.Builder("Sho")
                .filterByTypes( Arrays.asList(Type.PRODUCTS))
                .limit(10)
                .build();


        mShopApiClient.requestAutocompletion(autocompleteRequest, new ApiCallback<Autocomplete>());
    }

    @OnClick(R.id.productsearch)
    public void productsearch() {
        Map<FacetGroup, List<Long>> facetFilter = new HashMap<>();
        facetFilter.put(FacetGroup.CUPSIZE, Arrays.asList(93l, 94l, 95l, 96l));

        ProductSearchRequest request = new ProductSearchRequest.Builder("session4711")
                .filterByMinPrice(500)
                .filterByMaxPrice(5000)
                .filterByStatus(ProductFilter.NONSALEONLY)
                .filterByFacets(facetFilter)
                .sortBy(Sortby.RELEVANCE)
                .sortDirection(Direction.DESC)
                .limit(10)
                .build();

        mShopApiClient.requestProductSearch(request, new ApiCallback<ProductSearch>());
    }

    @OnClick(R.id.products)
    public void products() {
        ProductsRequest productRequest = new ProductsRequest.Builder()
                .filterByProductIds(Arrays.asList(329777l, 325136l))
                .listFields(Arrays.asList(ProductFields.VARIANTS))
                .build();

        mShopApiClient.requestProducts(productRequest, new ApiCallback<List<Product>>());
    }

    @OnClick(R.id.basketadd)
    public void basketadd() {
        BasketAddRequest basketAddRequest = new BasketAddRequest.Builder("session4711")
                .setOrderLines(Arrays.asList(new OrderLine(UUID.randomUUID().toString(), 5615651l)))
                .build();

            mShopApiClient.requestAddBasket(basketAddRequest, new ApiCallback<Basket>());
    }

    private class ApiCallback<T> implements ShopApiClient.Callback<T> {

        @Override
        public void onCompleted(T response) {
            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private class Logger implements ShopApiClient.Logger {

    }

}
