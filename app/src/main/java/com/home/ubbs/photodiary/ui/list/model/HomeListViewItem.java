package com.home.ubbs.photodiary.ui.list.model;

import android.graphics.drawable.Drawable;

/**
 * Created by udyatbhanu-mac on 7/9/15.
 */
public class HomeListViewItem {

    public final Drawable icon;       // the drawable for the ListView item ImageView
    public final String title;        // the text for the ListView item title
    public final String description;  // the text for the ListView item description

    public HomeListViewItem(Drawable icon, String title, String description) {
        this.icon = icon;
        this.title = title;
        this.description = description;
    }
}
