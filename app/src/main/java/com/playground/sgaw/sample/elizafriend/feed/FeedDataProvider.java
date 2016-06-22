package com.playground.sgaw.sample.elizafriend.feed;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.v4.util.CircularArray;

import com.playground.sgaw.sample.elizafriend.feed.feedItem.FeedItem;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by sgaw on 18/06/16.
 */

public class FeedDataProvider implements IFeed.IDataProvider {
    private IFeed.IPresenter presenter;
    private CircularArray<FeedItem> feedArray = new CircularArray<>();
    private Handler handler = null;

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
            handler = new Handler();
            handler.postDelayed(new PostElizaRunnable(this), 1000);
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

    @Override
    public void blockResponse() {
        handler.removeCallbacksAndMessages(null);
    }

    private static class PostElizaRunnable implements Runnable {
        private final WeakReference<FeedDataProvider> dataProviderRef;

        PostElizaRunnable(FeedDataProvider dataProvider) {
            this.dataProviderRef = new WeakReference<>(dataProvider);
        }

        @Override
        public void run() {
            android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            dataProviderRef.get().postElizaMessage();
        }
    }

    private void postElizaMessage() {
        FeedItem feedItem = new FeedItem("Eliza here", 0);
        feedArray.addLast(feedItem);
        if (presenter != null) {
            presenter.postFeedItem();
        }
    }
}
