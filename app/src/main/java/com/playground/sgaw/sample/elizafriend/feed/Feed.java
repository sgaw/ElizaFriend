package com.playground.sgaw.sample.elizafriend.feed;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Feed for conversation between Eliza and user.
 */

public class Feed extends RecyclerView implements IFeed.IView {
    public Feed(Context context) {
        super(context);
    }

    public Feed(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Feed(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
