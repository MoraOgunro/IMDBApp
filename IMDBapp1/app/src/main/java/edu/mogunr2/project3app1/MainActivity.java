package edu.mogunr2.project3app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String KABOOM_PERMISSION=
            "edu.uic.cs478.s19.kaboom";
    //The intent that a3 wil send to a1
    public static final String A3_A1_INTENT =
            "edu.uic.cs478.project3.a3_a1";
    //Intent to be sent to a2
    public static final String A1_A2_INTENT =
            "edu.uic.cs478.project3.a1_a2";

    MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
    Intent i = new Intent();
    IntentFilter filter = new IntentFilter(A3_A1_INTENT);
    boolean receiverRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiverRegistered = false;

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> checkPermissionAndBroadcast());
        //make sure the filter has a lower priority than app 2.
        filter.setPriority(10);
        //launch the second app
        //i.setAction("a2");
        i.setClassName("edu.mogunr2.project3app2","edu.mogunr2.project3app2.MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }
    //This function checks if this app has the Kaboom permission, if not it requests it
    private void checkPermissionAndBroadcast() {
        Log.i("checkPermissionAndBroadcast", "Button Clicked");
        if (ActivityCompat.checkSelfPermission(this, KABOOM_PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("checkPermissionAndBroadcast", "Permission already granted");
            if(!receiverRegistered){
                registerReceiver(myBroadcastReceiver,filter);
                receiverRegistered = true;
            }
            startActivity(i);
        }
        else {
            Log.i("checkPermissionAndBroadcast", "Asking for permission...");
            ActivityCompat.requestPermissions(this, new String[]{KABOOM_PERMISSION}, 0);
        }
    }
    //This function checks if this the user granted the permission or not
    @Override
    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("onRequestPermissionsResult", "Permissions Granted");

                registerReceiver(myBroadcastReceiver,filter);
                receiverRegistered = true;
                //Sending intent to A2
                startActivity(i);
            }
            else {
                Log.i("onRequestPermissionsResult", "Permissions Denied");
            }
        }
    }
}