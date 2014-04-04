package de.sliceanddice.maryandpaul.lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

import de.sliceanddice.maryandpaul.lib.communication.AuthenticationRequestInterceptor;
import de.sliceanddice.maryandpaul.lib.communication.RestInterface;
import de.sliceanddice.maryandpaul.lib.enums.Direction;
import de.sliceanddice.maryandpaul.lib.enums.FacetGroup;
import de.sliceanddice.maryandpaul.lib.enums.Sortby;
import de.sliceanddice.maryandpaul.lib.enums.Type;
import de.sliceanddice.maryandpaul.lib.models.Autocomplete;
import de.sliceanddice.maryandpaul.lib.models.Category;
import de.sliceanddice.maryandpaul.lib.models.CategoryTree;
import de.sliceanddice.maryandpaul.lib.models.Facet;
import de.sliceanddice.maryandpaul.lib.models.Product;
import de.sliceanddice.maryandpaul.lib.models.ProductSearch;
import de.sliceanddice.maryandpaul.lib.requests.AutocompleteRequest;
import de.sliceanddice.maryandpaul.lib.requests.CategoryRequest;
import de.sliceanddice.maryandpaul.lib.requests.CategoryTreeRequest;
import de.sliceanddice.maryandpaul.lib.requests.FacetRequest;
import de.sliceanddice.maryandpaul.lib.requests.FacetTypesRequest;
import de.sliceanddice.maryandpaul.lib.requests.ProductRequest;
import de.sliceanddice.maryandpaul.lib.requests.ProductSearchRequest;
import de.sliceanddice.maryandpaul.lib.typeadapter.DirectionTypeAdapter;
import de.sliceanddice.maryandpaul.lib.typeadapter.FacetGroupTypeAdapter;
import de.sliceanddice.maryandpaul.lib.typeadapter.SortbyTypeAdapter;
import de.sliceanddice.maryandpaul.lib.typeadapter.TypeTypeAdapter;
import de.sliceanddice.maryandpaul.lib.wrapper.RequestWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.ResponseWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.request.AutocompleteWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.request.CategoriesWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.request.CategoryTreeWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.request.FacetTypesWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.request.FacetsWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.request.ProductSearchWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.request.ProductsWrapper;
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

    public void requestCategories(CategoryRequest categoryRequest, final Callback<List<Category>> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        RequestWrapper<CategoryRequest> wrappedRequest = RequestWrapper.wrap(categoryRequest);
        mAPI.requestCategories(wrappedRequest, new retrofit.Callback<ResponseWrapper<CategoriesWrapper>>() {
            @Override
            public void success(ResponseWrapper<CategoriesWrapper> categoriesWrapper, Response response) {
                callback.onCompleted(categoriesWrapper.getWrappedObject().getCategories());
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

        RequestWrapper<CategoryTreeRequest> wrappedRequest = RequestWrapper.wrap(new CategoryTreeRequest());
        mAPI.requestCategoryTree(wrappedRequest, new retrofit.Callback<ResponseWrapper<CategoryTreeWrapper>>() {
            @Override
            public void success(ResponseWrapper<CategoryTreeWrapper> categoryTreeWrapper, Response response) {
                callback.onCompleted(new CategoryTree(categoryTreeWrapper.getWrappedObject().getCategoryTree()));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void requestFacets(FacetRequest facetRequest, final Callback<List<Facet>> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        RequestWrapper<FacetRequest> wrappedRequest = RequestWrapper.wrap(facetRequest);
        mAPI.requestFacets(wrappedRequest, new retrofit.Callback<ResponseWrapper<FacetsWrapper>>() {
            @Override
            public void success(ResponseWrapper<FacetsWrapper> facetsWrapper, Response response) {
                callback.onCompleted(facetsWrapper.getWrappedObject().getFacets());
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

        RequestWrapper<FacetTypesRequest> wrappedRequest = RequestWrapper.wrap(new FacetTypesRequest());
        mAPI.requestFacetTypes(wrappedRequest, new retrofit.Callback<ResponseWrapper<FacetTypesWrapper>> () {
            @Override
            public void success(ResponseWrapper<FacetTypesWrapper> facetTypesWrapper, Response response) {
                ArrayList<FacetGroup> facetGroups = new ArrayList<>();
                for (Integer facetGroupId : facetTypesWrapper.getWrappedObject().getFacetTypes()) {
                    FacetGroup facetGroup = FacetGroup.fromInteger(facetGroupId);
                    if (facetGroup != null) {
                        facetGroups.add(FacetGroup.fromInteger(facetGroupId));
                    }
                }

                callback.onCompleted(facetGroups);
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

        RequestWrapper<AutocompleteRequest> wrappedRequest = RequestWrapper.wrap(autocompleteRequest);
        mAPI.requestAutocomplete(wrappedRequest, new retrofit.Callback<ResponseWrapper<AutocompleteWrapper>>() {
            @Override
            public void success(ResponseWrapper<AutocompleteWrapper> autocompleteWrapper, Response response) {
                callback.onCompleted(autocompleteWrapper.getWrappedObject().getAutocomplete());
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

        RequestWrapper<ProductSearchRequest> wrappedRequest = RequestWrapper.wrap(productSearchRequest);
        mAPI.requestProductSearch(wrappedRequest, new retrofit.Callback<ResponseWrapper<ProductSearchWrapper>>() {
            @Override
            public void success(ResponseWrapper<ProductSearchWrapper> productSearchWrapper, Response response) {
                callback.onCompleted(productSearchWrapper.getWrappedObject().getProductSearch());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void requestProducts(ProductRequest productRequest, final Callback<List<Product>> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        RequestWrapper<ProductRequest> wrappedRequest = RequestWrapper.wrap(productRequest);
        mAPI.requestProducts(wrappedRequest, new retrofit.Callback<ResponseWrapper<ProductsWrapper>>() {
            @Override
            public void success(ResponseWrapper<ProductsWrapper> productsWrapper, Response response) {
                callback.onCompleted(productsWrapper.getWrappedObject().getProducts());
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
