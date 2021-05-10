//package edu.mogunr2.project3app3;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.fragment.app.FragmentManager;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//
//public class MainActivity extends AppCompatActivity {
////todo ApplicationA3contains a single activity that consists of two fragments.
//    //todo defines permission edu.uic.cs478.s19.kaboom
//    //todo his application starts when its main activity receives the intent sent byA2, if A2 has permissionedu.uic.cs478.s19.kaboom.
//    //In this case,A3’s main activity is displayed with its first fragment.
////    private Button mButton ;
////    private MyReceiver mReceiver1 ;
////    private MyReceiver2 mReceiver2 ;
////    private MyReceiver3 mReceiver3 ;
////    private IntentFilter mFilter1 ;
////    private IntentFilter mFilter2 ;
////    private IntentFilter mFilter3 ;
//
//    private static final String KABOOM_PERMISSION=
//            "edu.uic.cs478.s19.kaboom";
//    //The intent that a3 wil send to a1
//    public static final String A3_A1_INTENT =
//            "edu.uic.cs478.project3.a3_a1";
//    boolean showSelected = false;
//
//    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar tb = findViewById(R.id.my_toolbar);
//        setSupportActionBar(tb);
//        tb.setLogo(R.drawable.ic_toolbar_icon);
//        showSelected = true;
//
//        // FRAGMENT STUFF BELOW
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater i = getMenuInflater();
//        i.inflate(R.menu.option_menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        Log.i("Menu","Options item selected");
//        if(id == R.id.runApps){
//            if(showSelected){
//                Intent i = new Intent(A3_A1_INTENT);
//                i.putExtra("EXTRA_URL", "www.google.com");
//                Log.i("Menu", "Sending intent: " + A3_A1_INTENT + " With Extra " + i.getStringExtra("EXTRA_URL"));
//                sendOrderedBroadcast(i, null);
//            }
//            return true;
//        }else if (id == R.id.exit){
//            finish();
//            return true;
//        }else{
//            return super.onOptionsItemSelected(item);
//        }
//    }
//}