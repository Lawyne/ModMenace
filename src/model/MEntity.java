package model;

import java.util.LinkedList;

public class MEntity extends MObject{
    protected String name;
    protected LinkedList<MLink> outs; //lists of existing links

    public MEntity(String name) {
        this.name=name;
        this.outs= new LinkedList<MLink>();
    }

    //return name
    public String getName() {
        return name;
    }

    //set name
    public void setName(String name) {
        this.name = name;
    }

    //adds link to the outs
    public void addLink(MLink link){
        outs.add(link);
    }

    //removes link from the outs
    public void remove(MLink link) {
        outs.remove(link);
    }

    //returns outs
    public LinkedList<MLink> getOuts() {
        return outs;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean isEntity() {
        return true;
    }

    @Override
    public boolean isLink() {
        return false;
    }

}
