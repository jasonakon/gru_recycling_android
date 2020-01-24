package com.example.limjichen.recycling2.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.limjichen.recycling2.R;
import com.example.limjichen.recycling2.map_testing;

/**
 * Created by jason on 16/04/2018.
 */

public class fragment_two extends Fragment {

    private ProgressBar bar_searching;
    private TextView text_header,text_content,text_description,text_vendor1,text_vendor2;
    int progress_trigger = 0;
    private Button but_next, but_delivery;
    private Handler background_handler;
    private String tempo_test,tempo_test2 = "33";
    private ImageView image_vendor1,image_vendor2;

    private static String frag_two_index = "55";
    private String tempo_check = "0";

    private static String map_camera = "0";


    public fragment_two() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view2 = inflater.inflate(R.layout.fragment_two_layout, container, false);


        text_header = view2.findViewById(R.id.text_frag_2);
        text_content = view2.findViewById(R.id.textView6);
        bar_searching = view2.findViewById(R.id.progressBar3);
        text_description = view2.findViewById(R.id.text_frag2_description);
        text_vendor1 = view2.findViewById(R.id.textView7);
        text_vendor2 = view2.findViewById(R.id.textView8);
        image_vendor1 = view2.findViewById(R.id.imageView3);
        image_vendor2 = view2.findViewById(R.id.imageView4);
        but_delivery = view2.findViewById(R.id.but_delivery);


        text_vendor1.setVisibility(View.GONE);
        text_vendor2.setVisibility(View.GONE);
        image_vendor2.setVisibility(View.GONE);
        image_vendor1.setVisibility(View.GONE);
        but_delivery.setVisibility(View.GONE);

        background_handler = new Handler();
        background_run.run();

       // bar_searching.setVisibility(View.GONE);

        but_next = view2.findViewById(R.id.but_delivery);

        but_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tempo_check.equals("1")){
                    frag_two_index = "9898989898";
                } else {
                    Toast.makeText(getActivity(), "Please Choose Your Preferred Vendor..", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return view2;
    }

    Runnable background_run = new Runnable() {
        @Override
        public void run() {
            //Toast.makeText(map_testing.this,fragment_one.getPhoneNumber() , Toast.LENGTH_SHORT).show();
            tempo_test = map_testing.get_fragment2_data();
            if (tempo_test.equals("1") && tempo_test2.equals("33")){
                //Move the pager to the respective page
                load_progressbar();
                //Stop the detection
                tempo_test2 = "44";

                background_handler.removeCallbacks(background_run);
            }
            background_handler.postDelayed(this, 1000);
        }
    };




    private void load_progressbar(){
        bar_searching.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bar_searching.setVisibility(View.GONE);
                text_content.setVisibility(View.GONE);
                text_header.setText("Choose Your Preferred Vendor");

                text_vendor1.setVisibility(View.VISIBLE);
                text_vendor2.setVisibility(View.VISIBLE);
                image_vendor2.setVisibility(View.VISIBLE);
                image_vendor1.setVisibility(View.VISIBLE);
                but_delivery.setVisibility(View.VISIBLE);


                image_vendor1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tempo_check = "1";
                        image_vendor1.setImageResource(R.drawable.caring_selected);
                        image_vendor2.setImageResource(R.drawable.jb);
                        text_vendor1.setTypeface(null, Typeface.BOLD);
                        text_vendor2.setTypeface(null, Typeface.NORMAL);
                        map_camera = "1";
                    }
                });

                image_vendor2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tempo_check = "1";
                        image_vendor1.setImageResource(R.drawable.caring);
                        image_vendor2.setImageResource(R.drawable.jb_selected);
                        text_vendor1.setTypeface(null, Typeface.NORMAL);
                        text_vendor2.setTypeface(null, Typeface.BOLD);
                        map_camera = "2";

                    }
                });
            }
        }, 2500);
        //bar.setVisibility(View.GONE);
    }

    public static String get_frag_two_index()
    {

        return frag_two_index;
    }

    public static String get_frag_two_map_camera_index(){
        return map_camera;
    }

    public static String get_frag_two_vendor_index(){
        return map_camera;
    }


}
