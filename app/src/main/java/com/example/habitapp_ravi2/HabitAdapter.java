package com.example.habitapp_ravi2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.habitapp_ravi2.data.MyDbHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HabitAdapter extends ArrayAdapter<Habit> {
    public HabitAdapter(Context context, ArrayList<Habit> list) {
        super(context,0,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Habit habit = getItem(position);

        TextView habit_title = listItemView.findViewById(R.id.title);
        habit_title.setText(habit.getTitle());

        TextView habit_desc = listItemView.findViewById(R.id.desc);
        habit_desc.setText(habit.getDescription());

        TextView habit_date = listItemView.findViewById(R.id.date);
        habit_date.setText(habit.getDate());

        if(!Habit.getModify()){
            listItemView.findViewById(R.id.delete_btn).setVisibility(View.GONE);
        }
        else{
            FloatingActionButton fab_delete = listItemView.findViewById(R.id.delete_btn);
            fab_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDbHandler db = new MyDbHandler(getContext());
                    int id = db.getId(habit);

                    db.deleteHabit(id); // delete from database
                    remove(habit); // for listview
                    notifyDataSetChanged();
                }
            });
        }
        return listItemView;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
