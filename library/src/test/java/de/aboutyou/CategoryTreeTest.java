package de.aboutyou;

import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import de.aboutyou.models.CategoryTree;
import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CategoryTreeTest extends TestBase {

    @Test
    public void test() {
        ShopApiClient shopApiClient = getApiClient(new MockClient());

        CategoryTree categoryTree = shopApiClient.requestCategoryTree();

        assertNotNull(categoryTree);
        assertTrue(categoryTree.getAllCategories().size() == 2);
        assertTrue(categoryTree.getAllCategories().get(0).getName().equals("Damen"));
    }

    private class MockClient implements Client {

        private static final String RESPONSE = "[{\"category_tree\":[{name:\"Damen\",parent:null,sub_categories:[],active:true,position:1,id:16077},{name:\"Herren\",parent:null,sub_categories:[],active:true,position:2,id:16282}]}]";

        @Override
        public Response execute(Request request) throws IOException {
            return new Response(request.getUrl(), 200, "no reason", Collections.EMPTY_LIST, new TypedByteArray("application/json", RESPONSE.getBytes()));
        }
    }
}