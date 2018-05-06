package com.example.user.firebasesearchdemo;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.example.user.firebasesearchdemo.SearchFragment;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private BottomNavigationView mBottomNavigationView;
    private FloatingActionButton fab;
    private static final String HOME_FRAGMENT = "newsFragment";
    private static final String SEARCH_FRAGMENT = "searchFragment";
    private static final String CONTENT_FRAGMENT = "contentFragment";
    private boolean isFirstFragment;
    private long mBackPressedTime;
    String API_KEY = "b295a63910dc4efca623dd5b1b2e1b89";
    String NEWS_SOURCE = "medical-news-today" ;
    ListView listNews;
    ProgressBar loader;
    Deque<Integer> mStack = new ArrayDeque<>();
    boolean isBackPressed  = false;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";


    public static String content;

     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setFab();
        setBottomNavigationView();
    }

    private void setFab(){
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent in;
            in = new Intent(MainActivity.this , AlarmActivity.class);
            startActivity(in);
        }
    });
    }

    private void init() {
        mBottomNavigationView = findViewById(R.id.navigation);
        fab= findViewById(R.id.fab);
        mFragmentManager = getFragmentManager();
        mBackPressedTime = 0;
        Fragment fragment= new NewsFragment();
        //setFragment(fragment,HOME_FRAGMENT);
        loadFragment(fragment);
    }


    private void setBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if(!isBackPressed) {
                            pushFragmentIntoStack(R.id.navigation_home);
                        }
                        isBackPressed = false;
                        //setFragment(new NewsFragment(), HOME_FRAGMENT);

                        loadFragment(new NewsFragment());
                        return true;
                    case R.id.navigation_dashboard:
                        if(!isBackPressed) {
                            pushFragmentIntoStack(R.id.navigation_dashboard);
                        }
                        isBackPressed = false;
                        loadFragment(new SearchFragment());
                        return true;
                    case R.id.navigation_symptom:
                        if(!isBackPressed) {
                            pushFragmentIntoStack(R.id.navigation_symptom);
                        }
                        isBackPressed = false;
                        loadFragment(new SymptomFragment());
                        return true;

                    case R.id.navigation_contentSearch:
                        if(!isBackPressed) {
                            pushFragmentIntoStack(R.id.navigation_contentSearch);
                        }
                        isBackPressed = false;
                        loadFragment(new ContentFragment());
                        return true;

                    case R.id.navigation_notifications:
                        if(!isBackPressed) {
                            pushFragmentIntoStack(R.id.navigation_contentSearch);
                        }
                        isBackPressed = false;
                        Intent i;
                        i = new Intent(MainActivity.this , MapActivity.class);
                        startActivity(i);
                        return false;





                    default:
                        return false;
                }
            }
        });
        mBottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
            }
        });
        mBottomNavigationView.setSelectedItemId(R.id.navigation_home);
        pushFragmentIntoStack(R.id.navigation_home);
    }

    private void pushFragmentIntoStack(int id)
    {
        if(mStack.size() < 5)
        {
            mStack.push(id);
        }
        else
        {
            mStack.removeLast();
            mStack.push(id);
        }
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
   @Override
    public void onBackPressed() {
        if(mStack.size() > 1)
        {
            isBackPressed = true;
            mStack.pop();
            mBottomNavigationView.setSelectedItemId(mStack.peek());
        }
        else
        {
            super.onBackPressed();
        }
    }
}


