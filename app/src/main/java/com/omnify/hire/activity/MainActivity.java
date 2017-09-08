package com.omnify.hire.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.omnify.hire.R;
import com.omnify.hire.fragment.MainFragment;
import com.omnify.hire.fragment.OutputSortFragment;

public class MainActivity extends AppCompatActivity {

    private Button btSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btReGenerate = (Button) findViewById(R.id.btReGenerate);
        btSort = (Button) findViewById(R.id.btSort);

        // setting basic fragment
        setFragment(MainFragment.newInstance());

        btReGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(MainFragment.newInstance());
            }
        });

        btSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(OutputSortFragment.newInstance());
            }
        });
    }

    public void setFragment(Fragment selectedFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
    }
}
