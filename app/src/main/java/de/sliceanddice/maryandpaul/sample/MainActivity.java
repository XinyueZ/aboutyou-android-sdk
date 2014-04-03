package de.sliceanddice.maryandpaul.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import de.sliceanddice.maryandpaul.lib.ShopApiClient;
import de.sliceanddice.maryandpaul.lib.enums.Direction;
import de.sliceanddice.maryandpaul.lib.enums.FacetGroup;
import de.sliceanddice.maryandpaul.lib.enums.ProductFilter;
import de.sliceanddice.maryandpaul.lib.enums.Sortby;
import de.sliceanddice.maryandpaul.lib.enums.Type;
import de.sliceanddice.maryandpaul.lib.models.Autocomplete;
import de.sliceanddice.maryandpaul.lib.models.Category;
import de.sliceanddice.maryandpaul.lib.models.CategoryTree;
import de.sliceanddice.maryandpaul.lib.models.Facet;
import de.sliceanddice.maryandpaul.lib.models.ProductSearch;
import de.sliceanddice.maryandpaul.lib.requests.ProductSearchRequest;

public class MainActivity extends Activity {

    public static final String APP_ID = "110";
    public static final String APP_PASSWORD = "ed8272cc4d993378f595d112915920bb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ShopApiClient shopApiClient = new ShopApiClient(APP_ID, APP_PASSWORD);

        Button categoryTree = (Button) findViewById(R.id.category_tree);
        categoryTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopApiClient.requestCategoryTree(new ShopApiClient.Callback<CategoryTree>() {
                    @Override
                    public void onCompleted(CategoryTree response) {
                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });

        Button categories = (Button) findViewById(R.id.categories);
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopApiClient.requestCategories(Arrays.asList(19534l), new ShopApiClient.Callback<List<Category>>() {
                    @Override
                    public void onCompleted(List<Category> response) {
                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });

        Button facets = (Button) findViewById(R.id.facets);
        facets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopApiClient.requestFacets(Arrays.asList(FacetGroup.SIZE), null, null, new ShopApiClient.Callback<List<Facet>>() {
                    @Override
                    public void onCompleted(List<Facet> response) {
                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });

        Button facetTypes = (Button) findViewById(R.id.facettypes);
        facetTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopApiClient.requestFacetTypes(new ShopApiClient.Callback<List<FacetGroup>>() {
                    @Override
                    public void onCompleted(List<FacetGroup> response) {
                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });

        Button autocomplete = (Button) findViewById(R.id.autocomplete);
        autocomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopApiClient.requestAutocompletion("Sho", 10, Arrays.asList(Type.PRODUCTS), new ShopApiClient.Callback<Autocomplete>() {
                    @Override
                    public void onCompleted(Autocomplete response) {
                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });

        Button productsearch = (Button) findViewById(R.id.productsearch);
        productsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductSearchRequest request = new ProductSearchRequest.Builder("session4711")
                        .filterByMinPrice(500)
                        .filterByMaxPrice(5000)
                        .filterByStatus(ProductFilter.NONSALEONLY)
                        .sortBy(Sortby.RELEVANCE)
                        .sortDirection(Direction.DESC)
                        .limit(10)
                        .build();

                shopApiClient.requestProductSearch(request, new ShopApiClient.Callback<ProductSearch>() {
                    @Override
                    public void onCompleted(ProductSearch response) {
                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });
    }

}
