package com.home.ubbs.photodiary.ui.fragments;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.home.ubbs.photodiary.R;
import com.home.ubbs.photodiary.sql.PhotoDiarySQLHelper;
import com.home.ubbs.photodiary.sql.model.Album;
import com.home.ubbs.photodiary.utilities.PhotoDiaryConstants;

import ubbs.home.com.core.lib.utilities.CameraUtils;

/**
 * Created by udyatbhanu-mac on 7/9/15.
 */
public class NewFragment extends Fragment {


    private Button createButton;
    private PhotoDiarySQLHelper travelDiarySQLHelper;

    ProgressDialog progressDialog = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new, container, false);

        final EditText titleText = (EditText)rootView.findViewById(R.id.input_title_text);
        final EditText locationText = (EditText)rootView.findViewById(R.id.input_location_text);
        final EditText descriptionText = (EditText)rootView.findViewById(R.id.input_description_text);
        createButton = (Button)rootView.findViewById(R.id.create_button);
        createButton.setEnabled(false);

        titleText.addTextChangedListener(titleTextWatcher);


        travelDiarySQLHelper = new PhotoDiarySQLHelper(rootView.getContext());
        progressDialog= new ProgressDialog(rootView.getContext());

        createButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Album album = new Album();
                album.setTitle(titleText.getText().toString());
                album.setLocation(locationText.getText().toString());
                album.setDescription(descriptionText.getText().toString());
                new SQLLoaderTask(album).execute();
            }
        });


        return rootView;
    }

    /**
     *
     */

   private final TextWatcher titleTextWatcher = new TextWatcher() {
       @Override
       public void beforeTextChanged(CharSequence s, int start, int count, int after) {

       }

       @Override
       public void onTextChanged(CharSequence s, int start, int before, int count) {

       }

       @Override
       public void afterTextChanged(Editable s) {
           if(s.length()<5){
               createButton.setEnabled(false);
           }else{
               createButton.setEnabled(true);
           }

       }
   };


    private class SQLLoaderTask extends AsyncTask<Void, Void, Uri> {
        private Album album;
      public SQLLoaderTask(Album travelAlbum){
          album = travelAlbum;

      }

        @Override
        protected void onPreExecute() {
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        // Decode image in background.
        @Override
        protected Uri doInBackground(Void... params) {

            String albumLocation = PhotoDiaryConstants.PHOTO_ROOT_FOLDER+"/"+album.getTitle();
            Uri uri = CameraUtils.getPhotoMediaUri(albumLocation);
            travelDiarySQLHelper.addAlbum(album);
           return uri;
        }

        @Override
        protected void onPostExecute(Uri uri) {
            progressDialog.dismiss();
            // do something

            Toast.makeText(getActivity(), "Data inserted successfully for "+ uri.toString(), Toast.LENGTH_LONG).show();
        }
    }


}
