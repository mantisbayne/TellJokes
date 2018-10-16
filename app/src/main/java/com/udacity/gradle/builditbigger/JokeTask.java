package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

class JokeTask extends AsyncTask<String, Void, String> {
    private static JokeApi api;
    private JokeTaskCallback jokeTaskCallback;
    private static final String ROOT_URL = "http://10.0.2.2:8080/_ah/api/";

    JokeTask(JokeTaskCallback callback) {
        this.jokeTaskCallback = callback;
    }

    @Override
    protected String doInBackground(String... param) {
        if (api == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(ROOT_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            api = builder.build();
        }

        try {
            return api.getJoke().execute().getJoke();
        } catch (IOException e) {
            Log.e("JokeTask", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (jokeTaskCallback != null) {
            jokeTaskCallback.onResult(result);
        }
    }

    interface JokeTaskCallback {
        void onResult(String result);
    }
}
