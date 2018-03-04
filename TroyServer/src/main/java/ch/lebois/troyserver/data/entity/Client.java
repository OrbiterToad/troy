package ch.lebois.troyserver.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Project: Hermann
 **/


@Entity
public class Client {

    @Id
    private String pcName;
    private String os;
    private String lastseen;
    private String info;
    private String commands;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

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

    public String getLastseen() {
        return lastseen;
    }

    public void setLastseen(String lastseen) {
        this.lastseen = lastseen;
    }
}

