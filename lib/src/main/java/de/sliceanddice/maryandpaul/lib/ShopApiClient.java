package de.sliceanddice.maryandpaul.lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.okhttp.OkHttpClient;

import android.content.Context;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.sliceanddice.maryandpaul.lib.enums.AuthScope;
import de.sliceanddice.maryandpaul.lib.enums.AuthenticationRequestMode;
import de.sliceanddice.maryandpaul.lib.enums.AutocompleteType;
import de.sliceanddice.maryandpaul.lib.enums.Direction;
import de.sliceanddice.maryandpaul.lib.enums.Endpoint;
import de.sliceanddice.maryandpaul.lib.enums.FacetGroup;
import de.sliceanddice.maryandpaul.lib.enums.ProductFields;
import de.sliceanddice.maryandpaul.lib.enums.SimpleColor;
import de.sliceanddice.maryandpaul.lib.enums.Sortby;
import de.sliceanddice.maryandpaul.lib.internal.communication.AuthenticationRequestInterceptor;
import de.sliceanddice.maryandpaul.lib.internal.communication.RestInterface;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.AttributesTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.AutocompleteTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.DirectionTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.FacetGroupTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.ProductFieldsTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.SortbyTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.widget.AuthWebDialog;
import de.sliceanddice.maryandpaul.lib.internal.wrapper.RequestEnvelope;
import de.sliceanddice.maryandpaul.lib.models.Attributes;
import de.sliceanddice.maryandpaul.lib.models.Autocomplete;
import de.sliceanddice.maryandpaul.lib.models.Basket;
import de.sliceanddice.maryandpaul.lib.models.Category;
import de.sliceanddice.maryandpaul.lib.models.CategoryTree;
import de.sliceanddice.maryandpaul.lib.models.Facet;
import de.sliceanddice.maryandpaul.lib.models.InitiateOrder;
import de.sliceanddice.maryandpaul.lib.models.LiveVariant;
import de.sliceanddice.maryandpaul.lib.models.Product;
import de.sliceanddice.maryandpaul.lib.models.ProductSearch;
import de.sliceanddice.maryandpaul.lib.models.Suggest;
import de.sliceanddice.maryandpaul.lib.request.AutocompleteRequest;
import de.sliceanddice.maryandpaul.lib.request.BasketModifyRequest;
import de.sliceanddice.maryandpaul.lib.request.BasketGetRequest;
import de.sliceanddice.maryandpaul.lib.request.CategoriesRequest;
import de.sliceanddice.maryandpaul.lib.request.CategoryTreeRequest;
import de.sliceanddice.maryandpaul.lib.request.CollinsRequest;
import de.sliceanddice.maryandpaul.lib.request.FacetsRequest;
import de.sliceanddice.maryandpaul.lib.request.FacetTypesRequest;
import de.sliceanddice.maryandpaul.lib.request.InitiateOrderRequest;
import de.sliceanddice.maryandpaul.lib.request.LiveVariantRequest;
import de.sliceanddice.maryandpaul.lib.request.ProductsRequest;
import de.sliceanddice.maryandpaul.lib.request.ProductSearchRequest;
import de.sliceanddice.maryandpaul.lib.request.SuggestRequest;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class ShopApiClient {

    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

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
                .setDateFormat(DATE_FORMAT)
                .registerTypeAdapter(FacetGroup.class, new FacetGroupTypeAdapter())
                .registerTypeAdapter(AutocompleteType.class, new AutocompleteTypeAdapter())
                .registerTypeAdapter(Sortby.class, new SortbyTypeAdapter())
                .registerTypeAdapter(Direction.class, new DirectionTypeAdapter())
                .registerTypeAdapter(ProductFields.class, new ProductFieldsTypeAdapter())
                .registerTypeAdapter(Attributes.class, new AttributesTypeAdapter())
                .create();
        return new GsonConverter(gson);
    }

    public void requestAuthentication(Context context, List<AuthScope> scopes, AuthenticationRequestMode mode, String redirectUrl, final AuthenticationCallback callback) {
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

        AuthWebDialog loginDialog = new AuthWebDialog(context, mAppId, mEndpoint, scopes, mode, redirectUrl, listener);
        loginDialog.show();
    }

    public List<Category> requestCategories(CategoriesRequest categoriesRequest) {
        validateRequest(categoriesRequest);
        RequestEnvelope<CategoriesRequest> wrappedRequest = RequestEnvelope.wrap(categoriesRequest);
        return mAPI.requestCategories(wrappedRequest).unwrap().get();
    }

    public CategoryTree requestCategoryTree() {
        RequestEnvelope<CategoryTreeRequest> wrappedRequest = RequestEnvelope.wrap(new CategoryTreeRequest());
        return mAPI.requestCategoryTree(wrappedRequest).unwrap().get();
    }

    public List<Facet> requestFacets(FacetsRequest facetsRequest) {
        validateRequest(facetsRequest);
        RequestEnvelope<FacetsRequest> wrappedRequest = RequestEnvelope.wrap(facetsRequest);
        return mAPI.requestFacets(wrappedRequest).unwrap().get();
    }

    public List<FacetGroup> requestFacetTypes() {
        RequestEnvelope<FacetTypesRequest> wrappedRequest = RequestEnvelope.wrap(new FacetTypesRequest());
        return mAPI.requestFacetTypes(wrappedRequest).unwrap().get();
    }

    public Autocomplete requestAutocompletion(AutocompleteRequest autocompleteRequest) {
        validateRequest(autocompleteRequest);
        RequestEnvelope<AutocompleteRequest> wrappedRequest = RequestEnvelope.wrap(autocompleteRequest);
        return mAPI.requestAutocomplete(wrappedRequest).unwrap().get();
    }

    public Suggest requestSuggest(SuggestRequest suggestRequest) {
        validateRequest(suggestRequest);
        RequestEnvelope<SuggestRequest> wrappedRequest = RequestEnvelope.wrap(suggestRequest);
        return mAPI.requestSuggest(wrappedRequest).unwrap().get();
    }

    public ProductSearch requestProductSearch(ProductSearchRequest productSearchRequest) {
        validateRequest(productSearchRequest);
        RequestEnvelope<ProductSearchRequest> wrappedRequest = RequestEnvelope.wrap(productSearchRequest);
        return mAPI.requestProductSearch(wrappedRequest).unwrap().get();
    }

    public List<LiveVariant> requestLiveVariants(LiveVariantRequest liveVariantRequest) {
        validateRequest(liveVariantRequest);
        RequestEnvelope<LiveVariantRequest> wrappedRequest = RequestEnvelope.wrap(liveVariantRequest);
        return mAPI.requestLiveVariants(wrappedRequest).unwrap().get();
    }

    public List<Product> requestProducts(ProductsRequest productsRequest) {
        validateRequest(productsRequest);
        RequestEnvelope<ProductsRequest> wrappedRequest = RequestEnvelope.wrap(productsRequest);
        return mAPI.requestProducts(wrappedRequest).unwrap().get();
    }

    public Basket requestModifyBasket(BasketModifyRequest basketModifyRequest) {
        validateRequest(basketModifyRequest);
        RequestEnvelope<BasketModifyRequest> wrappedRequest = RequestEnvelope.wrap(basketModifyRequest);
        return mAPI.requestModifyBasket(wrappedRequest).unwrap().get();
    }

    public Basket requestGetBasket(BasketGetRequest basketGetRequest) {
        validateRequest(basketGetRequest);
        RequestEnvelope<BasketGetRequest> wrappedRequest = RequestEnvelope.wrap(basketGetRequest);
        return mAPI.requestGetBasket(wrappedRequest).unwrap().get();
    }

    public InitiateOrder requestInitiateOrder(InitiateOrderRequest initiateOrderRequest) {
        validateRequest(initiateOrderRequest);
        RequestEnvelope<InitiateOrderRequest> wrappedRequest = RequestEnvelope.wrap(initiateOrderRequest);
        return mAPI.requestInitiateOrder(wrappedRequest).unwrap().get();
    }

    private void validateRequest(CollinsRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request must not be null");
        }
    }

    public class Helper {

        public List<SimpleColor> getSimpleColorFacets() {
            return Arrays.asList(SimpleColor.values());
        }
    }
}
