package com.playground.sgaw.sample.elizafriend.user;

import com.playground.sgaw.sample.elizafriend.feed.IFeed;

import static android.R.id.message;

/**
 * Presenter for user interaction in the conversation stream.
 */

public class UserInputPresenter implements IUserInput.IPresenter {
    private final IUserInput.IView view;
    private final IFeed.IDataProvider feed;

    public UserInputPresenter(IUserInput.IView view, IFeed.IDataProvider feedDataProvider) {
        this.view = view;
        this.feed = feedDataProvider;
    }

    @Override
    public void postMessage() {
        String message = view.getMessage();
        view.clearMessage();

        if (!message.isEmpty()) {
            feed.postUserMessage(message.trim());
        }
    }
}
