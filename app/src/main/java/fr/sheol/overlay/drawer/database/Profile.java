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
        @Index(value = "name DESC", unique = true)
})
public class Profile {
    @Id
    private int id;

    @NotNull
    private String name;
    private int trigger_id;

    @Generated(hash = 597641313)
    public Profile(int id, @NotNull String name, int trigger_id) {
        this.id = id;
        this.name = name;
        this.trigger_id = trigger_id;
    }

    @Generated(hash = 782787822)
    public Profile() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrigger_id() {
        return this.trigger_id;
    }

    public void setTrigger_id(int trigger_id) {
        this.trigger_id = trigger_id;
    }
}
