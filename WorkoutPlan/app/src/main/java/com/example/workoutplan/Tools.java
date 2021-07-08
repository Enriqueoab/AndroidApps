package com.example.workoutplan;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Tools {

    public SQLiteDatabase bd;

        //Method to get all the exercises by muscle
        public static ArrayList lookForMuscle(String muscle,SQLiteDatabase bd) {

            String respuesta="";
            int column=1;
            ArrayList exercisesList = new ArrayList();
            Exercise exercise;
            //Cursor puntero = bd.rawQuery("select exercise,weight,equipment from workoutsDB where muscle= "+muscle+" and id= 1", null);
            Cursor puntero = bd.rawQuery("select * from workoutsDB where muscle = '"+muscle+"'", null);
            //puntero.moveToFirst();
            /*id ,muscle,exercise,weight,equipment,sets,reps,comment */
            while (puntero.moveToNext()) {

                exercise=new Exercise(Integer.parseInt(puntero.getString(puntero.getColumnIndex("id"))),
                        puntero.getString(puntero.getColumnIndex("muscle")),
                        puntero.getString(puntero.getColumnIndex("exercise")),
                        Integer.parseInt(puntero.getString(puntero.getColumnIndex("weight"))),
                        puntero.getString(puntero.getColumnIndex("equipment")),
                        Integer.parseInt(puntero.getString(puntero.getColumnIndex("sets"))),
                        Integer.parseInt(puntero.getString(puntero.getColumnIndex("reps"))),
                        puntero.getString(puntero.getColumnIndex("comment")));

                exercisesList.add(exercise);
                column++;
            }
            return exercisesList;

        }

    //Method to check if the data we are going to save in the database is right (Integer values)
    public static int checkSetsRepsData(String dataName,String intValue) {

        /*id ,muscle,exercise,weight,equipment,sets,reps,comment*/
        int value=0;

        if (intValue.trim().isEmpty()&&dataName.equalsIgnoreCase("sets")){
            value=4;
        }else if(intValue.trim().isEmpty()&&dataName.equalsIgnoreCase("reps")){
            value=8;
        }else if(Integer.parseInt(intValue)==0&&dataName.equalsIgnoreCase("sets")){
            value=4;
        }else if(Integer.parseInt(intValue)==0&&dataName.equalsIgnoreCase("reps")){
            value=8;
        }else{
            value=Integer.parseInt(intValue);

        }

        return value;

    }

    public static boolean checkDeleteExercise(int exerciseId,SQLiteDatabase bd) {

        Boolean answer=false;
        Cursor puntero = bd.rawQuery("select * from workoutsDB where id = '"+exerciseId+"'", null);

        if (!puntero.moveToNext()) {

            answer=true;

        }

        return answer;

    }


    }


