package com.github.rovkinmax.githubclient.model;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.os.Parcel;

import com.github.rovkinmax.githubclient.BuildConfig;

/**
 * @author Rovkin Max
 */
@SuppressLint("ParcelCreator")
public class GitAccount extends Account {
    public GitAccount(String name) {
        super(name, BuildConfig.ACCOUNT_TYPE);
    }

    public GitAccount(Parcel in) {
        super(in);
    }
}
