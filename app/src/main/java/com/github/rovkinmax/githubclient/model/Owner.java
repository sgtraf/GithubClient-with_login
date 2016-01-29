package com.github.rovkinmax.githubclient.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Rovkin Max
 */
public class Owner extends RealmObject {
    @SerializedName("id")
    @PrimaryKey
    private long id;

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
