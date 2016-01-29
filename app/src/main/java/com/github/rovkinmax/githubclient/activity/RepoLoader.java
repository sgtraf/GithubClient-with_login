package com.github.rovkinmax.githubclient.activity;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.github.rovkinmax.githubclient.api.MainApi;
import com.github.rovkinmax.githubclient.model.Repo;

import java.util.List;

/**
 * Created by gad on 29.01.2016.
 */
public class RepoLoader extends AsyncTaskLoader<List<Repo>> {
    public RepoLoader(Context context) {
        super(context);
    }

    @Override
    public List<Repo> loadInBackground() {
        return MainApi.repoList();
    }



}
