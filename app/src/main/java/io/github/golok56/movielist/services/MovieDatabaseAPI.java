package io.github.golok56.movielist.services;

import io.github.golok56.movielist.models.MovieList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Satria Adi Putra
 */
public interface MovieDatabaseAPI {

    @GET("discover/movie?api_key=4e78e80b821817ef9eef946df7044d37&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    Call<MovieList> loadMovies();

}
