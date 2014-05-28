package de.aboutyou.sample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.aboutyou.ShopApiClient;
import de.aboutyou.enums.AuthScope;
import de.aboutyou.enums.AuthenticationRequestMode;
import de.aboutyou.models.ShopUser;

public class OAuthFragment extends Fragment {

    private ShopApiClient mShopApiClient;
    private ProgressDialog mSpinner;

    @InjectView(R.id.hello)
    TextView mHello;

    public static OAuthFragment newInstance() {
        return new OAuthFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_oauth, container, false);

        ButterKnife.inject(this, rootView);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivity mainActivity = ((MainActivity) activity);
        mainActivity.onFragmentAttached(getString(R.string.title_oauth));
        mShopApiClient = mainActivity.getShopApiClient();
    }

    @OnClick(R.id.auth)
    public void doAuth() {
        ShopApiClient.AuthenticationCallback callback = new ShopApiClient.AuthenticationCallback() {
            @Override
            public void onSuccess(final String accessToken) {
                loadUser(accessToken);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "OAuth canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(getActivity(), "OAuth failed", Toast.LENGTH_SHORT).show();
            }
        };

        mShopApiClient.requestAuthentication(getActivity(), Arrays.asList(AuthScope.FIRSTNAME, AuthScope.LASTNAME, AuthScope.ID, AuthScope.EMAIL), AuthenticationRequestMode.DEFAULT, "http://mp.sdk/oauth", callback);
    }

    private void loadUser(final String accessToken) {
        new AsyncTask<Void, Void, ShopUser>(){

            @Override
            protected void onPreExecute() {
                mSpinner = ProgressDialog.show(getActivity(), "", "Loading user data...", true, false);
            }

            @Override
            protected ShopUser doInBackground(Void... params) {
                return mShopApiClient.requestShopUser(accessToken);
            }

            @Override
            protected void onPostExecute(ShopUser shopUser) {
                mSpinner.dismiss();
                mHello.setText(String.format("Hello %s!", shopUser.getFirstname()));
            }
        }.execute();
    }
}
