package com.github.rovkinmax.githubclient.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.github.rovkinmax.githubclient.app.App;
import com.github.rovkinmax.githubclient.model.Repo;
import com.github.rovkinmax.githubclient.util.AuthUtil;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.http.GET;

/**
 * @author Rovkin Max
 */
public class MainApi {
    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String AUTH_HEADER_VALUE_FORMAT = "%s %s";
    private static final String AUTH_TOKEN_TYPE = "Token";
    private static RepoApi sRepoApi = Common.getRestAdapter()
            .setRequestInterceptor(buildRequestInterceptor())
            .build()
            .create(RepoApi.class);


    @NonNull
    private static RequestInterceptor buildRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                String authToken = AuthUtil.getToken(App.getInstance());
                if (!TextUtils.isEmpty(authToken)) {
                    String headerValue = String.format(AUTH_HEADER_VALUE_FORMAT, AUTH_TOKEN_TYPE, authToken);
                    request.addHeader(AUTH_HEADER_KEY, headerValue);
                }
            }
        };
    }

    public static List<Repo> repoList() {
        return sRepoApi.repoList();
    }

    private interface RepoApi {
        @GET("/user/repos")
        List<Repo> repoList();
    }
}
