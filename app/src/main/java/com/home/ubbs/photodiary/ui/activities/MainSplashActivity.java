package com.home.ubbs.photodiary.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.home.ubbs.photodiary.R;

import ubbs.home.com.core.lib.ui.activity.UBBSBaseActivity;

/**
 * Created by udyatbhanu-mac on 7/11/15.
 */
public class MainSplashActivity extends UBBSBaseActivity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
//    ProgressDialog progressDialog = null;
    ProgressBar spinner = null;
    @Override
    public void onCreate(Bundle bundle) {
    super.onCreate(bundle);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_splash);
//        spinner = (ProgressBar)findViewById(R.id.progressBar1);
//        spinner.setVisibility(View.VISIBLE);
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                Intent mainIntent = new Intent(MainSplashActivity.this, MainPhotoDiaryActivity.class);
                MainSplashActivity.this.startActivity(mainIntent);
                MainSplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
