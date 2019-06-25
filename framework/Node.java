package dankmemer.dependencies.framework;

public abstract class Node {

    public abstract boolean validate();

    public abstract void execute();

    public abstract Priority priority();

    public abstract String getName();

}