package com.example.workoutplan;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Custom class to manage the data easily
 *
 * Implements Parcelable to be able to send the object
 * between activities
 * */
public class Exercise implements Parcelable {

         private int id;
         private String muscle;
         private String exercise;
         private int weight;
         private String equipment; //DEFAULT'Dumbell'
         private int sets; //DEFAULT 4
         private int reps; //DEFAULT 8
         private String comment;


         public Exercise(int id,String muscle, String exercise, int weight, String equipment, int sets, int reps, String comment) {
             this.id=id;
             this.muscle = muscle;
             this.exercise = exercise;
             this.weight = weight;
             this.equipment = equipment;
             this.sets = sets;
             this.reps = reps;
             this.comment = comment;
         }


    public int getId() {
             return id;
         }

         public void setId(int id) {
             this.id = id;
         }

         public String getMuscle() {
             return muscle;
         }

         public void setMuscle(String muscle) {
             this.muscle = muscle;
         }

         public String getExercise() {
             return exercise;
         }

         public void setExercise(String exercise) {
             this.exercise = exercise;
         }

         public int getWeight() {
             return weight;
         }

         public void setWeight(int weight) {
             this.weight = weight;
         }

         public String getEquipment() {
             return equipment;
         }

         public void setEquipment(String equipment) {

             if (equipment.trim() == "") {

                 this.equipment = "Dumbell";

             } else {

                 this.equipment = equipment;

             }
         }

         public int getSets() {
             return sets;
         }

         public void setSets(int sets) {
             this.sets = sets;
         }

         public int getReps() {
             return reps;
         }

         public void setReps(int reps) {
             this.reps = reps;
         }

         public String getComment() {
             return comment;
         }

         public void setComment(String comment) {
             this.comment = comment;
         }

         public String seeAll() {
             return "Exercise{" + "id=" + id + ", muscle=" + muscle + ", exercise=" + exercise + ", weight=" + weight + ", equipment=" + equipment + ", sets=" + sets + ", reps=" + reps + ", comment=" + comment + '}';
         }

         @Override
         public String toString() {
            return "Exercise name= " + exercise + "\nEquipment= " + equipment +"\nWeight= " + weight ;
         }

    protected Exercise(Parcel in) {
        id = in.readInt();
        muscle = in.readString();
        exercise = in.readString();
        weight = in.readInt();
        equipment = in.readString();
        sets = in.readInt();
        reps = in.readInt();
        comment = in.readString();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(muscle);
        dest.writeString(exercise);
        dest.writeInt(weight);
        dest.writeString(equipment);
        dest.writeInt(sets);
        dest.writeInt(reps);
        dest.writeString(comment);
    }
}
