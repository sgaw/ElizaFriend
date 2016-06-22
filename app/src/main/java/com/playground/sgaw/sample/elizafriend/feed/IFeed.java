package com.playground.sgaw.sample.elizafriend.feed;

import com.playground.sgaw.sample.elizafriend.feed.feedItem.FeedItem;

/**
 * Interface for model-view-viewpresenter for conversation feed.
 */

public interface IFeed {
    interface IDataProvider {
        void postUserMessage(String message);
        int getFeedSize();
        FeedItem getFeedItem(int position);
        void blockResponse();
    }

    interface IView {

    }

    interface IPresenter {
        void postFeedItem();
    }
}
