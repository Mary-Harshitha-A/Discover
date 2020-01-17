package com.example.discover;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Article;
import com.example.SourceDetails;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NewsfeedAdapter extends RecyclerView.Adapter<NewsfeedAdapter.ViewHolder> {

    String imgURL;
    private List<Article> mValues;
    public NewsfeedAdapter(List<Article> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_newsfeed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Article feedList =mValues.get(position);
        holder.title.setText(feedList.getTitle());
        holder.desc.setText(feedList.getDescription());
        holder.timestamp.setText(feedList.getPublishedAt());
        holder.source.setText(feedList.getSource().getName());
        imgURL = feedList.getUrlToImage();
        Picasso.get().load(imgURL).into(holder.imageView);
    }

    public void setData(java.util.List<Article> feedList){
        this.mValues = feedList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageView;
        public final TextView title;
        public final TextView desc;
        public final TextView source;
        public final TextView timestamp;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = view.findViewById(R.id.feedImage);
            title = view.findViewById(R.id.headline);
            desc = view.findViewById(R.id.desc);
            source = view.findViewById(R.id.source);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }
}
