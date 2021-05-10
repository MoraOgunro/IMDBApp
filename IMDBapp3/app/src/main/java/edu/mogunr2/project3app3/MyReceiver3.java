package edu.mogunr2.project3app3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver3 extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub

		Toast.makeText(arg0, "Static receiver III in action! ",
				Toast.LENGTH_LONG).show() ;
		Log.i("BroadcastReceiver III", "Third receiver in action!!!") ;

	}

}
