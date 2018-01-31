package ch.lebois.troyserver.model;

/**
 * @author Felix
 * @date 31.01.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyserver.model
 **/
public class DashboardModel {

    private String commands;
    private String log;
    private String client;

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

}
