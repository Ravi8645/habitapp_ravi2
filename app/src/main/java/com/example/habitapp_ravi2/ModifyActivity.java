package com.example.habitapp_ravi2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.habitapp_ravi2.data.MyDbHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        MyDbHandler db = new MyDbHandler(this);

        ArrayList<Habit> habits = new ArrayList<>();
        habits = db.getAllHabits();

        Habit.setModify(true);

        HabitAdapter hAdapter = new HabitAdapter(this, habits);

        ListView listView = findViewById(R.id.list_modify);
        listView.setAdapter(hAdapter);

        /*FloatingActionButton fab_delete = listView.findViewById(R.id.delete_btn);
        fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = listView.getPositionForView(v);
                Habit h = hAdapter.getItem(position);

                Log.d("ravis",h.getTitle()+" "+h.getDescription()+" "+h.getDate());
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Habit.setModify(false);
    }
}
