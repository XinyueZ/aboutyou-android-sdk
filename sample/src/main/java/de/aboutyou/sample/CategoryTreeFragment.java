package de.aboutyou.sample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.aboutyou.ShopApiClient;
import de.aboutyou.exceptions.CollinsException;
import de.aboutyou.models.Category;
import de.aboutyou.models.CategoryTree;
import de.aboutyou.request.CategoriesRequest;
import de.aboutyou.request.CategoryTreeRequest;

public class CategoryTreeFragment extends Fragment {

    private ShopApiClient mShopApiClient;
    private ProgressDialog mSpinner;

    @InjectView(android.R.id.list)
    ExpandableListView mCategoryList;

    public static CategoryTreeFragment newInstance() {
        return new CategoryTreeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categorytree, container, false);

        ButterKnife.inject(this, rootView);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivity mainActivity = ((MainActivity) activity);
        mainActivity.onFragmentAttached(getString(R.string.title_categorytree));
        mShopApiClient = mainActivity.getShopApiClient();
    }

    @OnClick(R.id.categorytree)
    public void categoryTree() {
        (new CategoryTreeTask()).execute();
    }

    private class CategoryTreeTask extends AsyncTask<Void, Void, CategoryTree> {

        @Override
        protected void onPreExecute() {
            mSpinner = ProgressDialog.show(getActivity(), "", "Loading...", true, false);
        }

        @Override
        protected CategoryTree doInBackground(Void... params) {
            try {
                CategoryTreeRequest categoryTreeRequest = new CategoryTreeRequest.Builder().build();

                return mShopApiClient.requestCategoryTree(categoryTreeRequest);
            } catch (CollinsException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(CategoryTree categoryTree) {
            mSpinner.dismiss();
            if (categoryTree != null) {
                mCategoryList.setAdapter(new CategoryTreeAdapter(getActivity(), categoryTree));
            } else {
                Toast.makeText(getActivity(), "Error requesting category tree.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class CategoryTreeAdapter extends BaseExpandableListAdapter {

        private final List<Category> mCategories;
        private final LayoutInflater mInflater;

        public CategoryTreeAdapter(Context context, CategoryTree categoryTree) {
            mCategories = categoryTree.getAllCategories();
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getGroupCount() {
            return mCategories.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mCategories.get(groupPosition).getAllSubCategories().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mCategories.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mCategories.get(groupPosition).getAllSubCategories().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return mCategories.get(groupPosition).getId();
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return mCategories.get(groupPosition).getAllSubCategories().get(childPosition).getId();
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.listitem_category_group, parent, false);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Category category = (Category) getGroup(groupPosition);
            viewHolder.title.setText(category.getName());

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.listitem_category, parent, false);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Category category = (Category) getChild(groupPosition, childPosition);
            viewHolder.title.setText(String.format("%s (%s childs)", category.getName(), category.getAllSubCategories().size()));

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
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
