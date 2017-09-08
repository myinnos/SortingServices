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

public class ServiceMergeSort extends Service {

    int mStartMode;
    IBinder mBinder;
    boolean mAllowRebind;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String finalSort = Remember.getString(AppConstants.RANDOM_NUMBERS, "").trim();
        List<String> finalList = Arrays.asList(finalSort.split(","));
        int[] arr = Helper.buildIntArray(finalList);
        int[] tempArr = Helper.buildIntArray(finalList);

        mergeSort(arr, tempArr, 0, finalList.size() - 1);
        System.out.println(Arrays.toString(arr));

        String quickSortNumbers = "";
        for (int anArr : arr) {
            quickSortNumbers = quickSortNumbers + ", " + String.valueOf(anArr);
        }

        Intent intent1 = new Intent();
        intent1.setAction(AppConstants.MERGE_SORT_ACTION);
        intent1.putExtra(AppConstants.MERGE_SORT_DATA, quickSortNumbers);
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


    private static void mergeSort(int[] data, int[] tempArray, int low, int high) {
        /** If low is smaller than high, array is not sorted, otherwise it is. */
        if (low < high) {

            int middle = (low + high) / 2;

            System.out.println(" Divide Step -  low:" + low + " middle:" + middle + " high:" + high);

            /** Recursively Sort the left side of the array **/
            mergeSort(data, tempArray, low, middle);

            /** Recursively Sort the right side of the array **/
            mergeSort(data, tempArray, middle + 1, high);

            /** Merge the sorted arrays **/
            merge(data, tempArray, low, middle, high);
        }
    }

    private static void merge(int[] data, int[] tempArray, int low, int middle, int high) {

        // Copy both parts into the temporary helper array
        for (int i = low; i <= high; i++) {
            tempArray[i] = data[i];
        }

        int i = low; // Start of Left Array
        int j = middle + 1; // Start of right Array
        int k = low;

        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (tempArray[i] <= tempArray[j]) {
                data[k] = tempArray[i];
                i++;
            } else {
                data[k] = tempArray[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            data[k] = tempArray[i];
            k++;
            i++;
        }
    }

}
