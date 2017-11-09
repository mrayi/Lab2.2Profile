package edu.tarc.lab22profile;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_UPDATE_REQUEST = 9;
    public static final String PROFILE_NAME = "edu.tarc.lab22profile.name";
    public static final String PROFILE_EMAIL = "edu.tarc.lab22profile.email";
    private TextView textViewName,textViewEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking ui to program
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewEmail=(TextView) findViewById(R.id.textViewEmail);
    }


    public void updateProfile(View view){
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivityForResult(intent,PROFILE_UPDATE_REQUEST);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //requestCode=the unique request code that identify an intent call
        //resultCode=result of the intent call; either ok or cancel
        //data=the actual data returned by an intent call
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PROFILE_UPDATE_REQUEST&&
                resultCode==RESULT_OK) {
            String name, email;
            name=data.getStringExtra(PROFILE_NAME);
            email=data.getStringExtra(PROFILE_EMAIL);
            textViewName.setText(getString(R.string.name)+" : " + name);
            textViewEmail.setText(getString(R.string.email)+" : " + email);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Main Activity","onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main Activity","onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main Activity","onPause");

    }
    public void visitBAIT2073(View v){
        String uri="https://www.google.com/search?q=ayi+profile&sa=X&ved=0ahUKEwj_58rFzLDXAhUB2o8KHX5lCp8Q1QIIWigA&biw=1366&bih=662";
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    public void showMain(View v){
        Intent intent=new Intent(Intent.ACTION_MAIN);
        startActivity(intent);
    }
    public void showDial(View v){
        Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + "0174005100"));

        startActivity(intent);
    }
    public void showMail(View v){
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"+"seekt@tarc.edu.my"));

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities= packageManager.queryIntentActivities(intent,0);
        boolean isIntentSafe=activities.size()>0;


        if(isIntentSafe) {
            startActivity(intent);
        }
    }

}
