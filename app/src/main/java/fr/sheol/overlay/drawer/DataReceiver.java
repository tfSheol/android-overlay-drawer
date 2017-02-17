package fr.sheol.overlay.drawer;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Sheol on 16/02/2017.
 */
public class DataReceiver extends BroadcastReceiver {
    private static final String ACTIVITY_ENABLE = "fr.sheol.overlay.drawer.ACTIVITY_ENABLE";
    private static final String OVERLAY_PERM = "fr.sheol.overlay.drawer.OVERLAY_PERM";

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case BluetoothAdapter.ACTION_STATE_CHANGED:
            case Intent.ACTION_BOOT_COMPLETED:
            case Intent.ACTION_USER_INITIALIZE:
            case ACTIVITY_ENABLE:
            case OVERLAY_PERM:
                enableServiceLoader(context);
                break;
            case Intent.ACTION_CLOSE_SYSTEM_DIALOGS:
                break;
        }
    }

    private void enableServiceLoader(Context context) {
        new ServiceLoader(context).run();
    }

    public static void sendActivityEnableEvent(Context context) {
        sendEvent(context, ACTIVITY_ENABLE);
    }

    public static void sendOverlayEnableEvent(Context context) {
        sendEvent(context, OVERLAY_PERM);
    }

    private static void sendEvent(Context context, String event) {
        Intent startEvent = new Intent(event);
        context.sendBroadcast(startEvent);
    }
}
