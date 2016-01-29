package com.github.rovkinmax.githubclient.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.rovkinmax.githubclient.BuildConfig;
import com.github.rovkinmax.githubclient.R;
import com.github.rovkinmax.githubclient.app.auth.AuthDelegate;

public class SplashActivity extends AppCompatActivity {

    private AuthDelegate mAuthDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AccountManager accountManager = AccountManager.get(this);
        Account[] accounts = accountManager.getAccountsByType(BuildConfig.ACCOUNT_TYPE);
        mAuthDelegate = new AuthDelegate(this);
        if (accounts.length > 0) {
            mAuthDelegate.checkAuth(accounts[0], new AuthDelegate.AuthCallback() {
                @Override
                public void onAuthSuccessful(Account account, String token) {
                    openMainActivity();
                }

                @Override
                public void onAuthFailed() {
                    openLoginActivity();
                }
            });
        } else {
            openLoginActivity();
        }
    }

    private void openMainActivity() {
        MainActivity.start(this);
    }

    private void openLoginActivity() {
        LoginActivity.start(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuthDelegate.cancel();
    }
}
