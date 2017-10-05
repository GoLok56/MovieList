package io.github.golok56.movielist.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.golok56.movielist.R;
import io.github.golok56.movielist.models.Movie;

/**
 * @author Satria Adi Putra
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context mContext;

    private List<Movie> mMovies;

    public MovieAdapter(Context context, List<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);

        holder.setMovieImage(movie.getImage(), movie.isHardCoded());
        holder.setMovieTitle(movie.getTitle());
        holder.setMovieYear(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

        private TextView mTvMovieTitle;
        private TextView mTvMovieYear;

        private ImageView mIvMovieImage;

        ViewHolder(View view) {
            super(view);

            mTvMovieTitle = view.findViewById(R.id.tv_item_list_movie_title);
            mTvMovieYear = view.findViewById(R.id.tv_item_list_movie_year);
            mIvMovieImage = view.findViewById(R.id.iv_item_list_movie_image);
        }

        void setMovieTitle(String title) {
            mTvMovieTitle.setText(title);
        }

        void setMovieYear(String year) {
            mTvMovieYear.setText(year);
        }

        void setMovieImage(String url, boolean isHardcoded) {
            if (isHardcoded) {
                Picasso.with(mContext)
                        .load(url)
                        .into(mIvMovieImage);
            } else {
                Picasso.with(mContext)
                        .load(IMAGE_BASE_URL + url)
                        .into(mIvMovieImage);
            }
        }

    }

}
