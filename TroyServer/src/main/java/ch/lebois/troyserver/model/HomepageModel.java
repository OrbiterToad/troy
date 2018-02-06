package ch.lebois.troyserver.model;

/**
 * Project: Hermann
 * Package: ch.lebois.troyserver.model
 **/
public class HomepageModel {
    private String client;
    private int logcount;
    private int errorcount;

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
}
