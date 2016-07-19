package com.example.android.popularmovies;

import java.util.Date;

/**
 * Created by shankar on 6/26/2016.
 */
public class MovieBean {

    private String posterPath;
    private String overviewOfMovie;
    private String title;
    private float userRating;
    private Date releaseDate;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverviewOfMovie() {
        return overviewOfMovie;
    }

    public void setOverviewOfMovie(String overviewOfMovie) {
        this.overviewOfMovie = overviewOfMovie;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Title " + title);
        sb.append("Poster Path " + posterPath);
        sb.append("User Rating " + userRating);
        sb.append("Release Date " + releaseDate);

        return sb.toString();
    }
}

