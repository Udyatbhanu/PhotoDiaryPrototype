package com.home.ubbs.photodiary.ui.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.home.ubbs.photodiary.R;

import ubbs.home.com.core.lib.utilities.CameraUtils;

/**
 * Created by udyatbhanu-mac on 8/9/15.
 */
public class GalleryImageFragment extends Fragment {

//    private static final String IMAGE_POSITION = "position";

    private  String imagePath;



    View rootView;
    ImageView imageView;
    Button shareButton;

    public static GalleryImageFragment newInstance(String path) {
        GalleryImageFragment fragment = new GalleryImageFragment(path);
        Bundle args = new Bundle();


        return fragment;
    }


    public GalleryImageFragment(){

    }
    public GalleryImageFragment(String path){
        this.imagePath = path;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {






        View rootView= inflater.inflate(R.layout.fragment_gallery, null);

        imageView = (ImageView)rootView.findViewById(R.id.gallery_image);
//        shareButton = (Button)rootView.findViewById(R.id.share_button);
        Bitmap bm = CameraUtils.decodeSampledBitmapFromUri(imagePath, 100, 220);
        Bitmap image = CameraUtils.rotateImageIfRequired(bm, imagePath);
        imageView.setImageBitmap(image);
//        shareButton.setBackgroundResource(R.drawable.splash);
//        imageView.setVisibility(View.GONE);

        return imageView;
    }


//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        if (imageView != null) {
//            ViewGroup parentViewGroup = (ViewGroup) imageView.getParent();
//            if (parentViewGroup != null) {
//                parentViewGroup.removeAllViews();
//            }
//        }
//    }
}
