package com.example.limjichen.recycling2;

import android.*;
import android.animation.FloatEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.limjichen.recycling2.Fragments.fragment_one;
import com.example.limjichen.recycling2.Fragments.fragment_two;
import com.example.limjichen.recycling2.map_library.Route;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import com.example.limjichen.recycling2.Fragments.fragment_three;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by LIMJICHEN on 23/4/2018.
 */

public class delivery_status extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap zGoogleMap;
    private static final int DURATION = 3000;
    LatLng myPosition,myDestination,myDestination_camera,myDestination2,myDestination2_camera;
    private String tempo_test,coin_ds;
    private ImageView image_delivery_status,image_question_ds;
    private TextView text_vendor_ds,text_coin_ds,text_header_ds,text_driver_ds,text_description_ds;
    private ProgressBar progress_ds;
    private Route map_route_status;
    private Handler handler_ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_status_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_status);
        mapFragment.getMapAsync(this);


        image_delivery_status = findViewById(R.id.image_delivery_status);
        text_vendor_ds = findViewById(R.id.text_vendor_delivery_status);
        text_coin_ds = findViewById(R.id.text_coin_delivery_status);
        progress_ds = findViewById(R.id.progress_ds);
        text_header_ds = findViewById(R.id.text_header_ds);
        text_driver_ds = findViewById(R.id.text_driver_ds);
        image_question_ds = findViewById(R.id.image_question_ds);
        text_description_ds = findViewById(R.id.text_description_ds);


        //*Temporary , Had to change after completed
        coin_ds = "• 30" + " coins";




//        runOnUiThread(new Runnable() {
//            public void run(){
//
//                tempo_test = fragment_two.get_frag_two_vendor_index();
//                if (map_testing.get_fragment3_data().equals("1") && tempo_test.equals("1") && fragment_three.get_frag3_complete_check().equals("1") ) {
//                    image_delivery_status.setVisibility(View.VISIBLE);
//                    text_vendor_ds.setVisibility(View.VISIBLE);
//                    text_driver_ds.setVisibility(View.VISIBLE);
//                    text_coin_ds.setVisibility(View.VISIBLE);
//                    image_question_ds.setVisibility(View.GONE);
//                    text_description_ds.setVisibility(View.GONE);
//
//                    image_delivery_status.setImageResource(R.drawable.caring_selected);
//                    text_vendor_ds.setText("• Caring Recycling Sdn Bhd");
//                    text_coin_ds.setText(coin_ds);
//                    progress_ds.setIndeterminate(true);
//
//
//                }
//
//                if (map_testing.get_fragment3_data().equals("1") && tempo_test.equals("2") && fragment_three.get_frag3_complete_check().equals("1")) {
//                    image_delivery_status.setVisibility(View.VISIBLE);
//                    text_vendor_ds.setVisibility(View.VISIBLE);
//                    text_driver_ds.setVisibility(View.VISIBLE);
//                    text_coin_ds.setVisibility(View.VISIBLE);
//                    image_question_ds.setVisibility(View.GONE);
//                    text_description_ds.setVisibility(View.GONE);
//
//
//
//                    image_delivery_status.setImageResource(R.drawable.jb_selected);
//                    text_vendor_ds.setText("• J.B. Good Recycling Sdn Bhd");
//                    text_coin_ds.setText(coin_ds);
//                    progress_ds.setIndeterminate(true);
//
//
//                }
//
//                if (fragment_three.get_frag3_complete_check().equals("0")){
///*                    text_coin_ds.setText(" - ");
//                    text_vendor_ds.setText(" - ");
//                    text_driver_ds.setText(" - ");*/
//
//                    text_header_ds.setText("No Requested Delivery");
//                }
//            }
//        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        zGoogleMap = googleMap;
        //  mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.map_json));


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //User has previously accepted this permission
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                googleMap.setMyLocationEnabled(true);
            }
        } else {
            //Not in api-23, no need to prompt
            googleMap.setMyLocationEnabled(true);
        }

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);

        //Check whether the object is null or not
        if( location != null) {
            // Getting latitude of the current location
            double latitude = location.getLatitude();

            // Getting longitude of the current location
            double longitude = location.getLongitude();

            // Creating a LatLng object for the current location
            myPosition = new LatLng(latitude, longitude);
        } else {
            myPosition = new LatLng(1.555628, 103.645000);
            Toast.makeText(this, "Please Check Your GPS connectivity", Toast.LENGTH_SHORT).show();
        }

        myDestination = new LatLng(1.548518, 103.645849);
        myDestination_camera = new LatLng(1.550838, 103.645850);
        myDestination2 = new LatLng(1.534493, 103.635859);
        myDestination2_camera = new LatLng(1.548825, 103.641452);

        //Move Camera
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 10));

        //Zoom in animation
        final Handler handler_map = new Handler();
        handler_map.postDelayed(new Runnable() {
            public void run() {
                zGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 16));
            }
        }, 1700);

        //Ripple Effect around the location
        showRipples(myPosition);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showRipples(myPosition);
            }
        }, DURATION - 500);

        map_route_status = new Route();




        tempo_test = fragment_two.get_frag_two_vendor_index();
        if (map_testing.get_fragment3_data().equals("1") && tempo_test.equals("1") && fragment_three.get_frag3_complete_check().equals("1") ) {

            image_delivery_status.setVisibility(View.VISIBLE);
            text_vendor_ds.setVisibility(View.VISIBLE);
            text_driver_ds.setVisibility(View.VISIBLE);
            text_coin_ds.setVisibility(View.VISIBLE);
            image_question_ds.setVisibility(View.GONE);
            text_description_ds.setVisibility(View.GONE);

            image_delivery_status.setImageResource(R.drawable.caring_selected);
            text_vendor_ds.setText("• Caring Recycling Sdn Bhd");
            text_coin_ds.setText(coin_ds);
            progress_ds.setIndeterminate(true);






            map_route_status.drawRoute(zGoogleMap,delivery_status.this,myPosition,myDestination,false,"en");


            zGoogleMap.addMarker(new MarkerOptions().position(myDestination).title("Caring Recycling Sdn Bhd").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            final Handler handler_map_zooms = new Handler();
            handler_map_zooms.postDelayed(new Runnable() {
                public void run() {
                    zGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myDestination_camera, 15));
                }
            }, 1700);
        }

        if (map_testing.get_fragment3_data().equals("1") && tempo_test.equals("2") && fragment_three.get_frag3_complete_check().equals("1")) {


            image_delivery_status.setVisibility(View.VISIBLE);
            text_vendor_ds.setVisibility(View.VISIBLE);
            text_driver_ds.setVisibility(View.VISIBLE);
            text_coin_ds.setVisibility(View.VISIBLE);
            image_question_ds.setVisibility(View.GONE);
            text_description_ds.setVisibility(View.GONE);



            image_delivery_status.setImageResource(R.drawable.jb_selected);
            text_vendor_ds.setText("• J.B. Good Recycling Sdn Bhd");
            text_coin_ds.setText(coin_ds);
            progress_ds.setIndeterminate(true);




            map_route_status.drawRoute(zGoogleMap,delivery_status.this,myPosition,myDestination2,false,"en");


            zGoogleMap.addMarker(new MarkerOptions().position(myDestination2).title("JB Good Recycling Sdn Bhd").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
            final Handler handler_map_zooms = new Handler();
            handler_map_zooms.postDelayed(new Runnable() {
                public void run() {
                    zGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.546637, 103.635959), 14));
                }
            }, 1700);
        }



    }


    //GoogleMap Marker's Animation
    private void showRipples(LatLng latLng) {
        GradientDrawable d = new GradientDrawable();
        d.setShape(GradientDrawable.OVAL);
        d.setSize(500,500);
        d.setColor(0x5500ff00);
        d.setStroke(0, Color.TRANSPARENT);

        Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth()
                , d.getIntrinsicHeight()
                , Bitmap.Config.ARGB_8888);

        // Convert the drawable to bitmap
        Canvas canvas = new Canvas(bitmap);
        d.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        d.draw(canvas);

        // Radius of the circle
        final int radius = 500;

        // Add the circle to the map
        final GroundOverlay circle = zGoogleMap.addGroundOverlay(new GroundOverlayOptions()
                .position(latLng, 2 * radius).image(BitmapDescriptorFactory.fromBitmap(bitmap)));

        // Prep the animator
        PropertyValuesHolder radiusHolder = PropertyValuesHolder.ofFloat("radius", 0, radius);
        PropertyValuesHolder transparencyHolder = PropertyValuesHolder.ofFloat("transparency", 0, 1);

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setValues(radiusHolder, transparencyHolder);
        valueAnimator.setDuration(DURATION);
        valueAnimator.setEvaluator(new FloatEvaluator());
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedRadius = (float) valueAnimator.getAnimatedValue("radius");
                float animatedAlpha = (float) valueAnimator.getAnimatedValue("transparency");
                circle.setDimensions(animatedRadius * 2);
                circle.setTransparency(animatedAlpha);
            }
        });

        // start the animation
        valueAnimator.start();
    }
}
