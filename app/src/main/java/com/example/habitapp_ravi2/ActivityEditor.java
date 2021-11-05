package com.example.habitapp_ravi2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.habitapp_ravi2.data.MyDbHandler;

public class ActivityEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Habit habit = new Habit();

                EditText title = findViewById(R.id.edit_title);
                habit.setTitle(title.getText().toString());

                EditText desc = findViewById(R.id.edit_desc);
                habit.setDescription(desc.getText().toString());

                EditText date = findViewById(R.id.edit_date);
                habit.setDate(date.getText().toString());

                MyDbHandler db = new MyDbHandler(ActivityEditor.this);
                db.addHabit(habit);

                finish();
            }
        });
    }
}