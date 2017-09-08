package com.omnify.hire.services;

/**
 * Created by myinnos on 08/09/17.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.omnify.hire.extras.AppConstants;
import com.omnify.hire.extras.Helper;
import com.omnify.hire.extras.Remember;

import java.util.Arrays;
import java.util.List;

public class ServiceQuickSort extends Service {

    int mStartMode;
    IBinder mBinder;
    boolean mAllowRebind;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String finalSort = Remember.getString(AppConstants.RANDOM_NUMBERS, "").trim();
        List<String> finalList = Arrays.asList(finalSort.split(","));
        int[] arr = Helper.buildIntArray(finalList);

        quickSort(arr, 0, finalList.size() - 1);
        System.out.println(Arrays.toString(arr));

        String quickSortNumbers = "";
        for (int anArr : arr) {
            quickSortNumbers = quickSortNumbers + ", " + String.valueOf(anArr);
        }

        Intent intent1 = new Intent();
        intent1.setAction(AppConstants.QUICK_SORT_ACTION);
        intent1.putExtra(AppConstants.QUICK_SORT_DATA, quickSortNumbers);
        sendBroadcast(intent1);

        return mStartMode;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return mAllowRebind;
    }

    @Override
    public void onRebind(Intent intent) {

    }

    @Override
    public void onDestroy() {

    }


    public static void quickSort(int[] arr, int low, int high) {

        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;

        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j)
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);
    }

}
