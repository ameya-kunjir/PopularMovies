package com.example.android.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shankar on 6/26/2016.
 */
public class FetchMovieDataTask extends AsyncTask<String, Void, ArrayList<MovieBean>> {

    private final static String LOG_TAG = "Poular Movies Stage :1";




    @Override
    protected ArrayList<MovieBean> doInBackground(String... str) {

        ArrayList<MovieBean> lsMovieBeans = null;
        String popMoviesApiResponseStr = null;
        try {
            popMoviesApiResponseStr = getPopularMoviesData();

            lsMovieBeans = parseJSONStringOfMovieData(popMoviesApiResponseStr);

        } catch (Exception ex) {
            Log.e(LOG_TAG, "Exception occured", ex);
        }

        return lsMovieBeans;
    }


    /*
     Return : response string from The Movie DB API
     Desc   : Creates URL for requesting Popular Movies and
              make HTTP request to Server and parses response
     */
    public String getPopularMoviesData() {
        HttpURLConnection urlConnection = null;
        InputStream inputStreamData = null;
        BufferedReader reader = null;
        String popularMoviesData = null;
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("api.themoviedb.org")
                    .appendPath("3")
                    .appendPath("movie")
                    .appendPath("popular")
                    .appendQueryParameter("api_key", "2608327415164fea54b06159ecd261a3");

            Log.e("Popular Movies", builder.toString());

            URL url = new URL(builder.build().toString());
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            inputStreamData = urlConnection.getInputStream();


            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStreamData));


            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }


            if (buffer.length() == 0) {
                popularMoviesData = null;
            }
            popularMoviesData = buffer.toString();

            Log.i(LOG_TAG, "JSON String " + popularMoviesData);


        } catch (MalformedURLException ex) {
            Log.e(LOG_TAG, "URL Malformation Exception Ouccured", ex);
        } catch (IOException ex) {
            Log.e(LOG_TAG, "Exception Ouccured while closing input stream", ex);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return popularMoviesData;
    }

    /*
        Return : ArrayList of MovieBean consisting of Response from API
        Desc   : Parses JSON Object of API response to extract useful
                 information
        */
    public ArrayList<MovieBean> parseJSONStringOfMovieData(String popularMoviesData) {
        ArrayList<MovieBean> lstMovieData = null;
        if (popularMoviesData != null) {
            try {
                JSONObject movieDataJson = new JSONObject(popularMoviesData);

                JSONArray movieDataArray = movieDataJson.getJSONArray("results");
                lstMovieData = new ArrayList<MovieBean>();
                for (int i = 0; i < movieDataArray.length(); i++) {
                    JSONObject movieData = movieDataArray.getJSONObject(i);
                    MovieBean objMovie = new MovieBean();
                    objMovie.setOverviewOfMovie(movieData.getString("overview"));
                    objMovie.setPosterPath(movieData.getString("poster_path"));

                    DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                    try {
                        Date relDate = df.parse(movieData.getString("release_date"));
                        objMovie.setReleaseDate(relDate);
                    } catch (ParseException ex) {
                        Log.e(LOG_TAG, "Error while parsing release date", ex);
                    }
                    objMovie.setTitle(movieData.getString("title"));
                    objMovie.setUserRating(Float.valueOf(movieData.getString("vote_average")));
                    lstMovieData.add(objMovie);
                }

            } catch (JSONException jsonException) {
                Log.e(LOG_TAG, "JSON Exception occured", jsonException);
            }
        }
        return lstMovieData;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieBean> lstMovieData) {
        Log.i("Popular Movies Logger" , "Async execute complete");
        new MainActivity().setNewImageAdapter(lstMovieData);
    }


}
