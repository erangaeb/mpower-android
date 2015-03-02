package com.mpower.power.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.mpower.power.R;
import com.mpower.power.asynctasks.LoginAsyncTask;
import com.mpower.power.listeners.LoginTaskListener;
import com.mpower.power.pojos.responses.LoginResponse;
import com.mpower.power.utils.ActivityUtils;

/**
 * Activity class of login
 * Perform login related functions
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public class LoginActivity extends Activity implements View.OnClickListener, LoginTaskListener {

    // layout components
    private RelativeLayout loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initLayout();
    }

    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            actionLogin();
        }
    }

    @Override
    public void onPostLogin(LoginResponse loginResponse) {
        ActivityUtils.cancelProgressDialog();

        if (loginResponse.getStatusCode() == 200) {
            switchToHome();
        } else {
            // TODO display error message
        }
    }

    /**
     * Initialize layout components
     * Set click listeners of UI elements/buttons
     */
    private void initLayout() {
        loginButton = (RelativeLayout) findViewById(R.id.sign_in_button_panel);

        loginButton.setOnClickListener(LoginActivity.this);
    }

    /**
     * Login button action
     * Send authenticate request to backend service via LoginAsyncTask
     */
    private void actionLogin() {
        ActivityUtils.hideSoftKeyboard(LoginActivity.this);
        ActivityUtils.showProgressDialog(LoginActivity.this, "Signing in...");
        new LoginAsyncTask(LoginActivity.this).execute();
    }

    /**
     * Switch to home activity
     * This method will be call after successful login
     */
    private void switchToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        this.startActivity(intent);
        LoginActivity.this.overridePendingTransition(R.anim.right_in, R.anim.left_out);

        LoginActivity.this.finish();
    }

}
