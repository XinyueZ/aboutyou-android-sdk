package de.aboutyou;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.okhttp.OkHttpClient;

import org.apache.http.HttpStatus;

import android.content.Context;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.aboutyou.enums.AuthScope;
import de.aboutyou.enums.AuthenticationRequestMode;
import de.aboutyou.enums.AutocompleteType;
import de.aboutyou.enums.Direction;
import de.aboutyou.enums.Endpoint;
import de.aboutyou.enums.FacetGroup;
import de.aboutyou.enums.ProductFields;
import de.aboutyou.enums.SimpleColor;
import de.aboutyou.enums.Sortby;
import de.aboutyou.exceptions.CollinsException;
import de.aboutyou.exceptions.HttpException;
import de.aboutyou.exceptions.NetworkException;
import de.aboutyou.internal.communication.AuthenticationRequestInterceptor;
import de.aboutyou.internal.communication.RestInterface;
import de.aboutyou.internal.typeadapter.AttributesTypeAdapter;
import de.aboutyou.internal.typeadapter.AutocompleteTypeAdapter;
import de.aboutyou.internal.typeadapter.DirectionTypeAdapter;
import de.aboutyou.internal.typeadapter.FacetGroupTypeAdapter;
import de.aboutyou.internal.typeadapter.ProductFieldsTypeAdapter;
import de.aboutyou.internal.typeadapter.SortbyTypeAdapter;
import de.aboutyou.internal.widget.AuthWebDialog;
import de.aboutyou.internal.wrapper.RequestEnvelope;
import de.aboutyou.models.Attributes;
import de.aboutyou.models.Autocomplete;
import de.aboutyou.models.Basket;
import de.aboutyou.models.Category;
import de.aboutyou.models.CategoryTree;
import de.aboutyou.models.Facet;
import de.aboutyou.models.HttpError;
import de.aboutyou.models.InitiateOrder;
import de.aboutyou.models.LiveVariant;
import de.aboutyou.models.Product;
import de.aboutyou.models.ProductSearch;
import de.aboutyou.models.Suggest;
import de.aboutyou.request.AutocompleteRequest;
import de.aboutyou.request.BasketGetRequest;
import de.aboutyou.request.BasketModifyRequest;
import de.aboutyou.request.CategoriesRequest;
import de.aboutyou.request.CategoryTreeRequest;
import de.aboutyou.request.CollinsRequest;
import de.aboutyou.request.FacetTypesRequest;
import de.aboutyou.request.FacetsRequest;
import de.aboutyou.request.InitiateOrderRequest;
import de.aboutyou.request.LiveVariantRequest;
import de.aboutyou.request.ProductSearchRequest;
import de.aboutyou.request.ProductsRequest;
import de.aboutyou.request.SuggestRequest;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
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
        public void log(String message);
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

        try {
            return mAPI.requestCategories(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    public CategoryTree requestCategoryTree() {
        RequestEnvelope<CategoryTreeRequest> wrappedRequest = RequestEnvelope.wrap(new CategoryTreeRequest());

        try {
            return mAPI.requestCategoryTree(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    public List<Facet> requestFacets(FacetsRequest facetsRequest) {
        validateRequest(facetsRequest);
        RequestEnvelope<FacetsRequest> wrappedRequest = RequestEnvelope.wrap(facetsRequest);

        try {
            return mAPI.requestFacets(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    public List<FacetGroup> requestFacetTypes() {
        RequestEnvelope<FacetTypesRequest> wrappedRequest = RequestEnvelope.wrap(new FacetTypesRequest());

        try {
            return mAPI.requestFacetTypes(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    public Autocomplete requestAutocompletion(AutocompleteRequest autocompleteRequest) {
        validateRequest(autocompleteRequest);
        RequestEnvelope<AutocompleteRequest> wrappedRequest = RequestEnvelope.wrap(autocompleteRequest);

        try {
            return mAPI.requestAutocomplete(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    public Suggest requestSuggest(SuggestRequest suggestRequest) {
        validateRequest(suggestRequest);
        RequestEnvelope<SuggestRequest> wrappedRequest = RequestEnvelope.wrap(suggestRequest);

        try {
            return mAPI.requestSuggest(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    public ProductSearch requestProductSearch(ProductSearchRequest productSearchRequest) {
        validateRequest(productSearchRequest);
        RequestEnvelope<ProductSearchRequest> wrappedRequest = RequestEnvelope.wrap(productSearchRequest);

        try {
            return mAPI.requestProductSearch(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    public List<LiveVariant> requestLiveVariants(LiveVariantRequest liveVariantRequest) {
        validateRequest(liveVariantRequest);
        RequestEnvelope<LiveVariantRequest> wrappedRequest = RequestEnvelope.wrap(liveVariantRequest);

        try {
            return mAPI.requestLiveVariants(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    public List<Product> requestProducts(ProductsRequest productsRequest) {
        validateRequest(productsRequest);
        RequestEnvelope<ProductsRequest> wrappedRequest = RequestEnvelope.wrap(productsRequest);

        try {
            return mAPI.requestProducts(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    public Basket requestModifyBasket(BasketModifyRequest basketModifyRequest) {
        validateRequest(basketModifyRequest);
        RequestEnvelope<BasketModifyRequest> wrappedRequest = RequestEnvelope.wrap(basketModifyRequest);

        try {
            return mAPI.requestModifyBasket(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    public Basket requestGetBasket(BasketGetRequest basketGetRequest) {
        validateRequest(basketGetRequest);
        RequestEnvelope<BasketGetRequest> wrappedRequest = RequestEnvelope.wrap(basketGetRequest);

        try {
            return mAPI.requestGetBasket(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    public InitiateOrder requestInitiateOrder(InitiateOrderRequest initiateOrderRequest) {
        validateRequest(initiateOrderRequest);
        RequestEnvelope<InitiateOrderRequest> wrappedRequest = RequestEnvelope.wrap(initiateOrderRequest);

        try {
            return mAPI.requestInitiateOrder(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }


    private void validateRequest(CollinsRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request must not be null");
        }
    }

    private void handleRetrofitError(RetrofitError e) {
        if (e.isNetworkError()) {
            throw new NetworkException(e.getCause());
        } else if (e.getResponse().getStatus() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            HttpError httpError = (HttpError) e.getBodyAs(HttpError.class);
            throw new HttpException(httpError, null);
        } else {
            throw new CollinsException("Unknown exception");
        }
    }

    public class Helper {

        public List<SimpleColor> getSimpleColorFacets() {
            return Arrays.asList(SimpleColor.values());
        }
    }
}
