package fr.sheol.overlay.drawer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sheol on 16/02/2017.
 */
public class AppData implements AdapterView.OnItemClickListener {
    private List<String> data = new ArrayList<>();
    private Context context;
    private List<ResolveInfo> pkgAppsList;
    private ApplicationListener applicationListener;

    public AppData(Context context, ListView listView, ApplicationListener applicationListener) {
        this.context = context;
        this.applicationListener = applicationListener;
        fillAdapter();
        listView.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, data));
        listView.setOnItemClickListener(this);
    }

    private void fillAdapter() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        pkgAppsList = context.getPackageManager().queryIntentActivities(mainIntent, 0);
        for (ResolveInfo resolveInfo : pkgAppsList) {
            data.add(String.valueOf(resolveInfo.loadLabel(context.getPackageManager())));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ResolveInfo resolveInfo = pkgAppsList.get(position);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        applicationListener.onApplicationLaunched();
        context.startActivity(intent);
    }

    interface ApplicationListener {
        void onApplicationLaunched();
    }
}
