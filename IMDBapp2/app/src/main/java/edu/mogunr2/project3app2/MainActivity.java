package edu.mogunr2.project3app2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /*Intents and Permissions*/

    //The permission that A3 defined
    private static final String KABOOM_PERMISSION=
            "edu.uic.cs478.s19.kaboom";
    //Intent sent from a1 to a2
    public static final String A1_A2_INTENT =
            "edu.uic.cs478.project3.a1_a2";
    //intent sent from a3 to a1
    public static final String A3_A1_INTENT =
            "edu.uic.cs478.project3.a3_a1";

    BroadcastReceiverA2 receiver = new BroadcastReceiverA2();
    Intent i = new Intent();
    IntentFilter filter = new IntentFilter(A1_A2_INTENT);
    boolean receiverRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiverRegistered = false;
        i.setClassName("edu.mogunr2.project3app3","edu.mogunr2.project3app3.MainActivity");

        filter.addAction(A3_A1_INTENT);
        filter.setPriority(100);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> checkPermissionAndBroadcast());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
    //This function checks if this app has the Kaboom permission, if not it requests it
    private void checkPermissionAndBroadcast() {
        Log.i("checkPermissionAndBroadcast", "Checking permissions...");
        if (ActivityCompat.checkSelfPermission(this, KABOOM_PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("checkPermissionAndBroadcast", "Permission already granted.");
            if(!receiverRegistered){
                registerReceiver(receiver,filter);
                receiverRegistered = true;
            }
            //start a3 if we already have the permission
            startActivity(i);
        }
        else {
            Log.i("checkPermissionAndBroadcast", "Asking for permissions...");
            ActivityCompat.requestPermissions(this, new String[]{KABOOM_PERMISSION}, 0);
        }
    }
    @Override
    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("onRequestPermissionsResult", "Permissions Granted");
                registerReceiver(receiver,filter);
                receiverRegistered = true;
                //Starting app 3
                startActivity(i);
            }
            else {
                Log.i("onRequestPermissionsResult", "Permissions Denied");
                Toast.makeText(this,"Permission Denied :(",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}