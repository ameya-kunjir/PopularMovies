package com.example.android.popularmovies;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shankar on 6/26/2016.
 */
public class ImageAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<MovieBean> lstMovieBean;
    private final String baseUrl = "http://image.tmdb.org/t/p/w185//";

    public ImageAdapter(Context c , ArrayList<MovieBean> lstMovieBean) {
        this.context = c;
        this.lstMovieBean = lstMovieBean;
    }

    public int getCount() {
        return lstMovieBean.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    /*public View getView(int position, View convertView, ViewGroup parent) {

        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        ImageView imageView;
        if(convertView == null)
        {
            if(context != null)
            {
                gridView = new View(context);
            *//*gridView = inflater.inflate(R.layout.gridview_item_layout, null);

            imageView = (ImageView) gridView.findViewById(R.id.movie_poster);
*//*          imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
                Picasso.with(context).load(baseUrl + lstMovieBean.get(position).getPosterPath().substring(2)).into(imageView);
            }

        }
        else {
            imageView = (ImageView) convertView;
            Picasso.with(context).load(baseUrl + lstMovieBean.get(position).getPosterPath().substring(2)).into(imageView);
        }


        return imageView;
    }*/


    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            int width = windowManager.getDefaultDisplay().getWidth();
            int height = windowManager.getDefaultDisplay().getHeight();
            Log.i("Screen size ", "Width" + width + "height" + height);
            imageView.setLayoutParams(new GridView.LayoutParams(width/2, height/2));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundColor(Color.BLACK);

        } else {
            imageView = (ImageView) convertView;
        }
        //Log.i("Popular Movies", baseUrl + lstMovieBean.get(position).getPosterPath().substring(1));
        Picasso.with(context).load(baseUrl + lstMovieBean.get(position).getPosterPath().substring(1)).into(imageView);
       // Picasso.with(context).load("http://image.tmdb.org/t/p/w185//8SqBiesvo1rh9P1hbJTmnVum6jv.jpg").into(imageView);
        return imageView;
    }
}
