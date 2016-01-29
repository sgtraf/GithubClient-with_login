package com.github.rovkinmax.githubclient.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.github.rovkinmax.githubclient.BuildConfig;

/**
 * @author Rovkin Max
 */
public class AuthUtil {

    public static String getToken(Context context) {
        Account account = getUserAccount(context);
        if (account != null) {
            return AccountManager.get(context).peekAuthToken(account, BuildConfig.ACCOUNT_TYPE);
        }
        return null;
    }

    public static Account getUserAccount(Context context) {
        AccountManager am = AccountManager.get(context);
        Account[] accounts = am.getAccountsByType(BuildConfig.ACCOUNT_TYPE);
        Account userAccount = null;
        if (accounts.length > 0) {
            userAccount = accounts[0];
        }
        return userAccount;
    }

    public static Account peekAccount(@NonNull AccountManager am, @NonNull String login) {
        final Account[] accounts = am.getAccountsByType(BuildConfig.ACCOUNT_TYPE);
        for (final Account account : accounts) {
            if (TextUtils.equals(login, account.name)) {
                return account;
            }
        }
        return null;
    }

    public static void logout(Context context) {
        Account account = AuthUtil.getUserAccount(context);
        if (account == null) {
            return;
        }
        AccountManager am = AccountManager.get(context);
        String token = am.peekAuthToken(account, account.type);
        am.invalidateAuthToken(account.type, token);
    }
}
