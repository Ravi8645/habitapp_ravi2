package com.example.habitapp_ravi2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.habitapp_ravi2.data.MyDbHandler;
import com.example.habitapp_ravi2.params.Params;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment1 extends Fragment {
    private MyDbHandler db;
    HabitAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.habit_list, container, false);

        /*
        ArrayList<Habit> habits = new ArrayList<>();
        habits.add(new Habit("Quit smoking","To start saving money","21/12/2020"));
        habits.add(new Habit("Start exercise","To get healthy","01/10/2021"));
        */

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(), ActivityEditor.class);
                startActivity(intent);
            }
        });

        db = new MyDbHandler(getActivity());

        ArrayList<Habit> habits = new ArrayList<>();
        habits = db.getAllHabits();

        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY, " + Params.KEY_NAME
                + " TEXT, " + Params.KEY_DESC + " TEXT, " + Params.KEY_DATE + " TEXT)";
        Log.d("dbharry",create);

        int i;
        for(i=0; i<habits.size(); i++){
            Log.d("dbharry",habits.get(i).getTitle()+" "+habits.get(i).getDescription()+" "+habits.get(i).getDate());
        }

        adapter = new HabitAdapter(getActivity(), habits);

        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        return rootView;
    }

    /*public void fillData() {
        Fragment frg = null;
        frg = getFragmentManager().findFragmentByTag("Your_Fragment_TAG");
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }*/

    @Override
    public void onResume(){
        super.onResume();
    }

}