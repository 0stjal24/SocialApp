package com.example.lukeboyde.socialapp.Profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.lukeboyde.socialapp.R;
import com.example.lukeboyde.socialapp.Utils.BottomNavigationViewHelper;
import com.example.lukeboyde.socialapp.Utils.FirebaseMethods;
import com.example.lukeboyde.socialapp.Utils.SectionsStatePagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

/**
 * Created by lukeboyde on 14/04/2018.
 */

public class AccountSettingsActivity extends AppCompatActivity {

    private static final String TAG = "AccountSettingsActivity";
    private static final int ACTIVITY_NUM = 4;

    private Context mContext;
    public SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        mContext = AccountSettingsActivity.this;
        mViewPager = (ViewPager) findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relLayout1);

        setupSettingsList();
        setupBottomNavigationView();
        setupFragments();
        getIncomingIntent();

        //backarrow to navigate back to profileActivity
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIncomingIntent(){
        //retrieve any intent that comes to activity when started
        Intent intent = getIntent();
        if(intent.hasExtra(getString(R.string.selected_image))
                || intent.hasExtra(getString(R.string.selected_bitmap))){


        //if there is an imageURl attachced as extra, then was picked from gallery/picture fragment
        Log.d(TAG, "getIncomingIntent: New incoming imgUrl");
        if(intent.getStringExtra(getString(R.string.return_to_fragment)).equals(getString(R.string.edit_profile))) {

            if (intent.hasExtra(getString(R.string.selected_image))) {
                //set new profile pic
                FirebaseMethods firebaseMethods = new FirebaseMethods(AccountSettingsActivity.this);
                firebaseMethods.uploadNewPicture(getString(R.string.profile_picture), null, 0,
                        intent.getStringExtra(getString(R.string.selected_image)), null);
            } else if (intent.hasExtra(getString(R.string.selected_bitmap))) {
                //set new profile pic
                FirebaseMethods firebaseMethods = new FirebaseMethods(AccountSettingsActivity.this);
                firebaseMethods.uploadNewPicture(getString(R.string.profile_picture), null, 0,
                        null, (Bitmap) intent.getParcelableExtra(getString(R.string.selected_bitmap)));
            }

        }

        }
        if(intent.hasExtra(getString(R.string.calling_activity))){
            //received intent sets viewpager to edit profile fragment
            setViewPager(pagerAdapter.getFragmentNumber(getString(R.string.edit_profile)));
        }
    }

    private void setupFragments(){
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(),getString(R.string.edit_profile));
        pagerAdapter.addFragment(new SignOutFragment(),getString(R.string.sign_out));
    }

    //Method responsible for navigation to fragment
    public void setViewPager(int fragmentNumber){
        mRelativeLayout.setVisibility(View.GONE);
        mViewPager.setAdapter(pagerAdapter); //This will instantiate the viewpager
        mViewPager.setCurrentItem(fragmentNumber); //navigates to any fragment user chooses
    }

    private void setupSettingsList(){
        ListView listView = (ListView) findViewById(R.id.lvAccountSettings);

        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile));
        options.add(getString(R.string.sign_out));

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // navigating to fragment number position
                setViewPager(position);
            }
        });
    }

    //Bottom navigation view setup
    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }
}
