package com.playground.sgaw.sample.elizafriend.feed.feedItem;

/**
 * Domain object for a message shown in the feed.
 */
public class FeedItem {
    final String message;
    final int sender; // 0 == Eliza, 1 == user

    public FeedItem(String message, int sender) {
        this.message = message;
        this.sender = sender;
    }
}
