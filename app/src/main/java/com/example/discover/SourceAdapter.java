package com.example.discover;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.SourceDetails;

import java.util.List;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {

    private List<SourceDetails> mValues;

    public SourceAdapter(List<SourceDetails> items)
    {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_source, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SourceDetails sourceList =mValues.get(position);
        holder.name.setText(sourceList.getName());
        holder.place.setText(sourceList.getCountry());
        holder.locator.setText(sourceList.getUrl());
        holder.locator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.description.setText(sourceList.getDescription());
    }

    public void setData(java.util.List<SourceDetails> sourceList){
        this.mValues = sourceList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public final TextView place;
        public final TextView locator;
        public final TextView description;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name =  view.findViewById(R.id.source_name);
            place = view.findViewById(R.id.country);
            description = view.findViewById(R.id.description);
            locator = view.findViewById(R.id.url);
        }

    }


}
