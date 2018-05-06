package com.example.user.firebasesearchdemo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * Created by hp on 02-04-2018.
 */

public class SymptomFragment extends Fragment {

    private DatabaseReference mUserDatabase;
    public static String content;
    private CardView cv1,cv2,cv3,cv4,cv5,cv6,cv7,cv8;
    public SymptomFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Medicine");

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_symptom, container, false);
        cv1 = (CardView) view.findViewById(R.id.cv1);
        cv2 = (CardView) view.findViewById(R.id.cv2);
        cv3 = (CardView) view.findViewById(R.id.cv3);
        cv4 = (CardView) view.findViewById(R.id.cv4);
        cv5 = (CardView) view.findViewById(R.id.cv5);
        cv6 = (CardView) view.findViewById(R.id.cv6);
        cv7 = (CardView) view.findViewById(R.id.cv7);
        cv8 = (CardView) view.findViewById(R.id.cv8);

        cv1.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override public void onClick(View view){
                Bundle bundle = new Bundle();
                bundle.putString("name", "Cold");
                Fragment fragment = new SymptomClickedFragment();
                fragment.setArguments(bundle);
                //loadFragment(fragment);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });
        cv2.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override public void onClick(View view){
                Bundle bundle = new Bundle();
                bundle.putString("name", "Heart Attack");
                Fragment fragment = new SymptomClickedFragment();
                fragment.setArguments(bundle);
                //loadFragment(fragment);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        cv3.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override public void onClick(View view){
                Bundle bundle = new Bundle();
                bundle.putString("name", "Pain");
                Fragment fragment = new SymptomClickedFragment();
                fragment.setArguments(bundle);
                //loadFragment(fragment);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        cv4.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override public void onClick(View view){
                Bundle bundle = new Bundle();
                bundle.putString("name", "Fever");
                Fragment fragment = new SymptomClickedFragment();
                fragment.setArguments(bundle);
                //loadFragment(fragment);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        cv5.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override public void onClick(View view){
                Bundle bundle = new Bundle();
                bundle.putString("name", "Bacterial infection");
                Fragment fragment = new SymptomClickedFragment();
                fragment.setArguments(bundle);
                //loadFragment(fragment);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        cv6.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override public void onClick(View view){
                Bundle bundle = new Bundle();
                bundle.putString("name", "Typhoid");
                Fragment fragment = new SymptomClickedFragment();
                fragment.setArguments(bundle);
                //loadFragment(fragment);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        cv7.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override public void onClick(View view){
                Bundle bundle = new Bundle();
                bundle.putString("name", "Itching");
                Fragment fragment = new SymptomClickedFragment();
                fragment.setArguments(bundle);
                //loadFragment(fragment);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        cv8.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override public void onClick(View view){
                Bundle bundle = new Bundle();
                bundle.putString("name", "Headache");
                Fragment fragment = new SymptomClickedFragment();
                fragment.setArguments(bundle);
                //loadFragment(fragment);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        return view;
    }

}