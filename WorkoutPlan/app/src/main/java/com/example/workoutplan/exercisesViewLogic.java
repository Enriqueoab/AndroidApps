package com.example.workoutplan;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class exercisesViewLogic extends Activity {

    public ExerciseManagerActivity crud;
    public SQLiteDatabase bd;
    public TextView commentView;
    public ListView exNameList;
    public ArrayList exerciseList;
    public TextView exerciseText;
    public int  counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises_view);

        crud=new ExerciseManagerActivity();
        Bundle bundle = getIntent().getExtras();

        String muscle = bundle.getString("muscle");
        bd=MainActivity.sql.getWritableDatabase();
        commentView = findViewById(R.id.commentView);
        exerciseText=findViewById(R.id.exerciseText);
        exNameList=findViewById(R.id.exNameList);

        exerciseList = Tools.lookForMuscle(muscle,bd);//ArrayList with the objects "Exercise" of a muscle
        exerciseText.setText(muscle+" exercises:");//Title of the activity by the muscle chos

        //Create the adapter with the data for the viewList
        ArrayAdapter adapter = new ArrayAdapter<String>(this.getApplicationContext(),android.R.layout.simple_list_item_1, exerciseList){

            //Override the getView method to get differents colors for each item in the list
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position % 2 == 0) {
                    view.setBackgroundColor(Color.WHITE);
                } else {
                    view.setBackgroundColor(Color.LTGRAY);
                }
                return view;
            }
        };
        exNameList.setAdapter(adapter);

        //Listener to show the comments of an exercise when the user choose an exercise from the list
        exNameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Handler h = new Handler();

                switch(counter) {
                    case 0: //first tap
                        counter++; //increase the counter
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                counter = 0;
                            }
                        }, 500); //set the counter to 0 after 0.5 seconds (500 milliseconds)

                        Exercise e = (Exercise) exerciseList.get(i);//Get the single clicked obj. in the listView
                        commentView.setText(String.valueOf(e.getComment()));
                        break;
                    case 1: //second tap
                        counter = 0;//reset the counter
                        Exercise e2 = (Exercise) exerciseList.get(i);//Get the double clicked obj. in the listView
                        openFormActivity(e2);
                        break;
                }
            }
        });

    }

    public void openFormActivity(Exercise ex) {

        Intent activity=new Intent(this, ExerciseManagerActivity.class);

        activity.putExtra("exerciseObj",ex);
        startActivity(activity);

    }



}
