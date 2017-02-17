package fr.sheol.overlay.drawer;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Sheol on 14/02/2017.
 */
public class MainActivity extends AppCompatActivity {
    public final static int PERM_REQUEST_CODE_DRAW_OVERLAYS = 42457;

    public void permissionToDrawOverlays() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, PERM_REQUEST_CODE_DRAW_OVERLAYS);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        permissionToDrawOverlays();
        DataReceiver.sendActivityEnableEvent(getBaseContext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PERM_REQUEST_CODE_DRAW_OVERLAYS) {
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    // TODO: 16/02/2017 show windows to explain why overlay perm do enabled
                } else {
                    DataReceiver.sendOverlayEnableEvent(getBaseContext());
                }
            }
        }
    }
}
