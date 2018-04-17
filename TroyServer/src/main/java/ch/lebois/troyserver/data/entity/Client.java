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

//    private String pcNickname;
//    private String os;
//    private String ip;
//    private String pcuser;
//    private String arch;
//    private String lastseen;
//    private String refreshtime;
//    private String commands;

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }
}

