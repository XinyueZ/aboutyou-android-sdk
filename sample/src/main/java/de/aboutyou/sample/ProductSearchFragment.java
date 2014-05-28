package de.aboutyou.sample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import de.aboutyou.ShopApiClient;
import de.aboutyou.exceptions.CollinsException;
import de.aboutyou.models.Product;
import de.aboutyou.models.ProductSearch;
import de.aboutyou.request.ProductSearchRequest;

public class ProductSearchFragment extends Fragment {

    private ShopApiClient mShopApiClient;
    private ProgressDialog mSpinner;

    @InjectView(R.id.searchword)
    EditText mSearchWord;

    @InjectView(android.R.id.list)
    ListView mProductList;

    public static ProductSearchFragment newInstance() {
        return new ProductSearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_productsearch, container, false);

        ButterKnife.inject(this, rootView);

        mSearchWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search();
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivity mainActivity = ((MainActivity) activity);
        mainActivity.onFragmentAttached(getString(R.string.title_productsearch));
        mShopApiClient = mainActivity.getShopApiClient();
    }

    @OnClick(R.id.search)
    public void search() {
        String searchWord = mSearchWord.getText().toString();
        if (searchWord.length() >= 3) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mSearchWord.getWindowToken(), 0);

            (new ProductSearchTask()).execute(searchWord);
        } else {
            Toast.makeText(getActivity(), "Searchword has to be at least 3 chars long.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnItemClick(android.R.id.list)
    public void onClick(int position) {
        Product product = ((ProductAdapter) mProductList.getAdapter()).getItem(position);
        product.getId();

        ProductDialogFragment productDialogFragment = ProductDialogFragment.newInstance(product.getId(), product.getName());
        productDialogFragment.show(getFragmentManager(), "product");
    }

    private String generateSessionId() {
        return "foobar";
    }

    private class ProductSearchTask extends AsyncTask<String, Void, ProductSearch> {

        @Override
        protected void onPreExecute() {
            mSpinner = ProgressDialog.show(getActivity(), "", "Loading...", true, false);
        }

        @Override
        protected ProductSearch doInBackground(String... params) {
            String searchWord = params[0];
            String sessionId = generateSessionId();

            ProductSearchRequest request = new ProductSearchRequest.Builder(sessionId)
                    .filterBySearchString(searchWord)
                    .limit(100)
                    .build();

            try {
                return mShopApiClient.requestProductSearch(request);
            } catch (CollinsException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(ProductSearch productSearch) {
            mSpinner.dismiss();
            if (productSearch != null) {
                if (productSearch.getProductCount() != 0) {
                    mProductList.setAdapter(new ProductAdapter(getActivity(), productSearch.getProducts()));
                } else {
                    Toast.makeText(getActivity(), "No matching products found.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Error requesting products.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class ProductAdapter extends ArrayAdapter<Product> {

        private LayoutInflater mInflater;

        public ProductAdapter(Context context, List<Product> objects) {
            super(context, 0, objects);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.listitem_product, parent, false);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Product product = getItem(position);
            viewHolder.title.setText(String.format("%s (%s)", product.getName(), product.getId()));

            return convertView;
        }

    }

    public static class ViewHolder {

        @InjectView(R.id.title)
        TextView title;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
