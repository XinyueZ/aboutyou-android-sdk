package de.aboutyou.sample;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.aboutyou.ShopApiClient;
import de.aboutyou.enums.Dimension;
import de.aboutyou.enums.ProductFields;
import de.aboutyou.models.Product;
import de.aboutyou.models.Variant;
import de.aboutyou.request.ProductsRequest;

public class ProductDialogFragment extends DialogFragment {

    public static final String ARG_PRODUCT_ID = "product_id";
    public static final String ARG_PRODUCT_NAME = "product_name";

    private ShopApiClient mShopApiClient;

    private long mProductId;
    private String mProductName;
    private int mImageTargetPixelSize;

    @InjectView(R.id.image)
    ImageView mImage;

    @InjectView(R.id.desc)
    TextView mDesc;

    @InjectView(R.id.progress)
    ProgressBar mProgress;

    @InjectView(R.id.content)
    ViewGroup mContent;

    public static ProductDialogFragment newInstance(long productId, String productName) {
        ProductDialogFragment f = new ProductDialogFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PRODUCT_ID, productId);
        args.putString(ARG_PRODUCT_NAME, productName);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProductId = getArguments().getLong(ARG_PRODUCT_ID);
        mProductName = getArguments().getString(ARG_PRODUCT_NAME);

        mImageTargetPixelSize = getResources().getDimensionPixelSize(R.dimen.product_image_dimension);

        (new LoadProductTask()).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_product, container, false);

        ButterKnife.inject(this, view);

        getDialog().setTitle(mProductName);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mShopApiClient = ((MainActivity) activity).getShopApiClient();
    }

    private class LoadProductTask extends AsyncTask<Void, Void, Product> {

        @Override
        protected Product doInBackground(Void... params) {
            ProductsRequest productsRequest = new ProductsRequest.Builder()
                    .filterByProductIds(Arrays.asList(mProductId))
                    .listFields(Arrays.asList(ProductFields.DEFAULT_IMAGE, ProductFields.DESCRIPTION_LONG))
                    .build();

            List<Product> products = mShopApiClient.requestProducts(productsRequest);
            return products.get(0);
        }

        @Override
        protected void onPostExecute(Product product) {
            mProgress.setVisibility(View.GONE);
            mContent.setVisibility(View.VISIBLE);
            mDesc.setText(Html.fromHtml(product.getDescriptionLong()));
            Picasso.with(getActivity()).load(product.getImage().getUrl(Dimension.WIDTH, mImageTargetPixelSize)).into(mImage);
        }
    }
}
