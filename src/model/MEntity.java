package model;

import java.util.LinkedList;

public class MEntity extends MObject{
    protected String name;
    protected LinkedList<MLink> outs;



    public MEntity(String name) {
        this.name=name;
        this.outs= new LinkedList<MLink>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addLink(MLink link){
        outs.add(link);
    }

    public LinkedList<MLink> getOuts() {
        return outs;
    }

    @Override
    public String toString() {
        return name;
    }

}
