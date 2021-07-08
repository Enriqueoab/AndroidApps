package com.example.workoutplan;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(@Nullable MainActivity context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE workout");
        db.execSQL("create table workoutsDB ( id integer primary key autoincrement,muscle text not null,exercise text not null,weight integer not null," +
               "equipment text DEFAULT 'Dumbell',sets integer DEFAULT 4,reps integer DEFAULT 8, comment text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}