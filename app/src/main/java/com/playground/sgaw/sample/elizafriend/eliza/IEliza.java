package com.playground.sgaw.sample.elizafriend.eliza;

/**
 * Interface for model-view-viewpresenter for Eliza (fake friend conversation).
 */

public interface IEliza {
    interface IDataProvider {
        void receiveMessage(String message);
        String getResponse();
    }

    interface IView {

    }

    interface IPresenter {

    }
}
