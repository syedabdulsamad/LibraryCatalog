package com.example.abdulsamad.librarycatalog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.abdulsamad.librarycatalog.Fragments.BooksFragment;
import com.example.abdulsamad.librarycatalog.Fragments.MoreFragment;
import com.example.abdulsamad.librarycatalog.Fragments.OrdersFragment;
import com.google.firebase.auth.FirebaseUser;

public class HomePageActivity extends AppCompatActivity {

    private ActionBar toolBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        FirebaseUser user = getIntent().getParcelableExtra("user");
        toolBar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.main_bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new BooksFragment());
        toolBar.setTitle("Shop");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_book:
                    toolBar.setTitle("Books");
                    loadFragment(new BooksFragment());
                    return true;

                case R.id.navigation_orders:
                    toolBar.setTitle("Orders");
                    loadFragment(new OrdersFragment());
                    return true;

                case R.id.navigation_more:
                    toolBar.setTitle("More");
                    loadFragment(new MoreFragment());
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);

    }
}
