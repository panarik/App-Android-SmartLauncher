package com.github.panarik.smartlauncher;

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

    View myBottomSheet;
    GridView mDrawerGridView;
    BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ititializeDrawer();

    }

    List<AppObject> installedAppList = new ArrayList<>();

    private void ititializeDrawer() {

        myBottomSheet = findViewById(R.id.main_buttonSheet);
        mDrawerGridView = findViewById(R.id.main_drawerGridView);
        mBottomSheetBehavior = BottomSheetBehavior.from(myBottomSheet);

        mBottomSheetBehavior.setHideable(false);
        mBottomSheetBehavior.setPeekHeight(300);

        //получаем установленные приложения
        installedAppList = getInstalledAppList();

        mDrawerGridView.setAdapter(new AppAdapter(getApplicationContext(), installedAppList));

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