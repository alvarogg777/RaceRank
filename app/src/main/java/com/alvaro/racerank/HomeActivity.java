package com.alvaro.racerank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alvaro.firebasebasic.R;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.midhunarmid.movesapi.MovesAPI;
import com.midhunarmid.movesapi.MovesHandler;
import com.midhunarmid.movesapi.util.MovesStatus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.attr.data;
import static android.R.id.message;
import static android.os.Build.ID;
import static com.facebook.share.internal.ShareConstants.IMAGE_URL;
import static com.google.android.gms.internal.zznu.is;
import static com.midhunarmid.movesapi.MovesAPI.init;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    TextView userText;
    String user_id;
    Button logout,start,myruns,other;
    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;
    URL url_img;
    String uri;
    private static final int REQUEST_AUTHORIZE = 1;

    //    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
//        Log.v("INSIDE HOMEACTIVITY","AAAAAA");
        start=(Button)findViewById(R.id.start);
        myruns=(Button)findViewById(R.id.my_runs);
        other=(Button)findViewById(R.id.other_routes);
        start.setOnClickListener(this);
        myruns.setOnClickListener(this);
        other.setOnClickListener(this);
        userText=(TextView)findViewById(R.id.userText);
        userText.setText(getIntent().getExtras().getString("display_name"));
        user_id=getIntent().getExtras().getString("user_id");
        logout=(Button)findViewById(R.id.button_log_out);
        Log.v("NOT WORKKKKKING","FUCKIT");
        logout.setOnClickListener(this);
        mNetworkImageView = (NetworkImageView) findViewById(R.id.imageView);
        uri=FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();
        try {
            url_img=new URL(uri.toString());
            // Get the ImageLoader through your singleton class.
            mImageLoader = MySingleton.getInstance(this).getImageLoader();

// Set the URL of the image that should be loaded into this view, and
// specify the ImageLoader that will be used to make the request.
            mNetworkImageView.setImageUrl(uri, mImageLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        auth= FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                Intent i=new Intent(HomeActivity.this,StartActivity.class );
                startActivity(i);
//                FirebaseStorage storage = FirebaseStorage.getInstance();
                // Create a storage reference from our app
//                StorageReference storageRef = storage.getReferenceFromUrl("gs://fir-basic-e3e11.appspot.com");
                // Create a child reference
                // imagesRef now points to "images"
//                StorageReference imagesRef = storageRef.child("images");

                // Child references can also take paths
                // spaceRef now points to "users/me/profile.png
                // imagesRef still points to "images"
//                StorageReference wallhavenREF = storageRef.child("images/wallhaven-60613");
//                final long ONE_MEGABYTE = 1024 * 1024;
//                wallhavenREF.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//                    @Override
//                    public void onSuccess(byte[] bytes) {
//                         Data for "images/island.jpg" is returns, use this as needed
//                        Bitmap myBitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//                        pic.setImageBitmap(myBitmap);
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
//                    }
//                });
//                MovesHandler movesHandler=new MovesHandler() {
//                    @Override
//                    public void onSuccess(Object result) {
//                        Log.d("ONSUCCESSSSSSSSSSS",String.valueOf(result).toString());
//                    }

//                    @Override
//                    public void onFailure(MovesStatus status, String message) {

//                    }
//                };
//                try {
                    /**
                 *Call this method before using any other functions of this library. Call to this method will
                 * initialize your {@link MovesAPI} client application details.
                 * @param context : A valid application {@link Context} is required to store data in {@link SharedPreferences}
                 * @param clientId : The Client ID you received when registering your application with Moves
                 * @param clientSecret : The Client Secret you received when registering your application with Moves
                 * @param clientScopes : Requested scopes (space-delimited). Should contain either activity, location or both scopes.
                 * @param redirectURL : The URI must match one of the callback URIs registered for your app
                 * @throws Exception will thrown if the client details are null or empty
                 */
//                String scope="location activity";
//                    scope=scope.trim();
//                    MovesAPI.init(getApplicationContext(),"YcS123nK4f651fEt0ID3wM8Ouj5gR0qB","ONkJ13bFO1JLTbrdECg10wI8f80eMWx0TZ6VGlIA6c7A0uTOyYK5eI2Pwxq63AjT",scope,"https://moves-api-demo.herokuapp.com/auth/moves/callback");
//                    MovesAPI.authenticate(movesHandler,this);
//                    private void doRequestAuthBrowser() {
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://moves-api-demo.herokuapp.com/"));
//                        startActivity(intent);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

//                MovesAPI.getActivities_SingleDay(movesHandler,"20160815",null);
//                Log.d("CLIENTEID",MovesAPI.getClientDetails().getClientID());
//                movesHandler.onSuccess(MovesAPI.getAuthData());

                break;

            case R.id.my_runs:


                break;
            case R.id.other_routes:


                break;
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_AUTHORIZE:
                Uri resultUri = data.getData();
                Toast.makeText(this,
                        (resultCode == RESULT_OK ? "Authorized: " : "Failed: ")
                                + resultUri, Toast.LENGTH_LONG).show();
        }

    }
}
