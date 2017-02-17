package fr.sheol.overlay.drawer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

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
        }
    }

    public void setDrawerReceiverListener(DrawerReceiverListener drawerReceiverListener) {
        this.drawerReceiverListener = drawerReceiverListener;
    }

    interface DrawerReceiverListener {
        void onHomeKeyListener();
    }
}
