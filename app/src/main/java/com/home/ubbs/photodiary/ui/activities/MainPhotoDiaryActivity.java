package com.home.ubbs.photodiary.ui.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.home.ubbs.photodiary.R;

import ubbs.home.com.core.lib.ui.activity.UBBSSlidingMenuBaseActivity;

public class MainPhotoDiaryActivity  extends UBBSSlidingMenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            setUpMenu(R.raw.menu_config, savedInstanceState);



    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


}
