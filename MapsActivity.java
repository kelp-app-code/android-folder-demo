package com.i2e1.kelptest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener
        {

    private GoogleMap mMap;

    private Marker mSydney;

    private ViewGroup infoWindow;
    private TextView infoTitle;
    private Button infoButton;
    private OnInfoWindowElemTouchListener infoButtonListener, infoImageListner;
    private ImageButton infoImage;
    MapWrapperLayout mapWrapperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapWrapperLayout = (MapWrapperLayout)findViewById(R.id.map_relative_layout);

    }

    public static int getPixelsFromDp(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mapWrapperLayout.init(mMap, getPixelsFromDp(this, 39 + 20));
        this.infoWindow = (ViewGroup)getLayoutInflater().inflate(R.layout.custom_info_window, null);
        this.infoTitle = (TextView)infoWindow.findViewById(R.id.tv_shop_name);
        this.infoButton = (Button)infoWindow.findViewById(R.id.b_connect_navigate_wifi);
        this.infoImage = (ImageButton) infoWindow.findViewById(R.id.image_cafe);

        this.infoButtonListener = new OnInfoWindowElemTouchListener(infoButton,
                getResources().getDrawable(R.drawable.btn_default),
                getResources().getDrawable(R.drawable.btn_default_2))
        {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                // Here we can perform some action triggered after clicking the button
                Toast.makeText(MapsActivity.this, "'s button clicked!", Toast.LENGTH_SHORT).show();
            }
        };

        this.infoButton.setOnTouchListener(infoButtonListener);

        this.infoImageListner = new OnInfoWindowElemTouchListener(infoImage,
                getResources().getDrawable(R.drawable.btn_default),
                getResources().getDrawable(R.drawable.btn_default_2)) {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                Toast.makeText(MapsActivity.this, "image button clicked!", Toast.LENGTH_SHORT).show();
            }
        };

        this.infoImage.setOnTouchListener(infoImageListner);



        // Add a marker in Sydney and move the camera



        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

        // Set listeners for marker events.  See the bottom of this class for their behavior.
        mMap.setOnMarkerClickListener(this);


    }


            class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        // These are both viewgroups containing an ImageView with id "badge" and two TextViews with id
        // "title" and "snippet".

        @Override
        public View getInfoWindow(Marker marker) {

//            infoTitle.setText(marker.getTitle());
//
//            mapWrapperLayout.setMarkerWithInfoWindow(marker, infoWindow);

//            render(marker, mWindow);

            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            infoTitle.setText(marker.getTitle());

            mapWrapperLayout.setMarkerWithInfoWindow(marker, infoWindow);
            return infoWindow;
        }


//        private void render(Marker marker, View view) {
//
//            ImageView shop_image = (ImageView) view.findViewById(R.id.image_cafe);
//            shop_image.setImageResource(R.drawable.coffee_shop);
//            shop_image.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getApplicationContext(), "Image clicked", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            String title = marker.getTitle();
//            TextView titleUi = ((TextView) view.findViewById(R.id.tv_shop_name));
//            if (title != null) {
//                // Spannable string allows us to edit the formatting of the text.
//                SpannableString titleText = new SpannableString(title);
//                titleText.setSpan(new ForegroundColorSpan(Color.RED), 0, titleText.length(), 0);
//                titleUi.setText(titleText);
//            } else {
//                titleUi.setText("");
//            }
//
//            TextView wifi_name = (TextView)view.findViewById(R.id.tv_wifi_Name);
//            wifi_name.setText("New WiFi");
//
//            TextView number_of_offers = (TextView)view.findViewById(R.id.tv_number_Of_Offers);
//            number_of_offers.setText("Offers 5");
//
//            TextView average_ratings = (TextView)view.findViewById(R.id.tv_wifi_ratings);
//            average_ratings.setText("Ratings 4.5");
//
//            TextView distance_from_current_loc = (TextView)view.findViewById(R.id.tv_Distance_from_current_loc);
//            distance_from_current_loc.setText("250m");
//
//            TextView number_of_users = (TextView)view.findViewById(R.id.tv_number_of_users);
//            number_of_users.setText("Users 10");
//
//            Button conect_navigate_button = (Button)view.findViewById(R.id.b_connect_navigate_wifi);
//            conect_navigate_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getApplicationContext(), "Connect clicked", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//
//        }

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

}
