package com.meredithbayne.jokelib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Joke {
    private List<String> jokeList;

    public Joke() {
        this.jokeList = new ArrayList<>();
        this.jokeList.add("How many tickles does it take to make a squid laugh?" +
                "\n\nTen-tickles");
        this.jokeList.add("Schrödinger’s cat walks into a bar...\n\n...and doesn't");
        this.jokeList.add("Wanna hear a joke about potassium?\n\nK.");
        this.jokeList.add("Why was Pavlov's hair so soft?\n\nClassical Conditioning");
        this.jokeList.add("How many ears does Mr. Spock have?" +
                "\n\nThe left ear, the right ear, and the final front ear");
    }

    public String tellAJoke() {
        Random random = new Random();
        return jokeList.get(random.nextInt(jokeList.size() - 1));
    }
}
