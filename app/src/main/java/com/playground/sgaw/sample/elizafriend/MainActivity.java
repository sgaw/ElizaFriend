package com.playground.sgaw.sample.elizafriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;

import com.playground.sgaw.sample.elizafriend.feed.Feed;
import com.playground.sgaw.sample.elizafriend.feed.FeedAdapter;
import com.playground.sgaw.sample.elizafriend.feed.FeedDataProvider;
import com.playground.sgaw.sample.elizafriend.user.UserInputPresenter;
import com.playground.sgaw.sample.elizafriend.user.UserInputView;

public class MainActivity extends AppCompatActivity {
    private UserInputPresenter userInputPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FeedDataProvider feedDataProvider = new FeedDataProvider();
        FeedAdapter adapter = new FeedAdapter(feedDataProvider);
        feedDataProvider.setPresenter(adapter);

        Feed feed = (Feed) findViewById(R.id.feed);
        feed.setAdapter(adapter);

        UserInputView userInputView = new UserInputView(
                (ImageButton) findViewById(R.id.btn_send),
                (EditText) findViewById(R.id.txt_user));
        UserInputPresenter userInputPresenter = new UserInputPresenter(userInputView,
                feedDataProvider);

        userInputView.setPresenter(userInputPresenter);
    }
}
