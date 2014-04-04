package de.sliceanddice.maryandpaul.lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.enums.Direction;
import de.sliceanddice.maryandpaul.lib.enums.FacetGroup;
import de.sliceanddice.maryandpaul.lib.enums.Sortby;
import de.sliceanddice.maryandpaul.lib.enums.Type;
import de.sliceanddice.maryandpaul.lib.internal.communication.AuthenticationRequestInterceptor;
import de.sliceanddice.maryandpaul.lib.internal.communication.RestInterface;
import de.sliceanddice.maryandpaul.lib.internal.response.AutocompleteResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.CategoriesResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.CategoryTreeResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.FacetTypesResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.FacetsResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.ProductSearchResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.ProductsResponse;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.DirectionTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.FacetGroupTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.SortbyTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.typeadapter.TypeTypeAdapter;
import de.sliceanddice.maryandpaul.lib.internal.wrapper.RequestEnvelope;
import de.sliceanddice.maryandpaul.lib.internal.wrapper.ResponseEnvelope;
import de.sliceanddice.maryandpaul.lib.models.Autocomplete;
import de.sliceanddice.maryandpaul.lib.models.Category;
import de.sliceanddice.maryandpaul.lib.models.CategoryTree;
import de.sliceanddice.maryandpaul.lib.models.Facet;
import de.sliceanddice.maryandpaul.lib.models.Product;
import de.sliceanddice.maryandpaul.lib.models.ProductSearch;
import de.sliceanddice.maryandpaul.lib.request.AutocompleteRequest;
import de.sliceanddice.maryandpaul.lib.request.CategoriesRequest;
import de.sliceanddice.maryandpaul.lib.request.CategoryTreeRequest;
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

    private final RestInterface mAPI;

    public ShopApiClient(String appId, String appPassword) {
        RequestInterceptor authRequestInterceptor = new AuthenticationRequestInterceptor(appId, appPassword);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://ant-shop-api1.wavecloud.de")
                .setClient(buildClient())
                .setRequestInterceptor(authRequestInterceptor)
                .setConverter(buildGsonConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mAPI = restAdapter.create(RestInterface.class);
    }

    private Client buildClient() {
        return new OkClient(new OkHttpClient());
    }

    private GsonConverter buildGsonConverter() {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .registerTypeAdapter(FacetGroup.class, new FacetGroupTypeAdapter())
                .registerTypeAdapter(Type.class, new TypeTypeAdapter())
                .registerTypeAdapter(Sortby.class, new SortbyTypeAdapter())
                .registerTypeAdapter(Direction.class, new DirectionTypeAdapter())
                .create();
        return new GsonConverter(gson);
    }

    public void requestCategories(CategoriesRequest categoriesRequest, final Callback<List<Category>> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        RequestEnvelope<CategoriesRequest> wrappedRequest = RequestEnvelope.wrap(categoriesRequest);
        mAPI.requestCategories(wrappedRequest, new retrofit.Callback<ResponseEnvelope<CategoriesResponse>>() {
            @Override
            public void success(ResponseEnvelope<CategoriesResponse> categoriesResponse, Response response) {
                callback.onCompleted(categoriesResponse.unwrap().get());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void requestCategoryTree(final Callback<CategoryTree> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        RequestEnvelope<CategoryTreeRequest> wrappedRequest = RequestEnvelope.wrap(new CategoryTreeRequest());
        mAPI.requestCategoryTree(wrappedRequest, new retrofit.Callback<ResponseEnvelope<CategoryTreeResponse>>() {
            @Override
            public void success(ResponseEnvelope<CategoryTreeResponse> categoryTreeWrapper, Response response) {
                callback.onCompleted(new CategoryTree(categoryTreeWrapper.unwrap().get()));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void requestFacets(FacetsRequest facetsRequest, final Callback<List<Facet>> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        RequestEnvelope<FacetsRequest> wrappedRequest = RequestEnvelope.wrap(facetsRequest);
        mAPI.requestFacets(wrappedRequest, new retrofit.Callback<ResponseEnvelope<FacetsResponse>>() {
            @Override
            public void success(ResponseEnvelope<FacetsResponse> facetsWrapper, Response response) {
                callback.onCompleted(facetsWrapper.unwrap().get());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void requestFacetTypes(final Callback<List<FacetGroup>> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        RequestEnvelope<FacetTypesRequest> wrappedRequest = RequestEnvelope.wrap(new FacetTypesRequest());
        mAPI.requestFacetTypes(wrappedRequest, new retrofit.Callback<ResponseEnvelope<FacetTypesResponse>> () {
            @Override
            public void success(ResponseEnvelope<FacetTypesResponse> facetTypesWrapper, Response response) {
                callback.onCompleted(facetTypesWrapper.unwrap().get());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void requestAutocompletion(AutocompleteRequest autocompleteRequest, final Callback<Autocomplete> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        RequestEnvelope<AutocompleteRequest> wrappedRequest = RequestEnvelope.wrap(autocompleteRequest);
        mAPI.requestAutocomplete(wrappedRequest, new retrofit.Callback<ResponseEnvelope<AutocompleteResponse>>() {
            @Override
            public void success(ResponseEnvelope<AutocompleteResponse> autocompleteResponse, Response response) {
                callback.onCompleted(autocompleteResponse.unwrap().get());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void requestProductSearch(ProductSearchRequest productSearchRequest, final Callback<ProductSearch> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        RequestEnvelope<ProductSearchRequest> wrappedRequest = RequestEnvelope.wrap(productSearchRequest);
        mAPI.requestProductSearch(wrappedRequest, new retrofit.Callback<ResponseEnvelope<ProductSearchResponse>>() {
            @Override
            public void success(ResponseEnvelope<ProductSearchResponse> productSearchWrapper, Response response) {
                callback.onCompleted(productSearchWrapper.unwrap().get());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void requestProducts(ProductsRequest productsRequest, final Callback<List<Product>> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        RequestEnvelope<ProductsRequest> wrappedRequest = RequestEnvelope.wrap(productsRequest);
        mAPI.requestProducts(wrappedRequest, new retrofit.Callback<ResponseEnvelope<ProductsResponse>>() {
            @Override
            public void success(ResponseEnvelope<ProductsResponse> productsResponse, Response response) {
                callback.onCompleted(productsResponse.unwrap().get());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public interface Callback<T> {
        public void onCompleted(T response);
        public void onError(String message); // TODO
    }
}
