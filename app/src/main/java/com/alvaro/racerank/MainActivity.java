package com.alvaro.racerank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alvaro.firebasebasic.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth auth;
    TextView user;
    private final static int RC_SIGN_IN=0;
    Button login,signin;
    DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
    DatabaseReference logged_user=mRootRef.child("user");
    static boolean logged=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        user=(TextView)findViewById(R.id.user);
        login=(Button)findViewById(R.id.log_in_button);
        signin=(Button)findViewById(R.id.sign_in_button);
        login.setOnClickListener(this);
        signin.setOnClickListener(this);
        auth=FirebaseAuth.getInstance();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            if(resultCode==RESULT_OK){
                //user logged in
//                Log.d("AUTH",auth.getCurrentUser().getEmail());
                logged=true;
                Toast.makeText(getApplicationContext(),"Welcome to MyTracker "+auth.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(this,HomeActivity.class);
                i.putExtra("user_id",auth.getCurrentUser().getUid());
                i.putExtra("display_name",auth.getCurrentUser().getDisplayName());
                i.putExtra("pic",auth.getCurrentUser().getPhotoUrl());
//                Log.v("COME ON","  aaa");
                startActivity(i);
            }else{
                //user not authenticated
                Log.d("AUTH","NOT AUTHENTICATED");
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.log_in_button:
                auth(auth);
                if(logged)
                    startNextActivity();
                break;
            case R.id.sign_in_button:
                auth(auth);
                if(logged)
                    startNextActivity();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        logged_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String text=dataSnapshot.getValue(String.class);
//                user.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void auth(FirebaseAuth auth){
        if (auth.getCurrentUser() != null) {
            //user already sign in
//            Log.d("AUTH", auth.getCurrentUser().getEmail());
//            login.setVisibility(View.GONE);
//            signin.setVisibility(View.GONE);
            logged=true;
        }
        else {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setProviders(
                    AuthUI.EMAIL_PROVIDER,
                    AuthUI.FACEBOOK_PROVIDER,
                    AuthUI.GOOGLE_PROVIDER)
                    .build(), RC_SIGN_IN);
        }
    }
    private void startNextActivity(){
        Intent i=new Intent(this,HomeActivity.class);
        i.putExtra("user_id",auth.getCurrentUser().getUid());
        i.putExtra("display_name",auth.getCurrentUser().getDisplayName());
        i.putExtra("pic",auth.getCurrentUser().getPhotoUrl());
//        login.setVisibility(View.GONE);
//        signin.setVisibility(View.GONE);
        Log.v("COME ON","  aaa");
        startActivity(i);


    }
}
