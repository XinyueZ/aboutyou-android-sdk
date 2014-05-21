package de.aboutyou.sample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.aboutyou.ShopApiClient;
import de.aboutyou.enums.AuthScope;
import de.aboutyou.enums.AuthenticationRequestMode;
import de.aboutyou.enums.AutocompleteType;
import de.aboutyou.enums.Direction;
import de.aboutyou.enums.Endpoint;
import de.aboutyou.enums.FacetGroup;
import de.aboutyou.enums.ProductFields;
import de.aboutyou.enums.ProductFilter;
import de.aboutyou.enums.Sortby;
import de.aboutyou.exceptions.CollinsException;
import de.aboutyou.models.AddOrderLine;
import de.aboutyou.models.OrderLine;
import de.aboutyou.request.AutocompleteRequest;
import de.aboutyou.request.BasketModifyRequest;
import de.aboutyou.request.CategoriesRequest;
import de.aboutyou.request.FacetsRequest;
import de.aboutyou.request.ProductsRequest;
import de.aboutyou.request.ProductSearchRequest;

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
        mShopApiClient.requestAuthentication(this, Arrays.asList(AuthScope.FIRSTNAME, AuthScope.LASTNAME, AuthScope.ID, AuthScope.EMAIL), AuthenticationRequestMode.DEFAULT, "http://mp.sdk/oauth",
                new ShopApiClient.AuthenticationCallback() {
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
        (new RequestTask(new Runnable() {
            @Override
            public void run() {
                mShopApiClient.requestCategoryTree();
            }
        })).execute();
    }

    @OnClick(R.id.categories)
    public void categories() {
        final CategoriesRequest categoriesRequest = new CategoriesRequest.Builder()
                .filterByCategoryIds(Arrays.asList(19534l))
                .build();

        (new RequestTask(new Runnable() {
            @Override
            public void run() {
                mShopApiClient.requestCategories(categoriesRequest);
            }
        })).execute();
    }

    @OnClick(R.id.facets)
    public void facets() {
        final FacetsRequest facetRequest = new FacetsRequest.Builder()
                .filterByFacetGroup(Arrays.asList(FacetGroup.SIZE))
                .build();

        (new RequestTask(new Runnable() {
            @Override
            public void run() {
                mShopApiClient.requestFacets(facetRequest);
            }
        })).execute();
    }

    @OnClick(R.id.facettypes)
    public void facettypes() {
        (new RequestTask(new Runnable() {
            @Override
            public void run() {
                mShopApiClient.requestFacetTypes();
            }
        })).execute();
    }

    @OnClick(R.id.autocomplete)
    public void autocomplete() {
        final AutocompleteRequest autocompleteRequest = new AutocompleteRequest.Builder("Sho")
                .filterByTypes( Arrays.asList(AutocompleteType.PRODUCTS))
                .limit(10)
                .build();


        (new RequestTask(new Runnable() {
            @Override
            public void run() {
                mShopApiClient.requestAutocompletion(autocompleteRequest);
            }
        })).execute();
    }

    @OnClick(R.id.productsearch)
    public void productsearch() {
        Map<FacetGroup, List<Long>> facetFilter = new HashMap<>();
        facetFilter.put(FacetGroup.CUPSIZE, Arrays.asList(93l, 94l, 95l, 96l));

        final ProductSearchRequest request = new ProductSearchRequest.Builder("session4711")
                .filterByMinPrice(500)
                .filterByMaxPrice(5000)
                .filterByStatus(ProductFilter.NONSALEONLY)
                .filterByFacets(facetFilter)
                .sortBy(Sortby.RELEVANCE, Direction.DESC)
                .limit(10)
                .build();

        (new RequestTask(new Runnable() {
            @Override
            public void run() {
                mShopApiClient.requestProductSearch(request);
            }
        })).execute();
    }

    @OnClick(R.id.products)
    public void products() {
        final ProductsRequest productRequest = new ProductsRequest.Builder()
                .filterByProductIds(Arrays.asList(329777l, 325136l))
                .listFields(Arrays.asList(ProductFields.VARIANTS))
                .build();

        (new RequestTask(new Runnable() {
            @Override
            public void run() {
                mShopApiClient.requestProducts(productRequest);
            }
        })).execute();
    }

    @OnClick(R.id.basketadd)
    public void basketadd() {
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(new AddOrderLine(UUID.randomUUID().toString(), 5615651l));

        final BasketModifyRequest basketModifyRequest = new BasketModifyRequest.Builder("session4711")
                .setOrderLines(orderLines)
                .build();

        (new RequestTask(new Runnable() {
            @Override
            public void run() {
                mShopApiClient.requestModifyBasket(basketModifyRequest);
            }
        })).execute();
    }

    public class RequestTask extends AsyncTask<Void, Void, String> {

        private Runnable mRunnable;

        public RequestTask(Runnable runnable) {
            mRunnable = runnable;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                mRunnable.run();
            } catch (CollinsException e) {
                return e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String message) {
            if (message == null) {
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class Logger implements ShopApiClient.Logger {

        private static final String TAG = "ShopSDK";

        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    }

}
