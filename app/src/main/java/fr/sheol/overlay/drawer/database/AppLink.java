package fr.sheol.overlay.drawer.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by teddy on 20/02/2017.
 */
@Entity
public class AppLink {
    @NotNull
    private int app_id;
    private int profile_id;

    @Generated(hash = 180429250)
    public AppLink(int app_id, int profile_id) {
        this.app_id = app_id;
        this.profile_id = profile_id;
    }

    @Generated(hash = 1726685947)
    public AppLink() {
    }

    public int getApp_id() {
        return this.app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public int getProfile_id() {
        return this.profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }
}
