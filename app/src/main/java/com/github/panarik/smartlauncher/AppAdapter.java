package com.github.panarik.smartlauncher;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AppAdapter extends BaseAdapter {

    Context context;
    List<AppObject> appList;

    ImageView mImage; //image for item_app
    TextView mLabel; //name for item_app
    LinearLayout mLayout; //layout for item_app






    public AppAdapter(Context context, List<AppObject> appList){
        this.context = context;
        this.appList = appList;

    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v;
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        v = layoutInflater.inflate(R.layout.item_app, parent, false);
        }else {
            v = convertView;
        }

        mImage = v.findViewById(R.id.app_imageView);
        mLabel = v.findViewById(R.id.app_Label);
        mLayout = v.findViewById(R.id.app_layout);

        mImage.setImageDrawable(appList.get(position).getImage());
        mLabel.setText(appList.get(position).getName());

        //делаем listener на ArrayList
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //создаём интент с выбранной App в адаптере
                Intent launchAppIntent = context.getPackageManager().getLaunchIntentForPackage(
                        appList.get(position).getPackageName() //вытаскиваем app package
                );
                //запускаем выбранный интент, если приложение существует
                if (launchAppIntent != null) {
                    context.startActivity(launchAppIntent);
                }
            }
        });

        return v;
    }
}
