package com.home.ubbs.photodiary.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.home.ubbs.photodiary.R;
import com.home.ubbs.photodiary.utilities.PhotoDiaryConstants;

import java.io.File;

import ubbs.home.com.core.lib.ui.UBBSButton;
import ubbs.home.com.core.lib.ui.activity.UBBSBaseActivity;
import ubbs.home.com.core.lib.utilities.CameraUtils;

/**
 * Created by udyatbhanu-mac on 8/3/15.
 */
public class CameraActivity extends UBBSBaseActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private File file;
    UBBSButton button;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_camera);

        Intent myIntent = getIntent();
        final String title = myIntent.getStringExtra("albumTitle");
        button = (UBBSButton)findViewById(R.id.camera_button);


        File[] imageFils = CameraUtils.getImages(PhotoDiaryConstants.PHOTO_ROOT_FOLDER + File.separator + title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                String albumLocation = PhotoDiaryConstants.PHOTO_ROOT_FOLDER+File.separator+title;
                file = CameraUtils.getOutputMediaFile(albumLocation, MEDIA_TYPE_IMAGE); // locate the  file to save the image
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file)); // set the image file name

                // start the image capture Intent
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);

    }
}
