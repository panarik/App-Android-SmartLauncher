package com.github.panarik.smartlauncher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ititializeDrawer();

    }

    List<AppObject> installedAppList = new ArrayList<>();

    private void ititializeDrawer() {

        View myBottomSheet = findViewById(R.id.main_buttonSheet); //layout
        final GridView mDrawerGridView = findViewById(R.id.main_drawerGridView);
        final BottomSheetBehavior mBottomSheetBehavior = BottomSheetBehavior.from(myBottomSheet);

        mBottomSheetBehavior.setHideable(false);
        mBottomSheetBehavior.setPeekHeight(300);

        //получаем установленные приложения
        installedAppList = getInstalledAppList();

        mDrawerGridView.setAdapter(new AppAdapter(getApplicationContext(), installedAppList));

        //BottomSheetBehavior возвращается обратно в исходное положение
        mBottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                //если свернуто
                if (newState == BottomSheetBehavior.STATE_HIDDEN //starts collapse
                        &&
                        mDrawerGridView
                                .getChildAt(0) //first app
                                .getY() //position
                                !=0 //different from NULL OR already exist
                )
                {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED); //продолжаем разворачивать виджет
                }

                //если в движении
                if (newState == BottomSheetBehavior.STATE_DRAGGING //ahead dragging
                        &&
                        mDrawerGridView
                                .getChildAt(0) //first app
                                .getY() //position
                                !=0 //different from NULL OR already exist
                )
                {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED); //продолжаем разворачивать виджет
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    //получаем image, name и package всех приложений и формируем ArrayList из них
    private List<AppObject> getInstalledAppList() {
        List<AppObject> list = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        //выбираем категорию приложений
        intent.addCategory(Intent.CATEGORY_LAUNCHER); //выбираем все виды
        //формируем необработанные приложения
        List<ResolveInfo> untreatedAppList = getApplicationContext()
                .getPackageManager().queryIntentActivities(intent, 0);

        //перебираем все "сырые" приложения
        for (ResolveInfo untreatedApp : untreatedAppList) {
            String appName = untreatedApp.activityInfo.loadLabel(getPackageManager()).toString();
            String appPackageName = untreatedApp.activityInfo.packageName;
            Drawable appImage = untreatedApp.activityInfo.loadIcon(getPackageManager());

            //собираем объект с приложениями
            AppObject app = new AppObject(appName, appPackageName, appImage);

            //собираем ArrayList с объектом для каждого приложения (дубликаты приложений не попадают в ArrayList)
            if (!list.contains(app)) {
                list.add(app);
            }
        }

        //метод возвращает ArrayList с установленными приложениями
        return list;
    }
}