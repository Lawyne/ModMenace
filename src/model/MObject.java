package model;

public abstract class MObject {

    protected String name;

    public abstract String getName();
    public abstract void setName(String name);
    public abstract boolean isEntity();
    public abstract boolean isLink();

}
