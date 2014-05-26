package de.aboutyou;

import org.junit.Test;

import de.aboutyou.models.CategoryTree;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CategoryTreeTest extends TestBase {

    @Test
    public void testSuccess() {
        ShopApiClient shopApiClient = getNewApiClient(new SuccessMockClient());

        CategoryTree categoryTree = shopApiClient.requestCategoryTree();

        assertNotNull(categoryTree);
        assertTrue(categoryTree.getAllCategories().size() == 2);
        assertTrue(categoryTree.getActiveCategories().size() == 1);
        assertTrue(categoryTree.getAllCategories().get(0).getName().equals("Damen"));
    }

    private class SuccessMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"category_tree\":[{name:\"Damen\",parent:null,sub_categories:[],active:true,position:1,id:1},{name:\"Herren\",parent:null,sub_categories:[],active:false,position:2,id:2}]}]";
        }

    }
}