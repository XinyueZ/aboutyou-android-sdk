package de.sliceanddice.maryandpaul.lib.internal.communication;

import de.sliceanddice.maryandpaul.lib.internal.response.AutocompleteResponse;
import de.sliceanddice.maryandpaul.lib.internal.response.BasketResponse;
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
import de.sliceanddice.maryandpaul.lib.request.BasketGetRequest;
import de.sliceanddice.maryandpaul.lib.request.CategoriesRequest;
import de.sliceanddice.maryandpaul.lib.request.CategoryTreeRequest;
import de.sliceanddice.maryandpaul.lib.request.FacetsRequest;
import de.sliceanddice.maryandpaul.lib.request.FacetTypesRequest;
import de.sliceanddice.maryandpaul.lib.request.ProductsRequest;
import de.sliceanddice.maryandpaul.lib.request.ProductSearchRequest;
import retrofit.http.Body;
import retrofit.http.POST;

public interface RestInterface {

    static final String BASEPATH = "/api";

    @POST(BASEPATH)
    ResponseEnvelope<CategoriesResponse> requestCategories(@Body RequestEnvelope<CategoriesRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<CategoryTreeResponse> requestCategoryTree(@Body RequestEnvelope<CategoryTreeRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<FacetsResponse> requestFacets(@Body RequestEnvelope<FacetsRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<FacetTypesResponse> requestFacetTypes(@Body RequestEnvelope<FacetTypesRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<AutocompleteResponse> requestAutocomplete(@Body RequestEnvelope<AutocompleteRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<ProductSearchResponse> requestProductSearch(@Body RequestEnvelope<ProductSearchRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<ProductsResponse> requestProducts(@Body RequestEnvelope<ProductsRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<BasketResponse> requestAddBasket(@Body RequestEnvelope<BasketAddRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<BasketResponse> requestGetBasket(@Body RequestEnvelope<BasketGetRequest> request);

}
