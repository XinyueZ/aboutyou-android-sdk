package de.sliceanddice.maryandpaul.lib.internal.communication;

import de.sliceanddice.maryandpaul.lib.internal.response.AutocompleteResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.BasketAddResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.CategoriesResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.CategoryTreeResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.FacetTypesResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.FacetsResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.ProductSearchResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.ProductsResponse;
import de.sliceanddice.maryandpaul.lib.internal.wrapper.RequestEnvelope;
import de.sliceanddice.maryandpaul.lib.internal.wrapper.ResponseEnvelope;
import de.sliceanddice.maryandpaul.lib.request.AutocompleteRequest;
import de.sliceanddice.maryandpaul.lib.request.BasketAddRequest;
import de.sliceanddice.maryandpaul.lib.request.CategoriesRequest;
import de.sliceanddice.maryandpaul.lib.request.CategoryTreeRequest;
import de.sliceanddice.maryandpaul.lib.request.FacetsRequest;
import de.sliceanddice.maryandpaul.lib.request.FacetTypesRequest;
import de.sliceanddice.maryandpaul.lib.request.ProductsRequest;
import de.sliceanddice.maryandpaul.lib.request.ProductSearchRequest;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface RestInterface {

    static final String BASEPATH = "/api";

    @POST(BASEPATH)
    void requestCategories(@Body RequestEnvelope<CategoriesRequest> request, Callback<ResponseEnvelope<CategoriesResponse>> callback);

    @POST(BASEPATH)
    void requestCategoryTree(@Body RequestEnvelope<CategoryTreeRequest> request, Callback<ResponseEnvelope<CategoryTreeResponse>> callback);

    @POST(BASEPATH)
    ResponseEnvelope<CategoryTreeResponse> requestCategoryTree(@Body RequestEnvelope<CategoryTreeRequest> request);

    @POST(BASEPATH)
    void requestFacets(@Body RequestEnvelope<FacetsRequest> request, Callback<ResponseEnvelope<FacetsResponse>> callback);

    @POST(BASEPATH)
    void requestFacetTypes(@Body RequestEnvelope<FacetTypesRequest> request, Callback<ResponseEnvelope<FacetTypesResponse>> callback);

    @POST(BASEPATH)
    void requestAutocomplete(@Body RequestEnvelope<AutocompleteRequest> request, Callback<ResponseEnvelope<AutocompleteResponse>> callback);

    @POST(BASEPATH)
    void requestProductSearch(@Body RequestEnvelope<ProductSearchRequest> request, Callback<ResponseEnvelope<ProductSearchResponse>> callback);

    @POST(BASEPATH)
    void requestProducts(@Body RequestEnvelope<ProductsRequest> request, Callback<ResponseEnvelope<ProductsResponse>> callback);

    @POST(BASEPATH)
    void requestAddBasket(@Body RequestEnvelope<BasketAddRequest> request, Callback<ResponseEnvelope<BasketAddResponse>> callback);

}
