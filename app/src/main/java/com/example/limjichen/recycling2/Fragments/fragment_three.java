package com.example.limjichen.recycling2.Fragments;


import android.content.Intent;
import android.os.AsyncTask;
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

import com.example.limjichen.recycling2.MainActivity2;
import com.example.limjichen.recycling2.R;
import com.example.limjichen.recycling2.map_testing;

/**
 * Created by jason on 16/04/2018.
 */

public class fragment_three extends Fragment {

    private String tempo_test,tempo_test3 = "33";
    Button but_frag3;
    TextView text_frag3_vendor,text_frag3_coin, text_test_frag3;
    ImageView image_frag3;
    Handler background_handler;
    int jason_tempo_txt = 38;
    private ProgressBar progressBar5;
    private static String frag3_complete_check = "0";





    public fragment_three() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_three_layout, container, false);
        but_frag3 = view.findViewById(R.id.but_frag3);
        text_frag3_coin = view.findViewById(R.id.text_coin_frag3);
        text_frag3_vendor = view.findViewById(R.id.text_vendor_frag3);
        image_frag3 = view.findViewById(R.id.image_frag3);
        progressBar5 = view.findViewById(R.id.progressBar5);
        text_test_frag3 = view.findViewById(R.id.text_test_frag3);




        progressBar5.setIndeterminate(true);

//        Toast.makeText(getActivity(), "fkfkfkfkf", Toast.LENGTH_SHORT).show();



        getActivity().runOnUiThread(new Runnable() {
            public void run(){
                tempo_test = fragment_two.get_frag_two_vendor_index();
                if (map_testing.get_fragment3_data().equals("1") && tempo_test.equals("1") ) {
                    image_frag3.setImageResource(R.drawable.caring_selected);
                    text_frag3_vendor.setText("• Caring Recycling Sdn Bhd");
//                    Toast.makeText(getActivity(), "fuck", Toast.LENGTH_SHORT).show();

                       }

                if (map_testing.get_fragment3_data().equals("1") && tempo_test.equals("2")) {
                    image_frag3.setImageResource(R.drawable.jb_selected);
                    text_frag3_vendor.setText("• J.B. Good Recycling Sdn Bhd");
                }

                if (fragment_two.get_frag_two_map_camera_index().equals("1")){
                    image_frag3.setImageResource(R.drawable.jb_selected);
                }

//                tempo_test3 = "button pressed =" + map_testing.get_fragment3_data() + " vendor index=" + tempo_test;
//                text_test_frag3.setText(tempo_test3);


            }
        });





        but_frag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MainActivity2.class));
                frag3_complete_check = "1";
            }
        });


        return view;
    }

    public static String get_frag3_complete_check(){
        return frag3_complete_check;
    }





}
