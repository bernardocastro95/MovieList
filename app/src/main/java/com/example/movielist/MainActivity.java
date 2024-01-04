package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView movieListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        loadFromDBToMemory();
        setMovieAdapter();
        setOnClickListener();

    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateMovieListArray();

    }

    private void initWidget() {
        movieListView = findViewById(R.id.movieListView);
    }
    private void setMovieAdapter(){
        MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), Movie.nonDeletedMovie());
        movieListView.setAdapter(movieAdapter);
    }

    private void setOnClickListener(){
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie selectedMovie = (Movie) movieListView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), MovieDetailActivity.class);
                intent.putExtra(Movie.MOVIE_EDIT_EXTRA, selectedMovie.getId());
                startActivity(intent);
            }
        });
    }

    public void newMovie(View view) {
        Intent newMovieIntent = new Intent(this, MovieDetailActivity.class);
        startActivity(newMovieIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMovieAdapter();
    }
}