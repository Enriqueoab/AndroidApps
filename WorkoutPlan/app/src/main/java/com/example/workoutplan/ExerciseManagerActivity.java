package com.example.workoutplan;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExerciseManagerActivity extends Activity {

    public SQLiteDatabase bd;
    public Spinner spinnerMus, spinnerEq;
    public Exercise ex;
    public EditText exercise, weight, sets, reps, comment;
    public Button delete,update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the activity which is going to pick the data up
        setContentView(R.layout.data_manager);

        delete=findViewById(R.id.deleteButton);
        update=findViewById(R.id.updateButton);

        spinnerMus = (Spinner) findViewById(R.id.spinMus);
        spinnerEq = (Spinner) findViewById(R.id.spinEq);

        exercise = findViewById(R.id.editTextEx);
        weight = findViewById(R.id.editTextWei);
        sets = findViewById(R.id.editTextSets);
        reps = findViewById(R.id.editTextReps);
        comment = findViewById(R.id.editTextComment);

        bd = MainActivity.sql.getWritableDatabase();

        Intent intent=getIntent();
        ex=intent.getParcelableExtra("exerciseObj");

        //The buttons are able when a exercice object have been selected
        if (ex==null){

            delete.setEnabled(false);
            update.setEnabled(false);

        }else{

            spinnerEq.setSelection(getIndex(spinnerEq, ex.getEquipment()));
            spinnerMus.setSelection(getIndex(spinnerMus, ex.getMuscle()));
            exercise.setText(ex.getExercise());
            weight.setText(String.valueOf(ex.getWeight()));
            sets.setText(String.valueOf(ex.getSets()));
            reps.setText(String.valueOf(ex.getReps()));
            comment.setText(ex.getComment());
        }
    }

    //Method to get the index by the value of the listView
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

    public void addExercise(View vista) {

        ContentValues values = new ContentValues();
        if (spinnerMus.getSelectedItemPosition() != 0 && spinnerEq.getSelectedItemPosition() != 0) {
            if (!exercise.getText().toString().trim().isEmpty()&&!weight.getText().toString().trim().isEmpty()) {
                values.put("muscle", spinnerMus.getSelectedItem().toString());
                values.put("equipment", spinnerEq.getSelectedItem().toString());
                values.put("exercise", exercise.getText().toString());
                values.put("weight", Integer.parseInt(weight.getText().toString()));
                values.put("sets", Tools.checkSetsRepsData("sets", sets.getText().toString()));
                values.put("reps", Tools.checkSetsRepsData("reps", reps.getText().toString()));
                values.put("comment", comment.getText().toString());

                bd.insert("workoutsDB", null, values);
                toastMaker("Exercise save correctly!");

                spinnerMus.setSelection(0);
                spinnerEq.setSelection(0);
                exercise.setText("");
                weight.setText("");
                sets.setText("");
                reps.setText("");
                comment.setText("");

            }else{

                toastMaker("The fields exercise and weight are required");
            }
        } else {

            toastMaker("You have to select a muscle / equipment");
        }
    }

    public void updateExercise(View vista) {

        ContentValues values = new ContentValues();

        /*id ,muscle ,exercise ,weight ,equipment ,sets,reps ,Comment */
        if (spinnerMus.getSelectedItemPosition() != 0 && spinnerEq.getSelectedItemPosition() != 0) {
            if (!exercise.getText().toString().trim().isEmpty()&&!weight.getText().toString().trim().isEmpty()) {
                values.put("muscle", spinnerMus.getSelectedItem().toString());
                values.put("equipment", spinnerEq.getSelectedItem().toString());
                values.put("exercise", exercise.getText().toString());
                values.put("weight", Integer.parseInt(weight.getText().toString()));
                values.put("sets", Tools.checkSetsRepsData("sets", sets.getText().toString()));
                values.put("reps", Tools.checkSetsRepsData("reps", reps.getText().toString()));
                values.put("comment", comment.getText().toString());

                bd.update("workoutsDB", values,"id= '"+ex.getId()+"'",null);
                toastMaker("Exercise update correctly!");

            }else{

                toastMaker("The fields exercise and weight are required");
            }

        } else {

            toastMaker("You have to select a muscle / equipment");
        }
    }

    public void deleteExercise(View vista){

        bd.delete("workoutsDB","id= '"+ex.getId()+"'",null);
        if (Tools.checkDeleteExercise(ex.getId(),bd)){

            toastMaker("Exercise delete correctly");

        }else{
            toastMaker("Something went wrong");


        }
    }

    public void toastMaker(String mensaje) {

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

}
