package com.playground.sgaw.sample.elizafriend.feed;

import android.os.Handler;
import android.os.Process;
import android.support.v4.util.CircularArray;

import com.playground.sgaw.sample.elizafriend.feed.feedItem.FeedItem;

import java.lang.ref.WeakReference;

/**
 * Created by sgaw on 18/06/16.
 */

public class FeedDataProvider implements IFeed.IDataProvider {
    private static final String [] GREETINGS = {
            "hello",
            "hi",
            "hey",
            "heya"
    };

    private static final String [] ELIZA_MESSAGES = {
            "What other reasons might there be?",
            "Are you sure?",
            "Do you really think so?",
            "I see.",
            "Hmm...",
            "Yeah, I could see how that happens.",
            "Yeah, maybe...",
            "I understand.",
            "What does that suggest to you?",
            "Can you elaborate on that?",
            "That is quite interesting.",
            "Have you thought about it from the other person's perspective?",
            "I can see this has upset you. Do you want to talk about it some more?",
            "Sounds like you wanted a big change in that situation...",
            "Could you summarize this experience?",
            "So it sounds like maybe you were a little unhappy about all that."
    };
    private IFeed.IPresenter presenter;
    private CircularArray<FeedItem> feedArray = new CircularArray<>();
    private final Handler handler;

    public FeedDataProvider() {
        handler = new Handler();
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

            handler.postDelayed(new PostElizaRunnable(this, getSentiment(message)),
                    (int) (1000 + Math.random() * 5000));
        }
    }

    private Sentiment getSentiment(String message) {
        for (Sentiment sentiment: Sentiment.values()) {
            if (sentiment.isMatch(message)) {
                return sentiment;
            }
        }
        return Sentiment.NEUTRAL;
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

    @Override
    public String getElizaMessage(Sentiment sentiment) {
        if (Sentiment.GREETING == sentiment) {
            return "Hello!  How are you today? What would you like to discuss?";
        }
        return ELIZA_MESSAGES[(int) (Math.random() * ELIZA_MESSAGES.length)];
    }

    private void postElizaMessage(Sentiment sentiment) {
        String elizaMessage = getElizaMessage(sentiment);
        postElizaMessage(elizaMessage);
    }

    private void postElizaMessage(String elizaMessage) {
        FeedItem feedItem = new FeedItem(elizaMessage, 0);
        feedArray.addLast(feedItem);
        if (presenter != null) {
            presenter.postFeedItem();
        }
    }

    enum Sentiment {
        GREETING {
            @Override
            boolean isMatch(String message) {
                String[] tokens = message.toLowerCase().split("[,\\s]");
                for (int tokenI = 0; tokenI < tokens.length; tokenI++) {
                    String token = tokens[tokenI];
                    if (token.isEmpty()) {
                        continue;
                    }
                    for (int greetingsI = 0; greetingsI < GREETINGS.length; greetingsI++) {
                        if (token.equals(GREETINGS[greetingsI])) {
                            return true;
                        }
                    }
                }
                return false;
            }
        },
        NEUTRAL {
            @Override
            boolean isMatch(String message) {
                return false; // TODO(sgaw): refactor this
            }
        };

        abstract boolean isMatch(String message);
    }

    private static class PostElizaRunnable implements Runnable {
        private final WeakReference<FeedDataProvider> dataProviderRef;
        private final Sentiment sentiment;

        PostElizaRunnable(FeedDataProvider dataProvider, Sentiment sentiment) {
            this.dataProviderRef = new WeakReference<>(dataProvider);
            this.sentiment = sentiment;
        }

        @Override
        public void run() {
            android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            dataProviderRef.get().postElizaMessage(sentiment);
        }
    }

}
