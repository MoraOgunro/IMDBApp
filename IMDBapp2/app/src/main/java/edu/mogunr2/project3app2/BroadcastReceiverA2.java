package edu.mogunr2.project3app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReceiverA2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("OnReceive", "A2 has received something..." + intent.getAction());
        if("edu.uic.cs478.project3.a1_a2".equals(intent.getAction())) {
            Log.i("OnReceive", "A2 Received Broadcast from A1");
        }
        if("edu.uic.cs478.project3.a3_a1".equals(intent.getAction())) {
            Log.i("OnReceive", "A2 Received Broadcast from A3");
            Toast.makeText(context,"A2 Received Broadcast from A3",Toast.LENGTH_SHORT).show();
        }
    }
}
