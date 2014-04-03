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
import de.sliceanddice.maryandpaul.lib.models.ProductSearch;
import de.sliceanddice.maryandpaul.lib.requests.AutocompleteRequest;
import de.sliceanddice.maryandpaul.lib.requests.CategoryRequest;
import de.sliceanddice.maryandpaul.lib.requests.CategoryTreeRequest;
import de.sliceanddice.maryandpaul.lib.requests.FacetRequest;
import de.sliceanddice.maryandpaul.lib.requests.FacetTypesRequest;
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
        Client client = new OkClient(new OkHttpClient());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FacetGroup.class, new FacetGroupTypeAdapter());
        gsonBuilder.registerTypeAdapter(Type.class, new TypeTypeAdapter());
        gsonBuilder.registerTypeAdapter(Sortby.class, new SortbyTypeAdapter());
        gsonBuilder.registerTypeAdapter(Direction.class, new DirectionTypeAdapter());
        Gson gson = gsonBuilder.create();
        GsonConverter gsonConverter = new GsonConverter(gson);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://ant-shop-api1.wavecloud.de")
                .setClient(client)
                .setRequestInterceptor(authRequestInterceptor)
                .setConverter(gsonConverter)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mAPI = restAdapter.create(RestInterface.class);
    }

    public void requestCategories(List<Long> categoryIds, final Callback<List<Category>> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryIds(categoryIds);

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

        RequestWrapper<CategoryTreeRequest> wrappedRequest = RequestWrapper.forType(CategoryTreeRequest.class);
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

    public void requestFacets(List<FacetGroup> groupIds, Integer limit, Integer offset, final Callback<List<Facet>> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        FacetRequest facetRequest = new FacetRequest();
        facetRequest.setGroup_ids(groupIds);
        facetRequest.setLimit(limit);
        facetRequest.setOffset(offset);

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

        RequestWrapper<FacetTypesRequest> wrappedRequest = RequestWrapper.forType(FacetTypesRequest.class);
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

    public void requestAutocompletion(String searchString, Integer limit, List<Type> types, final Callback<Autocomplete> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }

        AutocompleteRequest autocompleteRequest = new AutocompleteRequest();
        autocompleteRequest.setSearchword(searchString);
        autocompleteRequest.setLimit(limit);
        autocompleteRequest.setTypes(types);

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

    public interface Callback<T> {
        public void onCompleted(T response);
        public void onError(String message); // TODO
    }
}
