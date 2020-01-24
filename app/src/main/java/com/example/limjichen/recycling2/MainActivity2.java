package com.example.limjichen.recycling2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jason on 14/04/2018.
 */

public class MainActivity2 extends AppCompatActivity {


    private Handler mHandler = new Handler();
    ProgressBar bar_level;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<String> listDataPoints;
    HashMap<String, List<String>> listDataChild;
    Button but_camera,but_top_setting,but_logo_1,but_logo_2,but_logo_3,but_logo_4;
    Animation shake;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init
        but_camera = (Button)findViewById(R.id.but_camera);
        but_top_setting = findViewById(R.id.but_top_setting);
        but_logo_1 = findViewById(R.id.but_logo_1);
        but_logo_4 = findViewById(R.id.but_logo_2);
        but_logo_3 = findViewById(R.id.but_logo_3);
        but_logo_2 = findViewById(R.id.but_logo_4);

        //progress_bar config
        bar_level = (ProgressBar) findViewById(R.id.progressBar2);
        bar_level.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        bar_level.setProgress(36);



        //Repeat Animation within 1.5seconds
        shake = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.shake);
        AnimationRunnable.run();

        //camera_button activity
        but_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,map_testing.class));
            }
        });

        //Top setting panels
        but_top_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity2.this, but_top_setting);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.setting_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())   {

                            case R.id.Account_Verification:
                                break;

                            case R.id.Bug_Fix:
                                break;

                            case R.id.Log_Out:
                                break;

                            default:
                                break;
                        }
                        Toast.makeText(
                                MainActivity2.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });

        but_logo_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,account_setting.class));
            }
        });
        but_logo_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,delivery_status.class));
            }
        });
        but_logo_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,shopping.class));
            }
        });


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.list_view_challenges);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, listDataPoints);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    //Background animation looping threads
    private Runnable AnimationRunnable = new Runnable() {
        @Override
        public void run() {
            but_camera.startAnimation(shake);
            mHandler.postDelayed(AnimationRunnable,2250);
        }
    };





    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listDataPoints = new ArrayList<String>();


        listDataPoints.add("hello");
        listDataPoints.add("zz");
        listDataPoints.add("ss");
        listDataPoints.add("helqqlo");
        listDataPoints.add("helfdlo");

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");
        listDataHeader.add("Jom Donate");
        listDataHeader.add("Free Flow");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }



}
