package com.github.rovkinmax.githubclient.activity;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rovkinmax.githubclient.R;
import com.github.rovkinmax.githubclient.app.auth.AuthDelegate;

/**
 * @author Rovkin Max
 */
public class LoginActivity extends AccountAuthenticatorActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginContentView;
    private AuthDelegate mAuthDelegate;

    static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.et_login);
        mPasswordView = (EditText) findViewById(R.id.password);
        final Button emailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mLoginContentView = findViewById(R.id.login_content);
        mProgressView = findViewById(R.id.login_progress);
        mAuthDelegate = new AuthDelegate(this);
        emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = mEmailView.getText().toString();
                String pass = mPasswordView.getText().toString();
                if (isValidCredentials(login, pass)) {
                    showProgress(true);
                    tryLogin(login, pass);
                } else {
                    showToast("Login or password is empty");
                }
            }
        });
    }

    private boolean isValidCredentials(String login, String pass) {
        return !TextUtils.isEmpty(login) && !TextUtils.isEmpty(pass);
    }

    private void tryLogin(String login, final String password) {
        mAuthDelegate.tryLogin(login, password, new AuthDelegate.AuthCallback() {
            @Override
            public void onAuthSuccessful(Account account, String token) {
                showProgress(false);
                saveResult(account, token);
                MainActivity.start(LoginActivity.this);
            }

            @Override
            public void onAuthFailed() {
                showProgress(false);
                showToast("Login failed");
            }
        });

    }

    private void saveResult(Account account, String token) {
        final Bundle result = new Bundle();
        result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
        result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
        result.putString(AccountManager.KEY_AUTHTOKEN, token);

        setAccountAuthenticatorResult(result);
        setResult(RESULT_OK);
    }

    private void showProgress(final boolean show) {
        mLoginContentView.setVisibility(show ? View.GONE : View.VISIBLE);
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuthDelegate.cancel();
    }
}

