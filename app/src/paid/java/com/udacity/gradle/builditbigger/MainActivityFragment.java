package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.meredithbayne.telljokes.TellJokeActivity;

public class MainActivityFragment extends Fragment {
    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mProgressBar = root.findViewById(R.id.progressBar);

        root.findViewById(R.id.buttonTellJoke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);

                new JokeTask(new JokeTask.JokeTaskCallback() {
                    @Override
                    public void onResult(String result) {
                        mProgressBar.setVisibility(View.GONE);
                        if (result != null) {
                            displayJoke(result);
                        } else {
                            Toast.makeText(getActivity(), R.string.failed_load_joke,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).execute();
            }
        });
        return root;
    }

    private void displayJoke(String joke) {
        Intent intent = new Intent(getActivity(), TellJokeActivity.class);
        intent.putExtra(TellJokeActivity.EXTRA_JOKE, joke);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }
}
