package com.example.android.popularmovies;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;

public class MovieDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Bundle bundle = getIntent().getExtras();
        String movieTitle = bundle.getString("movieTitle");
        String movieOverview = bundle.getString("movieOverview");
        String releaseDate = bundle.getString("releaseDate");
        float userRating = bundle.getFloat("userRating");
        String posterPath = bundle.getString("posterPath");

        String posterFullPath = "http://image.tmdb.org/t/p/w185//"+ posterPath.substring(1);
        TextView textMovieTitle = (TextView) findViewById(R.id.movie_title);
        textMovieTitle.setText(movieTitle);

        TextView textMovieOverview = (TextView) findViewById(R.id.movie_synopsis);
        textMovieOverview.setText(movieOverview);

        TextView textMovieReleaseDate = (TextView) findViewById(R.id.release_date);
        textMovieReleaseDate.setText(releaseDate);

        ImageView imgMoviePoster = (ImageView) findViewById(R.id.movie_poster);
        Picasso.with(this).load(posterFullPath).into(imgMoviePoster);

        TextView textUserRating = (TextView)  findViewById(R.id.user_rating);
        textUserRating.setText(String.valueOf(userRating));


    }
}
