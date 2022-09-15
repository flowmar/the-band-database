package com.zybooks.thebanddatabase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    private Band mBand;

    public static DetailsFragment newInstance(int bandId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("bandId", bandId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the bandID from the intent that started the DetailsActivity
        int bandId = 1;
        if(getArguments() != null) {
            bandId = getArguments().getInt("bandId");
        }
        mBand = BandDatabase.getInstance(getContext()).getBand(bandId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // Get the name TextView and set the text
        TextView nameTextView = (TextView) view.findViewById(R.id.bandName);
        nameTextView.setText(mBand.getName());

        // Get the description TextView and set the text
        TextView descriptionTextView = (TextView) view.findViewById(R.id.bandDescription);
        descriptionTextView.setText(mBand.getDescription());

        return view;
    }
}