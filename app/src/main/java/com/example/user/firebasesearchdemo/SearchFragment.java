package com.example.user.firebasesearchdemo;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText mSearchField;
    private android.support.v7.widget.AppCompatImageButton mSearchBtn;
  /*  private Button mSubstituteBtn;
    private Button mContentBtn;
    private Button mBrandBtn;*/

    private RecyclerView mResultList;
    private DatabaseReference mUserDatabase;
   // private Button onPrice;

    public static String content;
    // TODO: Rename and change types of parameters
    /*private String mParam1;
    private String mParam2;*/



    public SearchFragment() {
        // Required empty public constructor
    }

   /* public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

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
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        mSearchField = (EditText) view.findViewById(R.id.search_field);
        //onPrice = (Button) view.findViewById(R.id.onPrice);
        mSearchBtn = (android.support.v7.widget.AppCompatImageButton) view.findViewById(R.id.search_btn);
        //mSubstituteBtn = (Button) view.findViewById(R.id.substitute);
       // mContentBtn = (Button) view.findViewById(R.id.content);
       // mBrandBtn = (Button) view.findViewById(R.id.brand);


        mResultList = (RecyclerView) view.findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);

        mResultList.setLayoutManager(new LinearLayoutManager(getContext()));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });

      /*  mSubstituteBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                String searchText = content;

                firebaseUserSearchSubstitute(searchText);

            }
        });

        mContentBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearchContent(searchText);

            }
        });

        mBrandBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearchBrand(searchText);

            }
        });*/

        /*onPrice.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearchPrice(searchText);

            }
        });*/

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void firebaseUserSearch(String searchText) {

        Toast.makeText(getContext(), "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("Name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Medi, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Medi, UsersViewHolder>(

                Medi.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Medi model, int position) {


                viewHolder.setDetails(getContext(), model.getName(), model.getBrand(), model.getContent(), model.getPrice(), model.getImage());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }


   /* @RequiresApi(api = Build.VERSION_CODES.M)
    private void firebaseUserSearchPrice(String searchText) {

        Toast.makeText(getContext(), "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery;
        firebaseSearchQuery = mUserDatabase.orderByChild("Name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Medi, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Medi, UsersViewHolder>(

                Medi.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Medi model, int position) {


                viewHolder.setDetails(getContext(), model.getName(), model.getBrand(), model.getContent(), model.getPrice(), model.getImage());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }*/

/*
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void firebaseUserSearchBrand(String searchText) {

        Toast.makeText(getContext(), "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("Brand").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Medi, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Medi, UsersViewHolder>(

                Medi.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Medi model, int position) {


                viewHolder.setDetails(getContext(), model.getName(), model.getBrand(), model.getContent(), model.getPrice(), model.getImage());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void firebaseUserSearchContent(String searchText) {

        Toast.makeText(getContext(), "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("Content").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Medi, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Medi, UsersViewHolder>(

                Medi.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Medi model, int position) {


                viewHolder.setDetails(getContext(), model.getName(), model.getBrand(), model.getContent(), model.getPrice(), model.getImage());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }*/

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

        public void setDetails(Context ctx,  String mediName, String mediBrand, String mediContent, Double mediPrice,
                               String mediImage) {

            TextView medi_name = (TextView) mView.findViewById(R.id.mediname);
            //TextView medi_brand = (TextView) mView.findViewById(R.id.medibrand);
            TextView medi_content = (TextView) mView.findViewById(R.id.medicontent);
            //TextView medi_price = (TextView) mView.findViewById(R.id.mediprice);
            ImageView medi_image = (ImageView) mView.findViewById(R.id.profileImage);

            String price = String.valueOf(mediPrice);
            content = mediContent;

            medi_name.setText(""+mediName);
            //medi_brand.setText("Brand : "+mediBrand);
            medi_content.setText("Contains: "+mediContent);
            //medi_price.setText("Price per Tablet : "+price);

            Glide.with(ctx).load(mediImage).into(medi_image);


        }

    }

}

