package com.alvaro.racerank;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alvaro.firebasebasic.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    Button origin, destiny, save;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference user = mRootRef.child("user");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        origin = (Button) findViewById(R.id.buttonOrigin);
        destiny = (Button) findViewById(R.id.buttonDestiny);
        save = (Button) findViewById(R.id.buttonSave);
        origin.setOnClickListener(this);
        destiny.setOnClickListener(this);
        save.setOnClickListener(this);

        try {
            user.child("user_id").setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOrigin:
                DatabaseReference route_o = mRootRef.child("route");
                route_o.child("route_id").setValue(1);
                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location location = null;
                Log.v("LATLNGGGGG","aveeee");

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Log.v("fuarkssss","saaa");
                location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                String latlng=String.valueOf(latitude)+","+String.valueOf(longitude);
                Log.v("LATLNGGGGG",latlng.toString());
                route_o=mRootRef.child("route");
                Log.v("LATLNGGGGG",latlng.toString());
                route_o.child("origin").child("location").setValue(latlng);
                route_o.child("origin").child("time").setValue(System.currentTimeMillis());
                break;

            case R.id.buttonDestiny:
                DatabaseReference route_d=mRootRef.child("route");
                route_d.child("destiny").child("location").setValue("lausanne flon");
                route_d.child("destiny").child("time").setValue(System.currentTimeMillis()+600);
                break;

            case R.id.buttonSave:
                Log.d("userchilddd",mRootRef.child("route").child("route_id").toString());
                if(mRootRef.child("route").child("route_id").equals("1"))
                    Toast.makeText(getApplicationContext(),"Route s",Toast.LENGTH_SHORT);
                break;

        }

    }
}