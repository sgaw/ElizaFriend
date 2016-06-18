package com.playground.sgaw.sample.elizafriend.feed.feedItem;

/**
 * Interface for view-presenter for items in a feed.
 */
public interface IFeedItem {
    interface IView {
        void bindItem(FeedItem item);
    }
}
