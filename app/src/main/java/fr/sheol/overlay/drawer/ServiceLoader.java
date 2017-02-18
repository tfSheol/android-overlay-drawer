package fr.sheol.overlay.drawer;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import fr.sheol.overlay.drawer.service.drawer.SidebarService;

/**
 * Created by Sheol on 16/02/2017.
 */
public class ServiceLoader extends Thread {
    private Context context;

    public ServiceLoader(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        super.run();
        if (checkOverlayPerm(context)) {
            startSidebarService();
        }
    }

    private void startSidebarService() {
        if (!isMyServiceRunning(SidebarService.class)) {
            context.startService(new Intent(context, SidebarService.class));
        }
    }

    public static boolean checkOverlayPerm(Context context) {
        //noinspection SimplifiableIfStatement
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(context);
        }
        return true;
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
