package com.zybooks.thebanddatabase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_BAND_ID = "bandId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment exists
        Fragment fragment = fragmentManager.findFragmentById(R.id.details_fragment_container);

        // If it does not exist, create it
        if(fragment == null) {
            // Use band ID from the ListFragment to instantiate DetailsFragment
            int bandId = getIntent().getIntExtra(EXTRA_BAND_ID, 1);
            fragment = DetailsFragment.newInstance(bandId);
            // Add the fragment to the FragmentManager and commit it
            fragmentManager.beginTransaction().add(R.id.details_fragment_container, fragment).commit();
        }

    }
}