package com.zybooks.thebanddatabase;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ListActivity extends AppCompatActivity implements ListFragment.OnBandSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check the FragmentManager for the list_fragment_container fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.list_fragment_container);

        // If the fragment does not exist, create it
        if (fragment == null) {
            fragment = new ListFragment();
            // Add the fragment to the FragmentManager's list and commit it
            fragmentManager.beginTransaction().add(R.id.list_fragment_container, fragment).commit();
        }
    }

    @Override
    public void onBandSelected(int bandId){
        // Sent the band ID of the clicked button to the details activity
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_BAND_ID, bandId);
        startActivity(intent);
    }
}