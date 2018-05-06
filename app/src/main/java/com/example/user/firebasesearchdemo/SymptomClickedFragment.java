package com.example.user.firebasesearchdemo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class SymptomClickedFragment extends Fragment implements View.OnKeyListener {

    private RecyclerView mResultList;
    private DatabaseReference mUserDatabase;
    public static String content;
    String symp;

    public SymptomClickedFragment() {
        //Required empty constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                getFragmentManager().popBackStackImmediate();
                return true;
            }
        }

        return false;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Medicine");

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_symptom_clicked, container, false);
        symp = getArguments().getString("name");
        mResultList = (RecyclerView) view.findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);

        mResultList.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseUserSearch(symp);
        return view;

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void firebaseUserSearch(String searchText) {

        Toast.makeText(getContext(), "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild(symp).equalTo("true");

        FirebaseRecyclerAdapter<Medi, SymptomClickedFragment.UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Medi, SymptomClickedFragment.UsersViewHolder>(

                Medi.class,
                R.layout.list_layout,
                SymptomClickedFragment.UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(SymptomClickedFragment.UsersViewHolder viewHolder, Medi model, int position) {


                viewHolder.setDetails(getContext(), model.getName(), model.getBrand(), model.getContent(), model.getPrice(), model.getImage());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }
    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);
            final TextView medi_name = (TextView) itemView.findViewById(R.id.mediname);
            //TextView medi_brand = (TextView) mView.findViewById(R.id.medibrand);
            TextView medi_content = (TextView) itemView.findViewById(R.id.medicontent);
            //TextView medi_price = (TextView) mView.findViewById(R.id.mediprice);
            ImageView medi_image = (ImageView) itemView.findViewById(R.id.profileImage);
            CardView cv = (CardView) itemView.findViewById(R.id.card_view);

            medi_name.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Log.d("RecyclerView", "onClick：" + getAdapterPosition());
                    String a = medi_name.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", a);
                    Fragment fragment = new ResultFragment();
                    fragment.setArguments(bundle);
                    //loadFragment(fragment);
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();


                }
            });

            medi_content.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Log.d("RecyclerView", "onClick：" + getAdapterPosition());

                    String a = medi_name.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", a);
                    Fragment fragment = new ResultFragment();
                    fragment.setArguments(bundle);
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });

            cv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Log.d("RecyclerView", "onClick：" + getAdapterPosition());

                    String a = medi_name.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", a);
                    Fragment fragment = new ResultFragment();
                    fragment.setArguments(bundle);
                    //loadFragment(fragment);
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });

            medi_image.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Log.d("RecyclerView", "onClick：" + getAdapterPosition());
                    String a = medi_name.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", a);
                    Fragment fragment = new ResultFragment();
                    fragment.setArguments(bundle);
                    //loadFragment(fragment);
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("RecyclerView", "onClick：" + getAdapterPosition());
                    String a = medi_name.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", a);
                    Fragment fragment = new ResultFragment();
                    fragment.setArguments(bundle);
                    //loadFragment(fragment);
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });

            mView = itemView;

        }

        public void setDetails(Context ctx, String mediName, String mediBrand, String mediContent, Double mediPrice,
                               String mediImage) {

            TextView medi_name = (TextView) mView.findViewById(R.id.mediname);
            //TextView medi_brand = (TextView) mView.findViewById(R.id.medibrand);
            TextView medi_content = (TextView) mView.findViewById(R.id.medicontent);
            //TextView medi_price = (TextView) mView.findViewById(R.id.mediprice);
            ImageView medi_image = (ImageView) mView.findViewById(R.id.profileImage);

            String price = String.valueOf(mediPrice);
            content = mediContent;

            medi_name.setText(mediName);
            //medi_brand.setText("Brand : "+mediBrand);
            medi_content.setText("Content : " +mediContent);
            //medi_price.setText("Price per Tablet : "+price);

            Glide.with(ctx).load(mediImage).into(medi_image);


        }

    }
}