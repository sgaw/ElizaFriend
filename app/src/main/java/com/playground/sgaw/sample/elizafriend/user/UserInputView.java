package com.playground.sgaw.sample.elizafriend.user;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * View for user input.
 */
public class UserInputView implements IUserInput.IView, View.OnClickListener, View.OnKeyListener {
    private final EditText txtUserInput;
    private IUserInput.IPresenter presenter;

    public UserInputView(ImageButton btnSend, final EditText txtUserInput) {
        this.txtUserInput = txtUserInput;
        btnSend.setOnClickListener(this);
        txtUserInput.setOnKeyListener(this);
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
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == KeyEvent.ACTION_UP
            && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            presenter.postMessage();
            return true;
        } else {
            presenter.blockResponse();
        }
        return false;
    }
}
