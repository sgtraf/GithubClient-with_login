package com.github.rovkinmax.githubclient.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * @author Rovkin Max
 */
public class Authorization {
    @SerializedName("id")
    private long mId;

    @SerializedName("token")
    private String mToken;

    @SerializedName("fingerprint")
    private String mFingerprint;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public String getFingerprint() {
        return mFingerprint;
    }

    public void setFingerprint(String fingerprint) {
        mFingerprint = fingerprint;
    }
}
