package com.playground.sgaw.sample.elizafriend.user;

/**
 * User input interface for model-view-viewpresenter.
 */

public interface IUserInput {
    interface IView {
        String getMessage();

        void clearMessage();
    }

    interface IPresenter {
        void postMessage();
    }
}
