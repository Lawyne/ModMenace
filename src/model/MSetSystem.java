package model;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;

public class MSetSystem{

    protected MSystem system;
    protected HashMap<MEntity,Pair<Double,Double>> coordinates;
    protected boolean dirty;

    public MSetSystem(MSystem mSystem){
        this.system = mSystem;
        this.coordinates = new HashMap<>();
        this.dirty=true;

        //coordinates assignment
        for (MEntity ent: system.getEntities()) {
            coordinates.put(ent,generateCoordinates());
        }
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    //return random double tuple (between 0 & 1) for x & y assignments
    private Pair<Double,Double> generateCoordinates(){
        double randomX = Math.random();
        double randomY = Math.random();

        return new Pair<>(randomX,randomY);
    }

    public Pair<Double,Double> getCoordinates(MEntity ent){
        return coordinates.get(ent);
    }

    public void addEntity(MEntity ent) {
        system.addEntity(ent);
        coordinates.put(ent,generateCoordinates());
        dirty = true;
    }

    public void addLink(MLink link) {
        system.addLink(link);
        dirty = true;
    }

    public boolean contains(MEntity e){
        return system.contains(e);
    }

    public boolean contains(MLink l){
        return system.contains(l);
    }

    //To be modified
    public void remove(MEntity e){
        system.remove(e);
        coordinates.remove(e);
        dirty=true;
    }

    //To be modified
    public void remove(MLink l){
        system.remove(l);
        dirty=true;
    }

    public LinkedList<MEntity> getEntities() {
        return system.getEntities();
    }

    public LinkedList<MLink> getLinks() {
        return system.getLinks();
    }
}
