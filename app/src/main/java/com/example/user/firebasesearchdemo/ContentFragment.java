package com.example.user.firebasesearchdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;

import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.support.v7.widget.AppCompatImageButton;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.firebasesearchdemo.Medi;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.example.user.firebasesearchdemo.R;

public class ContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText mSearchField;
    private android.support.v7.widget.AppCompatImageButton mSearchBtn;
    private Button mSubstituteBtn;
    private Button mContentBtn;
    private Button mBrandBtn;

    private RecyclerView mResultList;
    private DatabaseReference mUserDatabase;

    public static String content;
    // TODO: Rename and change types of parameters
    /*private String mParam1;
    private String mParam2;*/



    public ContentFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Medicine");



    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        mSearchField = (EditText) view.findViewById(R.id.search_field);
        mSearchBtn = (android.support.v7.widget.AppCompatImageButton) view.findViewById(R.id.search_btn);
        //mSubstituteBtn = (Button) view.findViewById(R.id.substitute);
        //mContentBtn = (Button) view.findViewById(R.id.content);
        //mBrandBtn = (Button) view.findViewById(R.id.brand);


        mResultList = (RecyclerView) view.findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);

        mResultList.setLayoutManager(new LinearLayoutManager(getContext()));
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearchContent(searchText);

            }
        });
        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void firebaseUserSearchContent(String searchText) {

        Toast.makeText(getContext(), "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("Content").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Medi, SearchFragment.UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Medi, SearchFragment.UsersViewHolder>(

                Medi.class,
                R.layout.list_layout,
                SearchFragment.UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(SearchFragment.UsersViewHolder viewHolder, Medi model, int position) {


                viewHolder.setDetails(getContext(), model.getName(), model.getBrand(), model.getContent(), model.getPrice(),model.getImage());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String mediName,String mediBrand, String mediContent, Double mediPrice, String mediImage) {

            TextView medi_name = (TextView) mView.findViewById(R.id.mediname);
            //TextView medi_brand = (TextView) mView.findViewById(R.id.medibrand);
            TextView medi_content = (TextView) mView.findViewById(R.id.medicontent);
            //TextView medi_price = (TextView) mView.findViewById(R.id.mediprice);
            ImageView medi_image = (ImageView) mView.findViewById(R.id.profileImage);

            String price = String.valueOf(mediPrice);
            content = mediContent;

            medi_name.setText("Medicine Name : "+mediName);
            //medi_brand.setText("Brand : "+mediBrand);
            medi_content.setText("Content : " +mediContent);
            //medi_price.setText("Price per Tablet : "+price);

            Glide.with(ctx).load(mediImage).into(medi_image);


        }


    }
}
