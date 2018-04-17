package ch.lebois.troyserver.model;

/**
 * @author Felix
 * @date 11.04.2018
 * <p>
 * Project: ServerControl
 * Package: ch.lebois.troyserver.model
 **/
public class ClientModel {

    private String client;
    private String nickname;
    private String pcuser;

    private String command;

    private String os;
    private String ip;
    private String arch;

    private String lastseen;
    private String refreshtime;
    private String img;

    private int logcount;
    private int errorcount;

    public String getRefreshtime() {
        return refreshtime;
    }

    public void setRefreshtime(String refreshtime) {
        this.refreshtime = refreshtime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPcuser() {
        return pcuser;
    }

    public void setPcuser(String pcuser) {
        this.pcuser = pcuser;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getLastseen() {
        return lastseen;
    }

    public void setLastseen(String lastseen) {
        this.lastseen = lastseen;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getLogcount() {
        return logcount;
    }

    public void setLogcount(int logcount) {
        this.logcount = logcount;
    }

    public int getErrorcount() {
        return errorcount;
    }

    public void setErrorcount(int errorcount) {
        this.errorcount = errorcount;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
