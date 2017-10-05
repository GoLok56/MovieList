package io.github.golok56.movielist.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import io.github.golok56.movielist.R;
import io.github.golok56.movielist.models.Movie;

public class AddMovieActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mYear;
    private EditText mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        mTitle = (EditText) findViewById(R.id.et_activity_add_movie_title);
        mYear = (EditText) findViewById(R.id.et_activity_add_movie_year);
        mUrl = (EditText) findViewById(R.id.et_activity_add_movie_image);

        findViewById(R.id.btn_activity_add_movie_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitle.getText().toString();
                String year = mYear.getText().toString();
                String url = mUrl.getText().toString();

                if(TextUtils.isEmpty(title) || TextUtils.isEmpty(year) ||TextUtils.isEmpty(url)){
                    if(TextUtils.isEmpty(title)){
                        mTitle.setError(getString(R.string.should_not_be_empty_field));
                    }

                    if(TextUtils.isEmpty(year)){
                        mYear.setError(getString(R.string.should_not_be_empty_field));
                    }

                    if(TextUtils.isEmpty(url)){
                        mUrl.setError(getString(R.string.should_not_be_empty_field));
                    }

                    return;
                }

                Intent intent = new Intent(AddMovieActivity.this, HomeActivity.class);
                intent.putExtra(HomeActivity.NEW_MOVIE_EXTRA, new Movie(title, year, url, true));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}
