package com.playground.sgaw.sample.elizafriend.user;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * View for user input.
 */
public class UserInputView implements IUserInput.IView, View.OnClickListener, TextView.OnEditorActionListener {
    private final EditText txtUserInput;
    private IUserInput.IPresenter presenter;

    public UserInputView(ImageButton btnSend, final EditText txtUserInput) {
        this.txtUserInput = txtUserInput;
        txtUserInput.setOnEditorActionListener(this);

        btnSend.setOnClickListener(this);

    }

    public void setPresenter(IUserInput.IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View view) {
        presenter.postMessage();
    }

    @Override
    public String getMessage() {
        return txtUserInput.getText().toString();
    }

    @Override
    public void clearMessage() {
        txtUserInput.setText("");
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            presenter.postMessage();
            return true;
        } else {
            presenter.blockResponse();
        }
        return false;
    }
}
