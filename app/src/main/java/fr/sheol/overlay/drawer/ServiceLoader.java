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
        if (!isMyServiceRunning("sidebar")) {
            context.startService(new Intent(context, SidebarService.class));
        }
    }

    public void startService(Class<?> service) {
        if (!isMyServiceRunning(service.getSimpleName().replace("Service", "").toLowerCase())) {
            context.startService(new Intent(context, service));
        }
    }

    @SuppressWarnings("all")
    public static boolean checkOverlayPerm(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(context);
        }
        return true;
    }

    private boolean isMyServiceRunning(String serviceName) {
        final String servicePackage = context.getPackageName() + ":" + serviceName;
        final ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningAppProcessInfo runningApp : manager.getRunningAppProcesses()) {
                if (servicePackage.equals(runningApp.processName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
