package fr.sheol.overlay.drawer.service.drawer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.sheol.overlay.drawer.service.drawer.list.AppObj;
import fr.sheol.overlay.drawer.service.drawer.list.ImageGrid;

/**
 * Created by Sheol on 16/02/2017.
 */
public class AppData implements AdapterView.OnItemClickListener {
    private List<AppObj> data = new ArrayList<>();
    private Context context;
    private ApplicationListener applicationListener;

    public AppData(Context context, GridView gridView, ApplicationListener applicationListener) {
        this.context = context;
        this.applicationListener = applicationListener;
        fillAdapter();
        ImageGrid imageGrid = new ImageGrid(data);
        gridView.setAdapter(imageGrid);
        gridView.setOnItemClickListener(this);
    }

    private void fillAdapter() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = context.getPackageManager()
                .queryIntentActivities(mainIntent, 0);
        for (ResolveInfo resolveInfo : pkgAppsList) {
            data.add(new AppObj(String.valueOf(resolveInfo.loadLabel(context.getPackageManager())),
                    resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name,
                    resolveInfo.loadIcon(context.getPackageManager())));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AppObj appObj = data.get(position);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName(appObj.getPackageName(), appObj.getClassName());
        applicationListener.onApplicationLaunched();
        context.startActivity(intent);
    }

    interface ApplicationListener {
        void onApplicationLaunched();
    }
}
