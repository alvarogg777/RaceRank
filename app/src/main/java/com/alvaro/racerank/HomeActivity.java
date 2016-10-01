package com.alvaro.racerank;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alvaro.firebasebasic.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    TextView userText;
    String user_id;
    Button logout;
//    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.v("INSIDE HOMEACTIVITY","AAAAAA");
        userText=(TextView)findViewById(R.id.userText);
        userText.setText(getIntent().getExtras().getString("display_name"));
        user_id=getIntent().getExtras().getString("user_id");
        logout=(Button)findViewById(R.id.button_log_out);
        Log.v("NOT WORKKKKKING","FUCKIT");
        logout.setOnClickListener(this);
//        auth= FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_log_out:
//                auth.signOut();
                AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("AUTH", "LOGGED OUT!");
                        MainActivity.logged=false;
                        finish();
                    }
                });
                break;
        }
    }
}
