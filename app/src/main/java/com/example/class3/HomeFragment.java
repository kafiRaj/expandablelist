package com.example.class3;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    private TextView textView;

    View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_home, container, false);

        textView = mainView.findViewById(R.id.homeTitleId);


         return mainView;
    }

}
