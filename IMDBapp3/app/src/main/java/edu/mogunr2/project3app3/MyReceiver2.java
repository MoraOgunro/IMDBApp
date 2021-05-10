package edu.mogunr2.project3app3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub

		Toast aToast =
				Toast.makeText(arg0, "Static receiver II in action! ",
				Toast.LENGTH_LONG) ;

		aToast.show() ;

		Log.i("BroadcastReceiver II", "Second receiver in action!!!") ;

	}

}
