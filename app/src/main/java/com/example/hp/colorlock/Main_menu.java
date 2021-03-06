package com.example.hp.colorlock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main_menu extends  AppCompatActivity {

    private TextView mTextMessage;
    public static Button seqButton;
    public static Button mynet,joinnet;
    static final String STATE_FRAGMENT = "state_of_fragment";
    private TextView editText;
    public boolean isFragmentDisplayed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        editText=findViewById(R.id.user_id);
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("credentials",0);
        String user_id=sharedPreferences.getString("user_id","NULL");
        editText.setText("Unique user-id : "+user_id);
        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
        }
        mTextMessage = (TextView) findViewById(R.id.message);
       // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        seqButton = (Button) findViewById(R.id.joined_networks);
        seqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i;
//                i = new Intent(view.getContext(),MainActivity.class);
//                startActivity(i);
                displayFragment(R.id.joined_networks);
            }
        });
        mynet=findViewById(R.id.networks);
        joinnet=findViewById(R.id.joinnetwork);
        mynet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFragment(R.id.networks);
                mynet.setVisibility(View.GONE);
                joinnet.setVisibility(View.GONE);
                seqButton.setVisibility(View.GONE);
            }
        });
        joinnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFragment(R.id.joinnetwork);
                mynet.setVisibility(View.GONE);
                joinnet.setVisibility(View.GONE);
                seqButton.setVisibility(View.GONE);
            }
        });
        Button generate_seq=findViewById(R.id.generate_sequence);
        generate_seq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_menu.this,MainActivity.class));
            }
        });
    }
    public void displayFragment(int id){
        if(id==R.id.networks) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MyNetworksFragment()).addToBackStack("tag").commit();
        }else if(id==R.id.joinnetwork){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new JoinNetworksFragment()).addToBackStack("tag").commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Joined_networks_fragment()).addToBackStack("tag").commit();
        }
    }
//    public void closeFragment() {
//        // Get the FragmentManager.
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        // Check to see if the fragment is already showing.
//        MyNetworksFragment myNetworksFragment= (MyNetworksFragment) fragmentManager
//                .findFragmentById(R.id.fragment_container);
//        if (myNetworksFragment != null) {
//            // Create and commit the transaction to remove the fragment.
//            android.support.v4.app.FragmentTransaction fragmentTransaction =
//                    fragmentManager.beginTransaction();
//            fragmentTransaction.remove(myNetworksFragment).commit();
//            isFragmentDisplayed=false;
//        }
//    }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT,isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
