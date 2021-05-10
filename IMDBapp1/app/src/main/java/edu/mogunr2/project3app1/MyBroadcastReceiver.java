package edu.mogunr2.project3app1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("OnReceive", "A1 has received something... " + intent.getAction());

        if("edu.uic.cs478.project3.a3_a1".equals(intent.getAction())) {
            Log.i("A1 onReceive", "A1 Received intent extra " + intent.getStringExtra("EXTRA_URL") + " A3");
            Intent webPage = new Intent(context, WebPageActivity.class);
            webPage.putExtra("URL", intent.getStringExtra("EXTRA_URL"));
            context.startActivity(webPage);
        }
    }
}
