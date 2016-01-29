package com.github.rovkinmax.githubclient.api;

import android.util.Base64;

import com.github.rovkinmax.githubclient.model.api.AuthBody;
import com.github.rovkinmax.githubclient.model.api.Authorization;

import java.util.UUID;

import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * @author Rovkin Max
 */
public class AuthApi {
    private static final String CLIENT_ID = "2efdc771362ea85e13ba";
    private static final String CLIENT_SECRET = "3ec59bbe8de2bb13906a5f9b7a63d249aadae441";
    private static final String AUTH_TYPE_BASIC = "Basic";
    private static AuthService sAuthService = Common.getRestAdapter().build().create(AuthService.class);

    public static Authorization auth(String login, String pass) {
        final String credentials = Base64.encodeToString((login + ":" + pass).getBytes(), Base64.NO_WRAP);
        final String authHeader = String.format("%s %s", AUTH_TYPE_BASIC, credentials);
        final String fingerPrint = UUID.randomUUID().toString();
        final AuthBody body = new AuthBody();
        body.setClientSecret(CLIENT_SECRET);
        body.addScope("repo");
        return sAuthService.auth(CLIENT_ID, fingerPrint, authHeader, body);
    }




    private interface AuthService {
        @PUT("/authorizations/clients/{client_id}/{fingerprint}")
        @Headers("Content-type: application/json")
        Authorization auth(@Path("client_id") String clientId,
                           @Path("fingerprint") String fingerprint,
                           @Header("Authorization") String credentials,
                           @Body AuthBody authBody);
    }
}
