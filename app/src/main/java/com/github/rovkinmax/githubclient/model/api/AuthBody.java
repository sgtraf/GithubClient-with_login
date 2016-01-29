package com.github.rovkinmax.githubclient.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rovkin Max
 */
public class AuthBody {
    @SerializedName("client_secret")
    private String mClientSecret;

    @SerializedName("scopes")
    private List<String> mScopes = new ArrayList<>();

    public void setClientSecret(String clientSecret) {
        mClientSecret = clientSecret;
    }

    public void setScopes(List<String> scopes) {
        mScopes = scopes;
    }

    public void addScope(String scope) {
        mScopes.add(scope);
    }

    public void clearScopes() {
        mScopes.clear();
    }
}
