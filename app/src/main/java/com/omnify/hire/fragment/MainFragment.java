package com.omnify.hire.fragment;

/**
 * Created by myinnos on 08/09/17.
 */

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omnify.hire.extras.AppConstants;
import com.omnify.hire.R;
import com.omnify.hire.extras.Remember;

import java.util.ArrayList;
import java.util.Collections;


public class MainFragment extends Fragment {

    private View rootView;
    private TextView tvRandomNumbers;
    private int maxValue = 99;
    private int minValue = 10;
    private int maxCount = 20;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @SuppressLint("UseValueOf")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_main, container, false);

        tvRandomNumbers = (TextView) rootView.findViewById(R.id.tvRandomNumbers);

        generateRandomNumbers();

        return rootView;
    }

    public void generateRandomNumbers() {

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = minValue; i < maxValue + 1; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        String randomNumbers = "";
        for (int i = 0; i < maxCount; i++) {
            if (randomNumbers.equals("")) {
                randomNumbers = String.valueOf(list.get(i));
            } else {
                randomNumbers = randomNumbers + ", " + String.valueOf(list.get(i));
            }
        }

        Remember.putString(AppConstants.RANDOM_NUMBERS, randomNumbers);
        tvRandomNumbers.setText(randomNumbers);
    }
}