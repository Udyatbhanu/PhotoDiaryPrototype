package com.home.ubbs.photodiary.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.home.ubbs.photodiary.R;
import com.home.ubbs.photodiary.ui.fragments.GalleryImageFragment;
import com.home.ubbs.photodiary.utilities.PhotoDiaryConstants;

import java.io.File;
import java.util.ArrayList;

import ubbs.home.com.core.lib.ui.activity.UBBSBaseActivity;
import ubbs.home.com.core.lib.utilities.CameraUtils;

/**
 * Created by udyatbhanu-mac on 8/9/15.
 */
public class GalleryActivity extends UBBSBaseActivity implements ViewPager.OnPageChangeListener{
    ArrayList<String> imageList = new ArrayList<String>();
    private  String title;
    ImageButton backButton;
    private  File[] imageFiles;
    private int currentPosition = 0;
    private ViewPager galleryPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);

        View mActionBarView = getLayoutInflater().inflate(R.layout.my_action_bar, null);
        actionBar.setCustomView(mActionBarView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        backButton = (ImageButton)findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(ubbs.home.com.core.lib.R.animator.slide_in_right,
                        ubbs.home.com.core.lib.R.animator.slide_out_right);
            }
        });

        galleryPager = (ViewPager) findViewById(R.id.gallery1);

        Intent myIntent = getIntent();
        title = myIntent.getStringExtra("albumTitle");

        imageFiles = CameraUtils.getImages(PhotoDiaryConstants.PHOTO_ROOT_FOLDER + File.separator + title);

        // Put the images in an ArrayList
        for (File file : imageFiles){
            imageList.add(file.getAbsolutePath());
        }

        GalleryImageAdapter galleryImageAdapter = new GalleryImageAdapter(getSupportFragmentManager());
        galleryPager.setAdapter(galleryImageAdapter);

        galleryPager.setOffscreenPageLimit(imageList.size());
        galleryPager.addOnPageChangeListener(this);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_camera) {
            Intent intent = new Intent(this, CameraActivity.class);
            intent.putExtra("albumTitle",title);
            startActivity(intent);
//            overridePendingTransition(ubbs.home.com.core.lib.R.animator.slide_in_left,
//                    ubbs.home.com.core.lib.R.animator.slide_out_left);

            overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_top);
            return true;
        }

        if (id == R.id.action_delete) {

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:



                          File currentImage = imageFiles[currentPosition];
                            CameraUtils.deleteImage(currentImage);

                            imageList.remove(currentPosition);
//                            galleryPager.removeAllViews();
//
//                            galleryPager.getAdapter().getCount();
//                            galleryPager.setOffscreenPageLimit(imageList.size());
                            galleryPager.getAdapter().notifyDataSetChanged();


//                            galleryPager.removeAllViews();

//                            galleryPager.getAdapter().destroyItem(galleryPager, currentPosition,null);









//                            galleryPager.getAdapter().notifyDataSetChanged();



//                            galleryPager.setOffscreenPageLimit(imageList.size());

//                            galleryPager.removeAllViews();
//                            imageList.remove(currentPosition);
//                            galleryPager.setOffscreenPageLimit(imageList.size());




                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.cancel();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
            builder.setCancelable(false);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
//        ImageView imageView = (ImageView) findViewById(R.id.image1);
//        Bitmap bm = CameraUtils.decodeSampledBitmapFromUri(imageList.get(position), 220, 400);
//
//        Bitmap image = CameraUtils.rotateImageIfRequired(bm,imageList.get(position));
//        imageView.setImageBitmap(image);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class GalleryImageAdapter extends FragmentPagerAdapter {



        public GalleryImageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override public Fragment getItem(int position) {
            return GalleryImageFragment.newInstance(imageList.get(position));
        }




        @Override
        public int getItemPosition(Object object){

            return PagerAdapter.POSITION_NONE;
        }

        @Override public int getCount() {
            return imageList.size();
        }

    }

    @Override
    public void onBackPressed(){
        finish();
        overridePendingTransition(ubbs.home.com.core.lib.R.animator.slide_in_right,
                ubbs.home.com.core.lib.R.animator.slide_out_right);

    }



//    public class ImageAdapter extends BaseAdapter {
//        private Context context;
//        private int itemBackground;
//        public ImageAdapter(Context c)
//        {
//            context = c;
//            // sets a grey background; wraps around the images
//            TypedArray a =obtainStyledAttributes(R.styleable.MyGallery);
//            itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
//            a.recycle();
//        }
//        // returns the number of images
//        public int getCount() {
//            return imageList.size();
//        }
//        // returns the ID of an item
//        public Object getItem(int position) {
//            return position;
//        }
//        // returns the ID of an item
//        public long getItemId(int position) {
//            return position;
//        }
//        // returns an ImageView view
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            ImageView imageView;
//            if (convertView == null) {
//                imageView = new ImageView(context);
//
//            }else{
//                imageView = (ImageView) convertView;
//            }
//            Bitmap bm = CameraUtils.decodeSampledBitmapFromUri(imageList.get(position), 100, 220);
//
//
//
//            Bitmap image = CameraUtils.rotateImageIfRequired(bm,imageList.get(position));
//            imageView.setImageBitmap(image);
//            return imageView;
//
//            }
//
//    }


}


