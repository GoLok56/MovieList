package io.github.golok56.movielist.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Satria Adi Putra
 */
public class MovieList {

    @SerializedName("results")
    private ArrayList<Movie> mMovies;

    public ArrayList<Movie> getList(){
        return mMovies;
    }

}
