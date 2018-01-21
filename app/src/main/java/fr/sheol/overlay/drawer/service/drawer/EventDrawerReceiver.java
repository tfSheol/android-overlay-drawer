package fr.sheol.overlay.drawer.service.drawer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.sheol.overlay.drawer.DataReceiver;

/**
 * Created by Sheol on 16/02/2017.
 */
public class EventDrawerReceiver extends BroadcastReceiver {
    private DrawerReceiverListener drawerReceiverListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction() != null ? intent.getAction()
                : Intent.ACTION_CLOSE_SYSTEM_DIALOGS) {
            case Intent.ACTION_CLOSE_SYSTEM_DIALOGS:
                drawerReceiverListener.onHomeKeyListener();
                break;
            case DataReceiver.REMOTE_OPEN:
                drawerReceiverListener.onRemoteOpen();
                break;
            case Intent.ACTION_SCREEN_OFF:
                drawerReceiverListener.onScreenOff();
                break;
            case Intent.ACTION_CALL:
                drawerReceiverListener.onCall();
                break;
            default:
                // TODO: 04/03/2017 find how close drawer when notification was clicked!
                break;
        }
    }

    public void setDrawerReceiverListener(DrawerReceiverListener drawerReceiverListener) {
        this.drawerReceiverListener = drawerReceiverListener;
    }

    interface DrawerReceiverListener {
        void onNotificationClicked();

        void onCall();

        void onScreenOff();

        void onHomeKeyListener();

        void onRemoteOpen();
    }
}
