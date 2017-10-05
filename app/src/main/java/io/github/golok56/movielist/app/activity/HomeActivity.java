package io.github.golok56.movielist.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.golok56.movielist.R;
import io.github.golok56.movielist.app.adapter.MovieAdapter;
import io.github.golok56.movielist.models.Movie;
import io.github.golok56.movielist.models.MovieList;
import io.github.golok56.movielist.services.MovieDatabaseAPI;
import io.github.golok56.movielist.utility.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity implements Callback<MovieList> {

    public static final int NEW_MOVIE_CODE = 56;
    public static final String NEW_MOVIE_EXTRA = "movie";

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private TextView mTvNotFound;
    private RecyclerView mRvMovieList;
    private FloatingActionButton mFabAddMovie;
    private ProgressBar mPbProgress;

    private PreferenceManager mPrefManager;

    private ArrayList<Movie> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTvNotFound = (TextView) findViewById(R.id.tv_activity_home_movie_not_found);
        mRvMovieList = (RecyclerView) findViewById(R.id.rv_activity_home_movie_list);
        mFabAddMovie = (FloatingActionButton) findViewById(R.id.fab_activity_home_add_new_movie);
        mPbProgress = (ProgressBar) findViewById(R.id.pb_activity_home_request_progress);

        mFabAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddMovieActivity.class);
                startActivityForResult(intent, NEW_MOVIE_CODE);
            }
        });

        mPrefManager = new PreferenceManager(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieDatabaseAPI apiService = retrofit.create(MovieDatabaseAPI.class);

        Call<MovieList> call = apiService.loadMovies();
        call.enqueue(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_home_menu_logout:
                logout();
                return true;
        }
        return false;
    }

    @Override
    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
        if (response.isSuccessful()) {
            mMovies = response.body().getList();

            for (Movie movie : mMovies) {
                System.out.println(movie.toString());
            }

            mPbProgress.setVisibility(View.GONE);
            mRvMovieList.setVisibility(View.VISIBLE);
            mFabAddMovie.setVisibility(View.VISIBLE);

            mRvMovieList.setLayoutManager(new GridLayoutManager(this, 2));
            mRvMovieList.setAdapter(new MovieAdapter(this, mMovies));
        } else {
            mPbProgress.setVisibility(View.GONE);
            mTvNotFound.setVisibility(View.VISIBLE);
            System.out.println(response.errorBody());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == NEW_MOVIE_CODE && resultCode == RESULT_OK){
            Movie newMovie = data.getParcelableExtra(NEW_MOVIE_EXTRA);
            mMovies.add(0, newMovie);
            mRvMovieList.getAdapter().notifyItemInserted(0);
        }
    }

    @Override
    public void onFailure(Call<MovieList> call, Throwable t) {
        t.printStackTrace();
    }

    private void logout(){
        mPrefManager.setLogin(false);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
