package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MovieDetailActivity extends AppCompatActivity {

    private EditText titleEditText, descEditText;
    private Movie selectedMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initWidgets();
        checkEditMovie();
    }

    private void initWidgets() {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
    }

    public void saveMovie(View view) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(titleEditText.getText());
        String description = String.valueOf(descEditText.getText());

        if(selectedMovie == null){
            int id = Movie.movieArrayList.size();
            Movie newMovie = new Movie(id, title, description);
            Movie.movieArrayList.add(newMovie);
            sqLiteManager.addMovieToDatabase(newMovie);

        }
        else {
            selectedMovie.setTitle(title);
            selectedMovie.setDescription(description);
            sqLiteManager.updateMovieInDatabase(selectedMovie);
        }
        finish();

    }
    private void checkEditMovie(){
        Intent previous = getIntent();
        int passMovieID = previous.getIntExtra(Movie.MOVIE_EDIT_EXTRA, -1);
        selectedMovie = Movie.getMovieForID(passMovieID);

        if(selectedMovie != null){
            titleEditText.setText(selectedMovie.getTitle());
            descEditText.setText(selectedMovie.getDescription());
        }
    }
}