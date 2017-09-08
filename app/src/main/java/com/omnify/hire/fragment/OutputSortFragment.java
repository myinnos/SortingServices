package com.omnify.hire.fragment;

/**
 * Created by myinnos on 08/09/17.
 */

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omnify.hire.R;
import com.omnify.hire.extras.AppConstants;
import com.omnify.hire.services.ServiceMergeSort;
import com.omnify.hire.services.ServiceQuickSort;

import java.util.Arrays;
import java.util.List;


public class OutputSortFragment extends Fragment {

    private View rootView;
    private TextView tvQuickSort, tvMergeSort;

    public static OutputSortFragment newInstance() {
        return new OutputSortFragment();
    }

    @SuppressLint("UseValueOf")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_sort, container, false);

        tvQuickSort = (TextView) rootView.findViewById(R.id.tvQuickSort);
        tvMergeSort = (TextView) rootView.findViewById(R.id.tvMergeSort);

        startQuickSort();
        startMergeSort();

        return rootView;
    }

    private void startQuickSort() {
        ReceiverQuickSort myReceiver = new ReceiverQuickSort();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppConstants.QUICK_SORT_ACTION);
        getActivity().registerReceiver(myReceiver, intentFilter);
        Intent intent = new Intent(getActivity(),
                ServiceQuickSort.class);
        getActivity().startService(intent);
    }

    private class ReceiverQuickSort extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {

            String dataPassedForQuickSort = arg1.getStringExtra(AppConstants.QUICK_SORT_DATA);
            List<String> finalListQuickSort = Arrays.asList(dataPassedForQuickSort.split(","));
            for (int i = 0; i < finalListQuickSort.size(); i++) {
                tvQuickSort.append(finalListQuickSort.get(i) + "\n");
            }
        }

    }

    private void startMergeSort() {
        ReceiverMergeSort myReceiver = new ReceiverMergeSort();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppConstants.MERGE_SORT_ACTION);
        getActivity().registerReceiver(myReceiver, intentFilter);
        Intent intent = new Intent(getActivity(),
                ServiceMergeSort.class);
        getActivity().startService(intent);
    }

    private class ReceiverMergeSort extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {

            String dataPassedForMergeSort = arg1.getStringExtra(AppConstants.MERGE_SORT_DATA);
            List<String> finalListMergerSort = Arrays.asList(dataPassedForMergeSort.split(","));
            for (int i = 0; i < finalListMergerSort.size(); i++) {
                tvMergeSort.append(finalListMergerSort.get(i) + "\n");
            }
        }

    }

}