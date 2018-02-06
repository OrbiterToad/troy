package ch.lebois.troyserver.data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Felix
 * @date 05.02.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyserver.data
 **/


@Entity
public class Client {

    @Id
    private String pcName;

    private String info;
    private String commands;

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }
}

