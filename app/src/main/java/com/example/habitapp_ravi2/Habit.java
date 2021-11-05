package com.example.habitapp_ravi2;

public class Habit {

    private String mTitle;
    private String mDescription;
    private String mDate;

    static boolean modify = false; // default value for habit objects

    public Habit(String title, String desc, String date){
        mTitle = title;
        mDescription = desc;
        mDate = date;
    }

    public Habit(){}

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public static void setModify(boolean modify){Habit.modify = modify;}

    public static boolean getModify(){ return Habit.modify;}

}
