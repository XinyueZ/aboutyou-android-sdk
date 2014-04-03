package de.sliceanddice.maryandpaul.lib.communication;

import de.sliceanddice.maryandpaul.lib.requests.AutocompleteRequest;
import de.sliceanddice.maryandpaul.lib.requests.FacetTypesRequest;
import de.sliceanddice.maryandpaul.lib.wrapper.request.AutocompleteWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.request.CategoriesWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.request.CategoryTreeWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.request.FacetTypesWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.request.FacetsWrapper;
import de.sliceanddice.maryandpaul.lib.requests.CategoryRequest;
import de.sliceanddice.maryandpaul.lib.requests.CategoryTreeRequest;
import de.sliceanddice.maryandpaul.lib.requests.FacetRequest;
import de.sliceanddice.maryandpaul.lib.wrapper.RequestWrapper;
import de.sliceanddice.maryandpaul.lib.wrapper.ResponseWrapper;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface RestInterface {

    static final String BASEPATH = "/api";

    @POST(BASEPATH)
    void requestCategories(@Body RequestWrapper<CategoryRequest> request, Callback<ResponseWrapper<CategoriesWrapper>> callback);

    @POST(BASEPATH)
    void requestCategoryTree(@Body RequestWrapper<CategoryTreeRequest> request, Callback<ResponseWrapper<CategoryTreeWrapper>> callback);

    @POST(BASEPATH)
    void requestFacets(@Body RequestWrapper<FacetRequest> request, Callback<ResponseWrapper<FacetsWrapper>> callback);

    @POST(BASEPATH)
    void requestFacetTypes(@Body RequestWrapper<FacetTypesRequest> request, Callback<ResponseWrapper<FacetTypesWrapper>> callback);

    @POST(BASEPATH)
    void requestAutocomplete(@Body RequestWrapper<AutocompleteRequest> request, Callback<ResponseWrapper<AutocompleteWrapper>> callback);

}
