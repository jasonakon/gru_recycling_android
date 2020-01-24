package com.example.limjichen.recycling2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.limjichen.recycling2.Adapter.MyAdapter;
import com.example.limjichen.recycling2.Models.TitleChild;
import com.example.limjichen.recycling2.Models.TitleCreator;
import com.example.limjichen.recycling2.Models.TitleParent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        RecyclerView recyclerView;
        ProgressBar bar_level;


        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            ((MyAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            bar_level = (ProgressBar) findViewById(R.id.progressBar2);
            bar_level.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
            bar_level.setProgress(36);
            //recyclerView = (RecyclerView)findViewById(R.id.myRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            MyAdapter adapter = new MyAdapter(this,initData());
            adapter.setParentClickableViewAnimationDefaultDuration();
            adapter.setParentAndIconExpandOnClick(true);

            recyclerView.setAdapter(adapter);
        }

        private List<ParentObject> initData() {
            TitleCreator titleCreator = TitleCreator.get(this);
            List<TitleParent> titles = titleCreator.getAll();
            List<ParentObject> parentObject = new ArrayList<>();
            for(TitleParent title:titles)
            {
                List<Object> childList = new ArrayList<>();
                childList.add(new TitleChild("Add to contacts","Send message"));
                title.setChildObjectList(childList);
                parentObject.add(title);
            }
            return parentObject;

        }




    }

