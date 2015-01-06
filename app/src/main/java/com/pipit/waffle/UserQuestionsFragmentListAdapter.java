package com.pipit.waffle;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kyle on 1/4/2015.
 */
public class UserQuestionsFragmentListAdapter extends RecyclerView.Adapter<UserQuestionsFragmentListAdapter.ViewHolder> {
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public View mLayoutView;
        public TextView image1;
        public TextView image2;
        public ViewHolderClicksListener mListener;
        public ViewHolder(View v, ViewHolderClicksListener listener) {
            super(v);
            mListener = listener;
            mLayoutView = v;
            image1 = (TextView) v.findViewById(R.id.image_prev1);
            image2 = (TextView) v.findViewById(R.id.image_prev2);
            image1.setOnClickListener(this);
            image2.setOnClickListener(this);
            // necessary?
            mLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v instanceof TextView){
                mListener.onTomato((TextView) v);
            }
            else {
                mListener.onPotato(v);
            }
        }

        public static interface ViewHolderClicksListener {
            public void onPotato(View caller);
            public void onTomato(TextView callerImage);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UserQuestionsFragmentListAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UserQuestionsFragmentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_questions_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        UserQuestionsFragmentListAdapter.ViewHolder.ViewHolderClicksListener l = new UserQuestionsFragmentListAdapter.ViewHolder.ViewHolderClicksListener() {
            public void onPotato(View caller) {
                Toast.makeText(caller.getContext(), "Layout Clicked!", Toast.LENGTH_SHORT).show();
            }


            public void onTomato(TextView callerImage) {
                Toast.makeText(callerImage.getContext(), "TextView Clicked!", Toast.LENGTH_SHORT).show();
            }


        };

        ViewHolder vh = new ViewHolder(v, l);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.image1.setText(mDataset[position]);
        holder.image2.setText(mDataset[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}