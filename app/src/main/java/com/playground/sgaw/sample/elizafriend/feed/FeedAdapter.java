package com.playground.sgaw.sample.elizafriend.feed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playground.sgaw.sample.elizafriend.R;
import com.playground.sgaw.sample.elizafriend.feed.feedItem.FeedItemViewHolder;

/**
 * Created by sgaw on 18/06/16.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedItemViewHolder> implements IFeed.IPresenter {
    private final IFeed.IDataProvider dataProvider;

    public FeedAdapter(IFeed.IDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public FeedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed_item, parent, false);
        return new FeedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedItemViewHolder holder, int position) {
        holder.bindItem(dataProvider.getFeedItem(position));
    }

    @Override
    public int getItemCount() {
        return dataProvider.getFeedSize();
    }

    @Override
    public void postFeedItem() {
        notifyItemChanged(dataProvider.getFeedSize() - 1);
    }
}
