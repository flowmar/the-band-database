package com.zybooks.thebanddatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ListFragment extends Fragment {

    // For the activity to implement
    public interface OnBandSelectedListener {
        void onBandSelected(int bandId);
    }

    // Reference to the activity
    private OnBandSelectedListener mListener;

    // View references
    private LinearLayout mListView;
    private Button mDeleteButton;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Get the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.band_recycler_view);
        // Set the LayoutManager of the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Create Dividers for items
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL);
        // Add them to the recyclerView
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Create an adapter to send bands to the RecyclerView
        BandAdapter adapter = new BandAdapter(BandDatabase.getInstance(getContext()).getBands());
        // Set the adapter on the RecyclerView
        recyclerView.setAdapter(adapter);

        return view;
    }

    private class BandHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Band mBand;

        private final TextView mNameTextView;

        // Constructor for BandHolder
        public BandHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_band, parent, false));
            // Set a click listener onto the band name
            itemView.setOnClickListener(this);
            mNameTextView = itemView.findViewById(R.id.bandName);
        }

        // Binds the band name to the TextView
        public void bind(Band band) {
            mBand = band;
            mNameTextView.setText(mBand.getName());
        }

        @Override
        public void onClick(View view) {
            // Tell the ListActivity which band was clicked
            mListener.onBandSelected(mBand.getId());
        }
    }


    private class BandAdapter extends RecyclerView.Adapter<BandHolder> {

        private final List<Band> mBands;

        public BandAdapter(List<Band> bands) {
            mBands = bands;
        }

        @Override
        public BandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new BandHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(BandHolder holder, int position) {
            Band band = mBands.get(position);

            holder.bind(band);
        }

        @Override
        public int getItemCount() {
            return mBands.size();
        }
    }



//        // Create the buttons using the band names and ids from BandDatabase
//        List<Band> bandList = BandDatabase.getInstance(getContext()).getBands();
//        for (int i = 0; i < bandList.size(); i++) {
//            Button button = new Button(getContext());
//            LinearLayout.LayoutParams layoutParams =
//                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(0, 0, 0,10);
//            button.setLayoutParams(layoutParams);
//
//        // Set the text to the band's name and tag to the band ID
//        Band band = BandDatabase.getInstance(getContext()).getBand(i+ 1);
//        button.setText(band.getName());
//        button.setTag(Integer.toString(band.getId()));
//
//        // All buttons have the same click listener
//            button.setOnClickListener(buttonClickListener);
//
//            // Add the button to the LinearLayout
//            layout.addView(button);
//        }
//        return view;
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnBandSelectedListener) {
            mListener = (OnBandSelectedListener) context;
        }
        else {
            throw new RuntimeException(context
                    + " must implement OnBandSelectedListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private final View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Start DetailsActivity
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            String bandId = (String) view.getTag();
            intent.putExtra(DetailsActivity.EXTRA_BAND_ID, Integer.parseInt(bandId));
            startActivity(intent);
        }
    };
}