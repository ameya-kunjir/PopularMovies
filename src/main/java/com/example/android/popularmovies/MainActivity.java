package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static GridView gridView;
    private static ArrayList<MovieBean> movieDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.movie_poster_grid_view);
    }

    @Override
    public void onStart() {
        super.onStart();
        FetchMovieDataTask movieData = new FetchMovieDataTask();
        movieData.execute();
    }

    public void setNewImageAdapter(ArrayList<MovieBean> lstMovieBeans)
    {
        gridView.setAdapter(new ImageAdapter(gridView.getContext() , lstMovieBeans));
        gridView.setOnItemClickListener(this);
        movieDataList = lstMovieBeans;
    }


   @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    {
        MovieBean mvBean = movieDataList.get(position);
        Intent intent = new Intent(MainActivity.this , MovieDetailsActivity.class);
        intent.putExtra("movieTitle", mvBean.getTitle());
        intent.putExtra("posterPath" , mvBean.getPosterPath() );
        intent.putExtra("releaseDate" , mvBean.getReleaseDate().toString());
        intent.putExtra("userRating" , mvBean.getUserRating());
        intent.putExtra("movieOverview" , mvBean.getOverviewOfMovie());
        startActivity(intent);

    }

}
