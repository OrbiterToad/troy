package ch.lebois.troyclient.main;

/**
 * @author Felix
 * @date 13.03.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyclient.main
 **/
public abstract class Command {

    private final String command;

    public Command(String command) {

        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public abstract void run(String command);
}
