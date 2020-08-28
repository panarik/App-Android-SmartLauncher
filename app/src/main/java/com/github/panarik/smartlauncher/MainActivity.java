package com.github.panarik.smartlauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    View myBottomSheet;
    GridView mDgawerGridView;
    BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ititializeDrawer();

    }

    List<AppObject> appList = new ArrayList<>();

    private void ititializeDrawer() {

        myBottomSheet = findViewById(R.id.main_buttonSheet);
        mDgawerGridView = findViewById(R.id.main_drawerGridView);
        mBottomSheetBehavior = BottomSheetBehavior.from(myBottomSheet);

        mBottomSheetBehavior.setHideable(false);
        mBottomSheetBehavior.setPeekHeight(300);

        for (int i = 0; i < 20; i++)
            appList.add(new AppObject("", String.valueOf(i), ContextCompat.getDrawable(this, R.mipmap.ic_launcher)));

        mDgawerGridView.setAdapter(new AppAdapter(getApplicationContext(), appList));

    }
}