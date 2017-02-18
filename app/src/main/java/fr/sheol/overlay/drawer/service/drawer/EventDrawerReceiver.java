package fr.sheol.overlay.drawer.service.drawer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import fr.sheol.overlay.drawer.DataReceiver;

/**
 * Created by Sheol on 16/02/2017.
 */
public class EventDrawerReceiver extends BroadcastReceiver {
    private DrawerReceiverListener drawerReceiverListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Intent.ACTION_CLOSE_SYSTEM_DIALOGS:
                drawerReceiverListener.onHomeKeyListener();
                break;
            case DataReceiver.REMOTE_OPEN:
                drawerReceiverListener.onRemoteOpen();
                break;
        }
    }

    public void setDrawerReceiverListener(DrawerReceiverListener drawerReceiverListener) {
        this.drawerReceiverListener = drawerReceiverListener;
    }

    interface DrawerReceiverListener {
        void onHomeKeyListener();

        void onRemoteOpen();
    }
}
