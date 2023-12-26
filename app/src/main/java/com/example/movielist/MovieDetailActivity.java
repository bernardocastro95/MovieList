package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MovieDetailActivity extends AppCompatActivity {

    private EditText titleEditText, descEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initWidgets();
    }

    private void initWidgets() {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
    }

    public void saveNote(View view) {
        String title = String.valueOf(titleEditText.getText());
        String description = String.valueOf(descEditText.getText());

        int id = Movie.movieArrayList.size();
        Movie newMovie = new Movie(id, title, description);
        Movie.movieArrayList.add(newMovie);
        finish();
    }
}