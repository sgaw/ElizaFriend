package com.playground.sgaw.sample.elizafriend.feed.feedItem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.playground.sgaw.sample.elizafriend.R;

/**
 * View holder for {@link FeedItem}
 */

public class FeedItemViewHolder extends RecyclerView.ViewHolder implements IFeedItem.IView {
    TextView txtItem;

    public FeedItemViewHolder(View itemView) {
        super(itemView);

        txtItem = (TextView) itemView.findViewById(R.id.txt_message);
    }

    @Override
    public void bindItem(FeedItem item) {
        txtItem.setText(item.message);
        if (item.sender == 0) {
            txtItem.setBackgroundResource(R.color.colorPrimaryDark);
        } else {
            txtItem.setBackgroundResource(R.color.colorAccent);
        }
    }
}
