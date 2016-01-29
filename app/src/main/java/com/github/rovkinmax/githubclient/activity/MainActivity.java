package com.github.rovkinmax.githubclient.activity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.rovkinmax.githubclient.R;
import com.github.rovkinmax.githubclient.model.Repo;

import java.util.List;

public class MainActivity extends BaseAuthActivity implements LoaderManager.LoaderCallbacks<List<Repo>> {
private final RepoListAdapter mAdapter = new RepoListAdapter();
    static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<Repo>> onCreateLoader(int id, Bundle args) {
       if (id==R.id.repo_list_loader) {
           return new RepoLoader(getApplicationContext());
       }else {
           return null;
       }
    }

    @Override
    public void onLoadFinished(Loader<List<Repo>> loader, List<Repo> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Repo>> loader) {

    }
}
