package de.sliceanddice.maryandpaul.lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.okhttp.OkHttpClient;

import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import de.sliceanddice.maryandpaul.lib.enums.Direction;
import de.sliceanddice.maryandpaul.lib.enums.Endpoint;
import de.sliceanddice.maryandpaul.lib.enums.FacetGroup;
import de.sliceanddice.maryandpaul.lib.enums.ProductFields;
import de.sliceanddice.maryandpaul.lib.enums.Sortby;
import de.sliceanddice.maryandpaul.lib.enums.Type;
import de.sliceanddice.maryandpaul.lib.internal.communication.AuthenticationRequestInterceptor;
import de.sliceanddice.maryandpaul.lib.internal.communication.RestInterface;
import de.sliceanddice.maryandpaul.lib.internal.response.AutocompleteResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.BasketAddResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.CategoriesResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.CategoryTreeResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.CollinsResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.FacetTypesResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.FacetsResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.ProductSearchResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.ProductsResponse;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.DirectionTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.FacetGroupTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.ProductFieldsTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.SortbyTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.TypeTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.widget.AuthWebDialog;
import de.sliceanddice.maryandpaul.lib.internal.wrapper.RequestEnvelope;
import de.sliceanddice.maryandpaul.lib.internal.wrapper.ResponseEnvelope;
import de.sliceanddice.maryandpaul.lib.models.Autocomplete;
import de.sliceanddice.maryandpaul.lib.models.Basket;
import de.sliceanddice.maryandpaul.lib.models.Category;
import de.sliceanddice.maryandpaul.lib.models.CategoryTree;
import de.sliceanddice.maryandpaul.lib.models.Facet;
import de.sliceanddice.maryandpaul.lib.models.Product;
import de.sliceanddice.maryandpaul.lib.models.ProductSearch;
import de.sliceanddice.maryandpaul.lib.request.AutocompleteRequest;
import de.sliceanddice.maryandpaul.lib.request.BasketAddRequest;
import de.sliceanddice.maryandpaul.lib.request.CategoriesRequest;
import de.sliceanddice.maryandpaul.lib.request.CategoryTreeRequest;
import de.sliceanddice.maryandpaul.lib.request.CollinsRequest;
import de.sliceanddice.maryandpaul.lib.request.FacetsRequest;
import de.sliceanddice.maryandpaul.lib.request.FacetTypesRequest;
import de.sliceanddice.maryandpaul.lib.request.ProductsRequest;
import de.sliceanddice.maryandpaul.lib.request.ProductSearchRequest;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class ShopApiClient {

    public interface Callback<T> {
        public void onCompleted(T response);
        public void onError(String message);
    }

    public interface AuthenticationCallback {
        public void onSuccess(String accessToken);
        public void onFailure();
    }

    public interface Logger {

    }

    private final RestInterface mAPI;
    private final Logger mLogger;

    private final String mAppId;
    private final Endpoint mEndpoint;

    public ShopApiClient(String appId, String appPassword, Endpoint endpoint, Logger logger) {
        RequestInterceptor authRequestInterceptor = new AuthenticationRequestInterceptor(appId, appPassword);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint.getUrl())
                .setClient(buildClient())
                .setRequestInterceptor(authRequestInterceptor)
                .setConverter(buildGsonConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mAPI = restAdapter.create(RestInterface.class);
        mLogger = logger;

        mAppId = appId;
        mEndpoint = endpoint;
    }

    private Client buildClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);
        return new OkClient(okHttpClient);
    }

    private GsonConverter buildGsonConverter() {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setDateFormat("dd-MM-yyyy HH:mm:ss")
                .registerTypeAdapter(FacetGroup.class, new FacetGroupTypeAdapter())
                .registerTypeAdapter(Type.class, new TypeTypeAdapter())
                .registerTypeAdapter(Sortby.class, new SortbyTypeAdapter())
                .registerTypeAdapter(Direction.class, new DirectionTypeAdapter())
                .registerTypeAdapter(ProductFields.class, new ProductFieldsTypeAdapter())
                .create();
        return new GsonConverter(gson);
    }

    public void login(Context context, List<String> scopes, final AuthenticationCallback callback) {
        requestAuthentication(context, scopes, AuthWebDialog.Mode.LOGIN, callback);
    }

    public void register(Context context, List<String> scopes, final AuthenticationCallback callback) {
        requestAuthentication(context, scopes, AuthWebDialog.Mode.REGISTER, callback);
    }

    public void authenticate(Context context, List<String> scopes, final AuthenticationCallback callback) {
        requestAuthentication(context, scopes, AuthWebDialog.Mode.DEFAULT, callback);
    }

    private void requestAuthentication(Context context, List<String> scopes, AuthWebDialog.Mode mode, final AuthenticationCallback callback) {
        AuthWebDialog.OnCompleteListener listener = new AuthWebDialog.OnCompleteListener() {
            @Override
            public void onComplete(String accessToken) {
                if (accessToken != null) {
                    callback.onSuccess(accessToken);
                } else {
                    callback.onFailure();
                }
            }
        };

        AuthWebDialog loginDialog = new AuthWebDialog(context, mAppId, mEndpoint, scopes, mode, listener);
        loginDialog.show();
    }

    public void requestCategories(CategoriesRequest categoriesRequest, final Callback<List<Category>> callback) {
        validateParams(categoriesRequest, callback);
        RequestEnvelope<CategoriesRequest> wrappedRequest = RequestEnvelope.wrap(categoriesRequest);
        mAPI.requestCategories(wrappedRequest, new RetrofitCallback<CategoriesResponse, List<Category>>(callback));
    }

    public void requestCategoryTree(final Callback<CategoryTree> callback) {
        validateParam(callback);
        RequestEnvelope<CategoryTreeRequest> wrappedRequest = RequestEnvelope.wrap(new CategoryTreeRequest());
        mAPI.requestCategoryTree(wrappedRequest, new RetrofitCallback<CategoryTreeResponse, CategoryTree>(callback));
    }

    public void requestFacets(FacetsRequest facetsRequest, final Callback<List<Facet>> callback) {
        validateParams(facetsRequest, callback);
        RequestEnvelope<FacetsRequest> wrappedRequest = RequestEnvelope.wrap(facetsRequest);
        mAPI.requestFacets(wrappedRequest, new RetrofitCallback<FacetsResponse, List<Facet>>(callback));
    }

    public void requestFacetTypes(final Callback<List<FacetGroup>> callback) {
        validateParam(callback);
        RequestEnvelope<FacetTypesRequest> wrappedRequest = RequestEnvelope.wrap(new FacetTypesRequest());
        mAPI.requestFacetTypes(wrappedRequest, new RetrofitCallback<FacetTypesResponse, List<FacetGroup>>(callback));
    }

    public void requestAutocompletion(AutocompleteRequest autocompleteRequest, final Callback<Autocomplete> callback) {
        validateParams(autocompleteRequest, callback);
        RequestEnvelope<AutocompleteRequest> wrappedRequest = RequestEnvelope.wrap(autocompleteRequest);
        mAPI.requestAutocomplete(wrappedRequest, new RetrofitCallback<AutocompleteResponse, Autocomplete>(callback));
    }

    public void requestProductSearch(ProductSearchRequest productSearchRequest, final Callback<ProductSearch> callback) {
        validateParams(productSearchRequest, callback);
        RequestEnvelope<ProductSearchRequest> wrappedRequest = RequestEnvelope.wrap(productSearchRequest);
        mAPI.requestProductSearch(wrappedRequest, new RetrofitCallback<ProductSearchResponse, ProductSearch>(callback));
    }

    public void requestProducts(ProductsRequest productsRequest, final Callback<List<Product>> callback) {
        validateParams(productsRequest, callback);
        RequestEnvelope<ProductsRequest> wrappedRequest = RequestEnvelope.wrap(productsRequest);
        mAPI.requestProducts(wrappedRequest, new RetrofitCallback<ProductsResponse, List<Product>>(callback));
    }

    public void requestAddBasket(BasketAddRequest basketAddRequest, final Callback<Basket> callback) {
        validateParams(basketAddRequest, callback);
        RequestEnvelope<BasketAddRequest> wrappedRequest = RequestEnvelope.wrap(basketAddRequest);
        mAPI.requestAddBasket(wrappedRequest, new RetrofitCallback<BasketAddResponse, Basket>(callback));
    }

    private void validateParams(CollinsRequest request, Callback callback) {
        validateParam(request);
        validateParam(callback);
    }

    private void validateParam(CollinsRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request must not be null");
        }
    }

    private void validateParam(Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }
    }

    private class RetrofitCallback<K extends CollinsResponse<V>, V> implements retrofit.Callback<ResponseEnvelope<K>> {

        private Callback<V> mCallback;

        public RetrofitCallback(Callback<V> callback) {
            mCallback = callback;
        }

        @Override
        public void success(ResponseEnvelope<K> responseEnvelope, Response response) {
            mCallback.onCompleted(responseEnvelope.unwrap().get());
            mCallback = null;
        }

        @Override
        public void failure(RetrofitError error) {
            mCallback.onError("failure");
            mCallback = null;
        }
    }

}
