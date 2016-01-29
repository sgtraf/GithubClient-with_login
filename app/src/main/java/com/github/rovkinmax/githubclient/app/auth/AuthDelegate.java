package com.github.rovkinmax.githubclient.app.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.github.rovkinmax.githubclient.model.GitAccount;
import com.github.rovkinmax.githubclient.util.AuthUtil;

import java.io.IOException;

/**
 * @author Rovkin Max
 */
public class AuthDelegate {
    private AccountManager mAccountManager;
    private AccountManagerFuture<Bundle> mFuture;

    public AuthDelegate(Context context) {
        mAccountManager = AccountManager.get(context);
    }

    public void tryLogin(String login, String password, @NonNull final AuthCallback callback) {
        final Account account = getOrCreateAccount(login, password);
        final Bundle options = new Bundle();
        options.putString(AccountManager.KEY_PASSWORD, password);
        getToken(callback, account, options);
    }

    public void checkAuth(Account account, @NonNull AuthCallback callback) {
        getToken(callback, account, Bundle.EMPTY);
    }

    private void getToken(@NonNull final AuthCallback callback, final Account account, Bundle options) {
        mFuture = mAccountManager.getAuthToken(account, account.type, options, false, new AccountManagerCallback<Bundle>() {
            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    Bundle result = future.getResult();
                    if (result.containsKey(AccountManager.KEY_AUTHTOKEN)) {
                        String token = result.getString(AccountManager.KEY_AUTHTOKEN);
                        callback.onAuthSuccessful(account, token);

                    } else {
                        callback.onAuthFailed();
                    }
                } catch (OperationCanceledException | IOException | AuthenticatorException e) {
                    callback.onAuthFailed();
                }
            }
        }, null);
    }

    private Account getOrCreateAccount(String login, String password) {
        Account account = AuthUtil.peekAccount(mAccountManager, login);
        if (account == null) {
            account = new GitAccount(login);
            mAccountManager.addAccountExplicitly(account, password, Bundle.EMPTY);
        }
        return account;
    }

    public void cancel() {
        if (mFuture != null && !mFuture.isCancelled() && !mFuture.isDone()) {
            mFuture.cancel(true);
        }
    }

    public interface AuthCallback {
        void onAuthSuccessful(Account account, String token);

        void onAuthFailed();
    }
}
