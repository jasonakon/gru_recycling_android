package com.example.limjichen.recycling2.Fragments;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.limjichen.recycling2.R;
import com.example.limjichen.recycling2.map_testing;

import java.lang.reflect.Type;

/**
 * Created by jason on 16/04/2018.
 */

public class fragment_one extends Fragment {

     private static String frag_one_index = "55";
     private String tempo_check = "0";


    public fragment_one() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // phoneNumber = "9898989898";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one_layout, container, false);

        Button mainButton = (Button) view.findViewById(R.id.but_next);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tempo_check.equals("1")){
                    frag_one_index = "9898989898";
                } else {
                    Toast.makeText(getActivity(), "Please Choose Your Preferred Timing", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //ImageSwitcher Activities
        final ImageView imageSwitcher_1,imageSwitcher_2,imageSwitcher_3;
        final TextView text_morning,text_afternoon,text_night;
        text_morning = view.findViewById(R.id.textView4);
        text_afternoon = view.findViewById(R.id.textView3);
        text_night = view.findViewById(R.id.textView5);

        imageSwitcher_1 = view.findViewById(R.id.imageSwitcher1);
        imageSwitcher_2 = view.findViewById(R.id.imageSwitcher2);
        imageSwitcher_3 = view.findViewById(R.id.imageSwitcher3);

        imageSwitcher_1.setImageResource(R.drawable.morning);
        imageSwitcher_2.setImageResource(R.drawable.afternoon);
        imageSwitcher_3.setImageResource(R.drawable.night);

        imageSwitcher_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    imageSwitcher_1.setImageResource(R.drawable.morning_selected);
                    imageSwitcher_2.setImageResource(R.drawable.afternoon);
                    imageSwitcher_3.setImageResource(R.drawable.night);
                    text_morning.setTypeface(null, Typeface.BOLD);
                    text_afternoon.setTypeface(null,Typeface.NORMAL);
                    text_night.setTypeface(null, Typeface.NORMAL);
                    tempo_check = "1";

            }
        });

        imageSwitcher_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    imageSwitcher_2.setImageResource(R.drawable.afternoon_selected);
                    imageSwitcher_1.setImageResource(R.drawable.morning);
                    imageSwitcher_3.setImageResource(R.drawable.night);
                    text_morning.setTypeface(null, Typeface.NORMAL);
                    text_afternoon.setTypeface(null,Typeface.BOLD);
                    text_night.setTypeface(null, Typeface.NORMAL);
                    tempo_check = "1";
            }
        });

        imageSwitcher_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    imageSwitcher_3.setImageResource(R.drawable.night_selected);
                    imageSwitcher_2.setImageResource(R.drawable.afternoon);
                    imageSwitcher_1.setImageResource(R.drawable.morning);
                    text_morning.setTypeface(null, Typeface.NORMAL);
                    text_afternoon.setTypeface(null,Typeface.NORMAL);
                    text_night.setTypeface(null, Typeface.BOLD);
                    tempo_check = "1";
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public static String get_frag_one_index()
    {

        return frag_one_index;
    }

}


