package com.udacity.gradle.builditbigger.backend;

import com.meredithbayne.jokelib.Joke;

public class JokeBean {

    private Joke joke;

    public JokeBean() {
        joke = new Joke();
    }

    public String getJoke() {
        return joke.tellAJoke();
    }
}