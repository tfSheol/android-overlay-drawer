package fr.sheol.overlay.drawer.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by teddy on 20/02/2017.
 */
@Entity(indexes = {
        @Index(value = "name_app DESC", unique = true)
})
public class App {
    @Id
    private int id;

    @NotNull
    private String name_app;
    private String launcher_path;
    private boolean pinned;

    @Generated(hash = 1024277967)
    public App(int id, @NotNull String name_app, String launcher_path,
               boolean pinned) {
        this.id = id;
        this.name_app = name_app;
        this.launcher_path = launcher_path;
        this.pinned = pinned;
    }

    @Generated(hash = 407064589)
    public App() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_app() {
        return this.name_app;
    }

    public void setName_app(String name_app) {
        this.name_app = name_app;
    }

    public String getLauncher_path() {
        return this.launcher_path;
    }

    public void setLauncher_path(String launcher_path) {
        this.launcher_path = launcher_path;
    }

    public boolean getPinned() {
        return this.pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }
}
