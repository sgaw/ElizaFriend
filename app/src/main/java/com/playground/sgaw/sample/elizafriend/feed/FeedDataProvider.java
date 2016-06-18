package com.playground.sgaw.sample.elizafriend.feed;

import android.support.v4.util.CircularArray;

import com.playground.sgaw.sample.elizafriend.feed.feedItem.FeedItem;

/**
 * Created by sgaw on 18/06/16.
 */

public class FeedDataProvider implements IFeed.IDataProvider {
    IFeed.IPresenter presenter;
    CircularArray<FeedItem> feedArray = new CircularArray<>();

    public FeedDataProvider() {
    }

    public void setPresenter(IFeed.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void postUserMessage(String message) {
        FeedItem feedItem = new FeedItem(message, 1);
        feedArray.addLast(feedItem);
        if (presenter != null) {
            presenter.postFeedItem();
        }
    }

    @Override
    public int getFeedSize() {
        return feedArray.size();
    }

    @Override
    public FeedItem getFeedItem(int position) {
        return feedArray.get(position);
    }
}
