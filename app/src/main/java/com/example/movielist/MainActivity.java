package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView movieListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        //loadFromDBToMemory();
        setMovieAdapter();

    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateMovieListArray();

    }

    private void initWidget() {
        movieListView = findViewById(R.id.movieListView);
    }
    private void setMovieAdapter(){
        MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), Movie.movieArrayList);
        movieListView.setAdapter(movieAdapter);
    }

    public void newMovie(View view) {
        Intent newMovieIntent = new Intent(this, MovieDetailActivity.class);
        startActivity(newMovieIntent);
    }
}