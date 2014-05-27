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
import de.aboutyou.enums.FacetType;
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
import de.aboutyou.internal.typeadapter.FacetTypeTypeAdapter;
import de.aboutyou.internal.typeadapter.ProductFieldsTypeAdapter;
import de.aboutyou.internal.typeadapter.SortbyTypeAdapter;
import de.aboutyou.internal.typeadapter.SuggestTypeAdapter;
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

    public static final String CDN_URL = "http://cdn.mary-paul.de/file/";

    public interface AuthenticationCallback {
        public void onSuccess(String accessToken);
        public void onFailure();
    }

    public interface Logger {
        public void log(String message);
    }

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    private final RestInterface mAPI;
    private final Logger mLogger;

    private final String mAppId;
    private final Endpoint mEndpoint;

    /**
     * Constructs a ShopApiClient
     * <p>
     * To get a valid appId / appSecret, please visit our <a href="http://developer.aboutyou.de">Developer Center</a>.
     *
     * @param appId The "App-ID" for your app from our <a href="http://developer.aboutyou.de">Developer Center</a>
     * @param appSecret The "Secret" for your app from our <a href="http://developer.aboutyou.de">Developer Center</a>
     * @param endpoint The {@link de.aboutyou.enums.Endpoint} you want to connect to; either LIVE or STAGE
     * @param logger A {@link de.aboutyou.ShopApiClient.Logger} instance to receive log output from the SDK
     */
    public ShopApiClient(String appId, String appSecret, Endpoint endpoint, Logger logger) {
        this(appId, appSecret, endpoint, logger, buildClient());
    }

    protected ShopApiClient(String appId, String appSecret, Endpoint endpoint, Logger logger, Client client) {
        RequestInterceptor authRequestInterceptor = new AuthenticationRequestInterceptor(appId, appSecret);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint.getUrl())
                .setClient(client)
                .setRequestInterceptor(authRequestInterceptor)
                .setConverter(buildGsonConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mAPI = restAdapter.create(RestInterface.class);
        mLogger = logger;

        mAppId = appId;
        mEndpoint = endpoint;
    }

    private static Client buildClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);
        return new OkClient(okHttpClient);
    }

    private static GsonConverter buildGsonConverter() {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setDateFormat(DATE_FORMAT)
                .registerTypeAdapter(FacetType.class, new FacetTypeTypeAdapter())
                .registerTypeAdapter(AutocompleteType.class, new AutocompleteTypeAdapter())
                .registerTypeAdapter(Sortby.class, new SortbyTypeAdapter())
                .registerTypeAdapter(Direction.class, new DirectionTypeAdapter())
                .registerTypeAdapter(ProductFields.class, new ProductFieldsTypeAdapter())
                .registerTypeAdapter(Attributes.class, new AttributesTypeAdapter())
                .registerTypeAdapter(Suggest.class, new SuggestTypeAdapter())
                .create();
        return new GsonConverter(gson);
    }

    /**
     * Starts an OAuth authentication flow to get an access token
     *
     * @param context An Android context to use for showing the web based OAuth dialog
     * @param scopes A list of {@link de.aboutyou.enums.AuthScope AuthScopes} to request authentication for
     * @param mode The {@link de.aboutyou.enums.AuthenticationRequestMode} to use
     * @param redirectUrl A redirect URL for the OAuth process; this URL has to match one of the URLs in "OAuth Callback URL" in the developer center
     * @param callback An {@link de.aboutyou.ShopApiClient.AuthenticationCallback} instance to be called after authentication success / failure
     */
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

    /**
     * Requests a list of {@link de.aboutyou.models.Category Categories}
     *
     * @param categoriesRequest A {@link de.aboutyou.request.CategoriesRequest}
     * @return The list of {@link de.aboutyou.models.Category Categories} matching the {@link de.aboutyou.request.CategoriesRequest} request parameter
     */
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

    /**
     * Requests the tree of categories configured for your app in our <a href="http://developer.aboutyou.de">Developer Center</a>
     *
     * @return The {@link de.aboutyou.models.CategoryTree} for your app
     */
    public CategoryTree requestCategoryTree() {
        RequestEnvelope<CategoryTreeRequest> wrappedRequest = RequestEnvelope.wrap(new CategoryTreeRequest());

        try {
            return mAPI.requestCategoryTree(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    /**
     * Requests a list of {@link de.aboutyou.models.Facet Facets}
     *
     * @param facetsRequest A {@link de.aboutyou.request.FacetsRequest}
     * @return The list of {@link de.aboutyou.models.Facet Facets} matching the {@link de.aboutyou.request.FacetsRequest} request parameter
     */
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

    /**
     * Requests the list of available {@link de.aboutyou.enums.FacetType FacetTypes}
     *
     * @return The list of available {@link de.aboutyou.enums.FacetType FacetTypes}
     */
    public List<FacetType> requestFacetTypes() {
        RequestEnvelope<FacetTypesRequest> wrappedRequest = RequestEnvelope.wrap(new FacetTypesRequest());

        try {
            return mAPI.requestFacetTypes(wrappedRequest).unwrap().get();
        } catch (RetrofitError e) {
            handleRetrofitError(e);
            return null;
        }
    }

    /**
     * Requests autocomplete suggestions for a search input field
     *
     * @param autocompleteRequest A {@link de.aboutyou.request.AutocompleteRequest}
     * @return An {@link de.aboutyou.models.Autocomplete} instance with {@link de.aboutyou.models.Product Products} and {@link de.aboutyou.models.Category Categories} matching the {@link de.aboutyou.request.AutocompleteRequest @AutocompleteRequest} request parameter
     */
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

    /**
     * Requests suggestions
     *
     * @param suggestRequest A {@link de.aboutyou.request.SuggestRequest}
     * @return An {@link de.aboutyou.models.Suggest} instance which is a list of {@link java.lang.String Strings} matching the {@link de.aboutyou.request.SuggestRequest} request parameter
     */
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

    /**
     * Request a product search
     *
     * @param productSearchRequest A {@link de.aboutyou.request.ProductSearchRequest}
     * @return A {@link de.aboutyou.models.ProductSearch} instance with {@link de.aboutyou.models.Product Products} matching the {@link de.aboutyou.request.ProductSearchRequest} request parameter
     */
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

    /**
     * Requests a list of live variants
     *
     * @param liveVariantRequest A {@link de.aboutyou.request.LiveVariantRequest}
     * @return A list of {@link de.aboutyou.models.LiveVariant LiveVariants} matching the {@link de.aboutyou.request.LiveVariantRequest} request parameter
     */
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

    /**
     * Requests a list of products
     *
     * @param productsRequest A {@link de.aboutyou.request.ProductsRequest}
     * @return A list of {@link de.aboutyou.models.Product Products} mathing the {@link de.aboutyou.request.ProductsRequest} request parameter
     */
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

    /**
     * Requests to modify a basket
     *
     * @param basketModifyRequest A {@link de.aboutyou.request.BasketModifyRequest}
     * @return The newly created or modified {@link de.aboutyou.models.Basket}
     */
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

    /**
     * Requests a basket
     *
     * @param basketGetRequest {@link de.aboutyou.request.BasketGetRequest}
     * @return The requested {@link de.aboutyou.models.Basket}
     */
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

    /**
     * Initiates an order
     *
     * @param initiateOrderRequest A {@link de.aboutyou.request.InitiateOrderRequest}
     * @return A {@link de.aboutyou.models.InitiateOrder} instance
     */
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
        } else if (e.getResponse() != null && e.getResponse().getStatus() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            HttpError httpError = (HttpError) e.getBodyAs(HttpError.class);
            throw new HttpException(httpError, null);
        } else {
            throw new CollinsException("Unknown exception");
        }
    }

    public class Helper {

        /**
         * Returns a list of {@link de.aboutyou.enums.SimpleColor SimpleColors} which is a subset of all available colors
         *
         * @return A list of {@link de.aboutyou.enums.SimpleColor SimpleColors}
         */
        public List<SimpleColor> getSimpleColorFacets() {
            return Arrays.asList(SimpleColor.values());
        }
    }
}
