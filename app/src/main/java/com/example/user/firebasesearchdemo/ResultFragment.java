package com.example.user.firebasesearchdemo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hp on 30-03-2018.
 */

public class ResultFragment extends Fragment implements View.OnKeyListener{

    String name,image,cont,brand,desc,precau,seffects,conts;
    Double price;
    private DatabaseReference mUserDatabase;
    private Button mSubstituteBtn;


    public ResultFragment() {
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Medicine");
        name = getArguments().getString("name");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_detail, container, false);
        name = getArguments().getString("name");
        TextView medi_name = (TextView) view.findViewById(R.id.primary_text);
        final TextView content = (TextView) view.findViewById(R.id.sub_text);
        final TextView description = (TextView) view.findViewById(R.id.TextDetail);
        final TextView priceText = (TextView) view.findViewById(R.id.TextDetail1);
        final TextView brandText = (TextView) view.findViewById(R.id.TextDetailM);
        final ImageView medi_image = (ImageView) view.findViewById(R.id.media_image);
        final TextView prec = (TextView) view.findViewById(R.id.TextDetail2);
        final TextView sdef = (TextView) view.findViewById(R.id.TextDetail3);
        mSubstituteBtn = (Button) view.findViewById(R.id.onclick);
        medi_name.setText(name);
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Medicine");
        Query firebaseSearchQuery = mUserDatabase.orderByChild("Name").equalTo(name);

        firebaseSearchQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot mdetails : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        Medi model = mdetails.getValue(Medi.class);
                        cont = model.getContent();
                        brand = model.getBrand();
                        desc = model.getDescription();
                        image = model.getImage();


                        precau = model.getPrecautions();
                        seffects = model.getSide_effects();
                        price = model.getPrice();


                        //Log.d("desc", "onClick：" + desc);
                        content.setText(cont);
                        priceText.setText(price.toString()+" ₹ per unit");
                        brandText.setText(brand);
                        description.setText(desc);
                        prec.setText(precau);
                        sdef.setText(seffects);

                        Glide.with(getActivity()).load(image).into(medi_image);

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mSubstituteBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                conts = content.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("name", conts);
                Fragment fragment = new SubstituteSetFragment();
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