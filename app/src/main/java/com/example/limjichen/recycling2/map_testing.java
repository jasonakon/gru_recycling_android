package com.example.limjichen.recycling2;

import android.animation.FloatEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
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
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arsy.maps_library.MapRipple;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;


import com.example.limjichen.recycling2.Fragments.fragment_three;
import com.example.limjichen.recycling2.Fragments.fragment_one;
import com.example.limjichen.recycling2.Fragments.fragment_two;
import com.example.limjichen.recycling2.ViewPagerAdapter;
import android.Manifest;
import com.example.limjichen.recycling2.map_library.JSONParser;
import com.example.limjichen.recycling2.map_library.Route;

import java.util.ArrayList;

/**
 * Created by jason on 15/04/2018.
 */

public class map_testing extends AppCompatActivity
        implements OnMapReadyCallback {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static String send_fragment2_data = "55",send_fragment3_data = "55";;

    private String frag2_selection_index = "0",frag2_selection_index_tempo = "1";

    Button but_next,but_1,but_2,but_3;


    private Handler handler_fragment1;
    GoogleMap mGoogleMap;
    LatLng myPosition, myDestination, myDestination2, myDestination_camera,myDestination2_camera;
    private static final int DURATION = 3000;
    private int[] tabIcons = {
            R.drawable.ic_today_black_24dp,
            R.drawable.ic_star_black_24dp,
            R.drawable.ic_local_shipping_black_24dp
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Next-Tab activity - fragment1
        handler_fragment1 = new Handler();
        background_run_fragment1.run();




        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        //viewPager.setCurrentItem(2);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
        //Tab Initialization
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[2]);
        tabLayout.getTabAt(2).setIcon(tabIcons[1]);
    }







    //Next-Tab activity fragment1
    Runnable background_run_fragment1 = new Runnable() {
        @Override
        public void run() {

            //Next Tab (2nd Tab) Activity
            if (fragment_one.get_frag_one_index().equals("9898989898")){
                //Move the pager to the respective page
                viewPager.setCurrentItem(1, true);
//                //Re-initiate the tab info
                tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                tabLayout.getTabAt(1).setIcon(tabIcons[2]);
                tabLayout.getTabAt(2).setIcon(tabIcons[1]);

                //send data signal back to fragment_two to execute progressbar loading
                send_fragment2_data = "1";

                final Handler handler_map_zoom = new Handler();
                handler_map_zoom.postDelayed(new Runnable() {
                    public void run() {
                        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 15));
                    }
                }, 1700);
            }

            //Next Tab (3rd Tab) Activity
            if(fragment_two.get_frag_two_index().equals("9898989898")){
                //Move the pager to the respective page
                viewPager.setCurrentItem(2, true);
                //Re-initiate the tab info
                tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                tabLayout.getTabAt(1).setIcon(tabIcons[2]);
                tabLayout.getTabAt(2).setIcon(tabIcons[1]);

                send_fragment3_data = "1";


                //Create Routing to the destination - Google Map - Fragment 3 Activity
                final Route map_route_library = new Route();
                if(frag2_selection_index.equals("1") && frag2_selection_index_tempo.equals("1")){
                    map_route_library.drawRoute(mGoogleMap,map_testing.this,myPosition,myDestination,false,"en");
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myDestination_camera, 15));
                    frag2_selection_index_tempo = "99";
                }
                if(frag2_selection_index.equals("2") && frag2_selection_index_tempo.equals("1")){
                    map_route_library.drawRoute(mGoogleMap,map_testing.this,myPosition,myDestination2,false,"en");
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myDestination2_camera, 14));
                    frag2_selection_index_tempo ="99";
                }

            }

            //Button vendor 1 - Fragment 2
            if(fragment_two.get_frag_two_map_camera_index().equals("1")){
                frag2_selection_index = "1";
                mGoogleMap.addMarker(new MarkerOptions().position(myDestination).title("Caring Recycling Sdn Bhd").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                final Handler handler_map_zoom = new Handler();
                handler_map_zoom.postDelayed(new Runnable() {
                    public void run() {
                        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myDestination_camera, 15));
                    }
                }, 1700);
            }

            //Button Vendor 2 - Fragment 2
            if(fragment_two.get_frag_two_map_camera_index().equals("2")){
                frag2_selection_index = "2";
                mGoogleMap.addMarker(new MarkerOptions().position(myDestination2).title("JB Good Recycling Sdn Bhd").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                final Handler handler_map_zoom = new Handler();
                handler_map_zoom.postDelayed(new Runnable() {
                    public void run() {
                        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.546637, 103.635959), 14));
                    }
                }, 1700);
            }
            handler_fragment1.postDelayed(this, 1000);
        }
    };


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),getApplicationContext());
        adapter.addFragment(new fragment_one(), "");
        adapter.addFragment(new fragment_two(), "");
        adapter.addFragment(new fragment_three(), "");
        viewPager.setAdapter(adapter);
    }



    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mGoogleMap = googleMap;
      //  mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.map_json));


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //User has previously accepted this permission
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
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
            //Destination Locations
            myDestination = new LatLng(1.548518, 103.645849);
            myDestination_camera = new LatLng(1.550838, 103.645850);
            myDestination2 = new LatLng(1.534493, 103.635859);
            myDestination2_camera = new LatLng(1.548825, 103.641452);



            //Marker Command
            //googleMap.addMarker(new MarkerOptions().position(myPosition).title("Start"));

            //Move Camera
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 10));



            //Zoom in animation
            final Handler handler_map = new Handler();
            handler_map.postDelayed(new Runnable() {
            public void run() {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 16));
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


        //using Route.java , route the location.
        //useLibrary 'org.apache.http.legacy' - under Android build.gradle
        //compile files('libs/json.jar') - Manual import library json.jar
        ArrayList<LatLng> route_list = new ArrayList<LatLng>();
        route_list.add(myPosition);
        route_list.add(myDestination2);
        route_list.add(myDestination);

        final Route map_route_library = new Route();
    }

    //sending trigger signal back to fragment_two for progressbar loading
    public static String get_fragment2_data()
    {
        return send_fragment2_data;
    }

    public static String get_fragment3_data(){
        return send_fragment3_data;
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
        final GroundOverlay circle = mGoogleMap.addGroundOverlay(new GroundOverlayOptions()
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

