package com.github.rovkinmax.githubclient.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.rovkinmax.githubclient.R;
import com.github.rovkinmax.githubclient.model.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gad on 29.01.2016.
 */
public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoHolder>{
    private List<Repo> mRepoList =new ArrayList<>();

    @Override
    public RepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        return new RepoHolder(inflater.inflate(R.layout.repo_layout,parent, false));
    }

    @Override
    public void onBindViewHolder(RepoHolder holder, int position) {
holder.bind(mRepoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }


    public class RepoHolder extends RecyclerView.ViewHolder {
        private TextView mNameView;
        public RepoHolder(View itemView) {
            super(itemView);
            mNameView = (TextView) itemView.findViewById(R.id.textView);
        }
public void bind (Repo repo){
    mNameView.setText(repo.getName());
}

    }
}
