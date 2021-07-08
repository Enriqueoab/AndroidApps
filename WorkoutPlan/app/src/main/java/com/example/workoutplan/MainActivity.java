package com.example.workoutplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    //public SQLiteDatabase bd;
    public static DataBase sql;
    public String muscle;
    public ImageButton shouldersButton,legsButton,chestButton,bicepsButton,backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shouldersButton=findViewById(R.id.shouldersButton);
        legsButton=findViewById(R.id.legsButton);
        chestButton=findViewById(R.id.chestButton);
        bicepsButton=findViewById(R.id.bicepsButton);
        backButton=findViewById(R.id.backButton);
        muscle="";

        //ExerciseManagerActivity db=new ExerciseManagerActivity();
        try {

            sql = new DataBase(this, "workoutsDB", null, 1);

            //bd = sql.getWritableDatabase();

        } catch (Exception e) {

        }

        //Listeners to set the variable "muscle" once is click an ImageButton
        shouldersButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                //Set the button's appearance
                //button.setSelected(!button.isSelected());

                    muscle="Shoulders";

                openExercisesView(muscle);

            }

        });

        legsButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                //Set the button's appearance
                //button.setSelected(!button.isSelected());

                muscle="Legs";

                openExercisesView(muscle);

            }

        });

        chestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                //Set the button's appearance
                //button.setSelected(!button.isSelected());

                muscle="Chest";

                openExercisesView(muscle);

            }

        });


        bicepsButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                //Set the button's appearance
                //button.setSelected(!button.isSelected());

                muscle="Biceps and triceps";

                openExercisesView(muscle);

            }

        });

        backButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                //Set the button's appearance
                //button.setSelected(!button.isSelected());
                muscle="Back";
                openExercisesView(muscle);
            }
        });
    }
    //Open the activity made to register a new exercise
    public void openFormActivity(View vista) {

        Intent activity=new Intent(this, ExerciseManagerActivity.class);
        startActivity(activity);

    }


    public void openExercisesView(String muscle) {

        Intent activity=new Intent(this,exercisesViewLogic.class);

        Bundle b = new Bundle();

        b.putString("muscle",muscle);
        activity.putExtras(b);
        startActivity(activity);

    }

}