package edu.mogunr2.project3app3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements TitlesFragment.ListSelectionListener {
    //The intent that app 3 wil send to app 1
    public static final String A3_A1_INTENT =
            "edu.uic.cs478.project3.a3_a1";
    //Flag if a show has been selected
    public static boolean showSelected = false;
    //The index of the show that was selected
    //Used to find the appropriate URL to send to app 1
    public static int indexSelected;
    /*
     FRAGMENT STUFF
     */
    public static String[] mTitleArray;
    public static int[] mImageArray;
    public static String[] mUrlArray;
    private final QuotesFragment mQuoteFragment = new QuotesFragment();
    FragmentManager mFragmentManager;
    private FrameLayout mTitleFrameLayout, mQuotesFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Grabbing the toolbar from activity_main.xml and setting it to an actionbar
        Toolbar tb = findViewById(R.id.my_toolbar);
        setSupportActionBar(tb);
        tb.setLogo(R.drawable.ic_toolbar_icon);

        //Filling the arrays with necessary data for use in fragments
        mTitleArray = new String[]{"Mr. Robot", "Attack on Titan", "WandaVision", "Watchmen", "Breaking Bad","Avatar The Last Airbender","Black Mirror","Chernobyl"};
        mImageArray = new int[]{R.drawable.mrrobot,R.drawable.attakcontitan,R.drawable.wandavision,R.drawable.watchmen, R.drawable.breakingbafd,R.drawable.avatar,R.drawable.blackmirror,R.drawable.chernobyl};
        mUrlArray = new String[]{"https://www.imdb.com/title/tt4158110/","https://www.imdb.com/title/tt2560140/?ref_=nv_sr_srsg_0","https://www.imdb.com/title/tt9140560/?ref_=nv_sr_srsg_0",
            "https://www.imdb.com/title/tt7049682/?ref_=nv_sr_srsg_3","https://www.imdb.com/title/tt0903747/?ref_=nv_sr_srsg_0", "https://www.imdb.com/title/tt0417299/?ref_=nv_sr_srsg_0",
            "https://www.imdb.com/title/tt2085059/?ref_=nv_sr_srsg_0","https://www.imdb.com/title/tt7366338/?ref_=nv_sr_srsg_6"};
        // Get references to the TitleFragment and to the QuotesFragment
        mTitleFrameLayout = (FrameLayout) findViewById(R.id.title_fragment_container);
        mQuotesFrameLayout = (FrameLayout) findViewById(R.id.quote_fragment_container);

        // Get a reference to the SupportFragmentManager instead of original FragmentManager
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        // Add the TitleFragment to the layout
        // UB: 10/2/2016 Changed add() to replace() to avoid overlapping fragments
        fragmentTransaction.replace(
                R.id.title_fragment_container,
                new TitlesFragment());
        // Commit the FragmentTransaction
        fragmentTransaction.commit();
        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager.addOnBackStackChangedListener(
                // UB 2/24/2019 -- Use support version of Listener
                () -> setLayout());
    }
    private void setLayout() {
        // Determine whether the QuoteFragment has been added
        if (!mQuoteFragment.isAdded()) {
            // Make the TitleFragment occupy the entire layout
            mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {
            //Determine if the phone is in landscape or portrait mode
            int orientation = this.getResources().getConfiguration().orientation;
            if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                Log.i("SetLayout", "Now in landscape mode.");
                // Make the TitleLayout take 1/3 of the layout's width
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the QuoteLayout take 2/3's of the layout's width
                mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }else{
                Log.i("SetLayout", "Now in portrait mode.");
                //make the title fragment disappear
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 0f));

                // Make the QuoteLayout take 100% of the layout's width
                mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));
            }
        }
    }
    @Override
    public void onListSelection(int index) {
        showSelected = true;
        indexSelected = index;
        // If the QuoteFragment has not been added, add it now
        if (!mQuoteFragment.isAdded()) {

            // Start a new FragmentTransaction
            // UB 2/24/2019 -- Now must use compatible version of FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the QuoteFragment to the layout
            fragmentTransaction.add(R.id.quote_fragment_container,
                    mQuoteFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }

        if (mQuoteFragment.getShownIndex() != index) {

            // Tell the QuoteFragment to show the quote string at position index
            mQuoteFragment.showQuoteAtIndex(index);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.i("Menu","Options item selected");
        if(id == R.id.runApps){
            //If the user has selected a show
            if(showSelected){
                Intent i = new Intent(A3_A1_INTENT);
                i.putExtra("EXTRA_URL", mUrlArray[indexSelected]);
                Log.i("Menu", "Sending intent: " + A3_A1_INTENT + " With Extra " + i.getStringExtra("EXTRA_URL"));
                sendOrderedBroadcast(i, null);
            }else{
                Toast.makeText(this,"Please select a show first.",Toast.LENGTH_SHORT).show();
            }
            return true;
        }else if (id == R.id.exit){
            finish();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}