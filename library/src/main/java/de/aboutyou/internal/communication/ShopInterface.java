package de.aboutyou.internal.communication;

import de.aboutyou.internal.response.AutocompleteResponse;
import de.aboutyou.internal.response.BasketResponse;
import de.aboutyou.internal.response.CategoriesResponse;
import de.aboutyou.internal.response.CategoryTreeResponse;
import de.aboutyou.internal.response.FacetTypesResponse;
import de.aboutyou.internal.response.FacetsResponse;
import de.aboutyou.internal.response.InitiateOrderResponse;
import de.aboutyou.internal.response.LiveVariantResponse;
import de.aboutyou.internal.response.ProductSearchResponse;
import de.aboutyou.internal.response.ProductsResponse;
import de.aboutyou.internal.response.SuggestResponse;
import de.aboutyou.internal.wrapper.RequestEnvelope;
import de.aboutyou.internal.wrapper.ResponseEnvelope;
import de.aboutyou.request.AutocompleteRequest;
import de.aboutyou.request.BasketGetRequest;
import de.aboutyou.request.BasketModifyRequest;
import de.aboutyou.request.CategoriesRequest;
import de.aboutyou.request.CategoryTreeRequest;
import de.aboutyou.request.FacetTypesRequest;
import de.aboutyou.request.FacetsRequest;
import de.aboutyou.request.InitiateOrderRequest;
import de.aboutyou.request.LiveVariantRequest;
import de.aboutyou.request.ProductSearchRequest;
import de.aboutyou.request.ProductsRequest;
import de.aboutyou.request.SuggestRequest;
import retrofit.http.Body;
import retrofit.http.POST;

public interface ShopInterface {

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
    ResponseEnvelope<SuggestResponse> requestSuggest(@Body RequestEnvelope<SuggestRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<ProductSearchResponse> requestProductSearch(@Body RequestEnvelope<ProductSearchRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<ProductsResponse> requestProducts(@Body RequestEnvelope<ProductsRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<BasketResponse> requestModifyBasket(@Body RequestEnvelope<BasketModifyRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<BasketResponse> requestGetBasket(@Body RequestEnvelope<BasketGetRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<InitiateOrderResponse> requestInitiateOrder(@Body RequestEnvelope<InitiateOrderRequest> request);

    @POST(BASEPATH)
    ResponseEnvelope<LiveVariantResponse> requestLiveVariants(@Body RequestEnvelope<LiveVariantRequest> request);

}
