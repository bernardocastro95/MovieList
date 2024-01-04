package com.example.movielist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "MovieDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Movie";
    private static final String DATABASE_COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String MOVIE_TITLE = "title";
    private static final String MOVIE_DESCRIPTION = "desc";
    private static final String DELETED_FIELD = "deleted";

    private static final DateFormat dateFormat = new SimpleDateFormat("MM-DD-YYYY HH:MM:SS");
    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context){
        if(sqLiteManager == null){
            sqLiteManager = new SQLiteManager(context);

        }
        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(DATABASE_COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(MOVIE_TITLE)
                .append(" TEXT, ")
                .append(MOVIE_DESCRIPTION)
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT)");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*switch (oldVersion){
            case 1:
                db.execSQL("ALTER TABLE" + TABLE_NAME + "ADD COLUMN" + NEW_COLUMN + "TEXT");
        }*/
    }
    public void addMovieToDatabase(Movie movie){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, movie.getId());
        contentValues.put(MOVIE_TITLE, movie.getTitle());
        contentValues.put(MOVIE_DESCRIPTION, movie.getDescription());
        contentValues.put(DELETED_FIELD, getStringFromDate(movie.getDeleted()));

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void updateMovieInDatabase(Movie movie){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, movie.getId());
        contentValues.put(MOVIE_TITLE, movie.getTitle());
        contentValues.put(MOVIE_DESCRIPTION, movie.getDescription());
        contentValues.put(DELETED_FIELD, getStringFromDate(movie.getDeleted()));

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + "=?", new String[]{String.valueOf(movie.getId())});

    }
    public void populateMovieListArray(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);){
            if (result.getCount() != 0) {
                while (result.moveToNext()){
                    int id = result.getInt(1);
                    String title = result.getString(2);
                    String description = result.getString(3);
                    String stringDeleted = result.getString(4);
                    Date deleted = getDateFromString(stringDeleted);
                    Movie movie = new Movie(id, title, description, deleted);
                    Movie.movieArrayList.add(movie);
                }
            }
        }
        catch (Exception e){

        }
    }

    private String getStringFromDate(Date deleted) {
        if(deleted == null){
            return null;
        }
        else {
            return dateFormat.format(deleted);
        }
    }
    private Date getDateFromString(String string){
        try {
            return dateFormat.parse(string);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }
}
