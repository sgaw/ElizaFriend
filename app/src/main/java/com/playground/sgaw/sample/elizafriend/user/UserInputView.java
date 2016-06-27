package com.playground.sgaw.sample.elizafriend.user;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * View for user input.
 */
public class UserInputView implements IUserInput.IView, View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {
    private final EditText txtUserInput;
    private IUserInput.IPresenter presenter;

    public UserInputView(ImageButton btnSend, final EditText txtUserInput) {
        this.txtUserInput = txtUserInput;
        txtUserInput.setOnEditorActionListener(this);
        txtUserInput.addTextChangedListener(this);
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
        String input = textView.getText().toString();
        if (input.isEmpty()) {
            return false;
        }

        if (actionId == EditorInfo.IME_ACTION_DONE ||
                (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            presenter.postMessage();
            return true;
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start,
                                  int before,
                                  int count) {
        // no-op
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start,
                              int before,
                              int count) {
        String changed = charSequence.subSequence(start, start + count).toString();
        if (!changed.isEmpty()) {
            presenter.blockResponse();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // no-op
    }
}
