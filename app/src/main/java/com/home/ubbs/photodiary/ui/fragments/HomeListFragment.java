package com.home.ubbs.photodiary.ui.fragments;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.home.ubbs.photodiary.sql.PhotoDiarySQLHelper;
import com.home.ubbs.photodiary.sql.model.Album;
import com.home.ubbs.photodiary.ui.activities.GalleryActivity;
import com.home.ubbs.photodiary.ui.list.adapters.AlbumAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udyatbhanu-mac on 7/9/15.
 */
public class HomeListFragment extends ListFragment {

    private List<Album> mItems;        // ListView items list
    // database helper
    private PhotoDiarySQLHelper mDatabaseHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
//        mItems = new ArrayList<HomeListViewItem>();
//        Resources resources = getResources();
//
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 1"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 2"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 3"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 4"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 5"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 6"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 7"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 8"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 9"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 10"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 11"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 12"));  mItems.add(new HomeListViewItem(null, "Test title1", "description 13"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 14"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 15"));  mItems.add(new HomeListViewItem(null, "Test title1", "description 16"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 15"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 17"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 18"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 19"));
//        mItems.add(new HomeListViewItem(null, "Test title1", "description 20"));


        mItems = new ArrayList<Album>();

        // initialize the database helper
        mDatabaseHelper = new PhotoDiarySQLHelper(getActivity());
        Resources resources = getResources();


        // initialize the items list
        mItems = mDatabaseHelper.getAllItems();
        // initialize and set the list adapter
        setListAdapter(new AlbumAdapter(getActivity(), mItems));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
//        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
//        HomeListViewItem item = mItems.get(position);

        // do something
//        Toast.makeText(getActivity(), item.title, Toast.LENGTH_SHORT).show();


        Album album = mItems.get(position);

        Intent intent = new Intent(getActivity(), GalleryActivity.class);
        intent.putExtra("albumTitle",album.getTitle());
        startActivity(intent);
        getActivity().overridePendingTransition(ubbs.home.com.core.lib.R.animator.slide_in_left,
                ubbs.home.com.core.lib.R.animator.slide_out_left);
    }
}
