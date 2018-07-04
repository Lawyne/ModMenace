package model;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;

public class MSetSystem{

    protected MSystem system;
    protected HashMap<MEntity,Pair<Double,Double>> coordinates; //Hashmap of entities coordinates
    protected HashMap<MEntity,Color> colorHashMap;
    protected boolean dirty; //Observation boolean

    public MSetSystem(MSystem mSystem){
        this.system = mSystem;
        this.coordinates = new HashMap<>();
        this.colorHashMap = new HashMap<>();
        this.dirty=true;

        //coordinates & color assignment
        for (MEntity ent: system.getEntities()) {
            coordinates.put(ent,generateCoordinates());
            colorHashMap.put(ent,generateColor());
        }
    }

    //check if dirty
    public boolean isDirty() {
        return dirty;
    }

    //set dirty
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    //return random double tuple (between 0 & 1) for x & y assignments
    private Pair<Double,Double> generateCoordinates(){
        double randomX = Math.random();
        double randomY = Math.random();

        return new Pair<>(randomX,randomY);
    }

    //return the coordinates assigned to the entity ent
    public Pair<Double,Double> getCoordinates(MEntity ent){
        return coordinates.get(ent);
    }

    private Color generateColor(){
        return Color.color(Math.random(),Math.random(),Math.random());
    }

    public Color getColor(MEntity ent){
        return colorHashMap.get(ent);
    }

    //add the entity ent to the system
    public void addEntity(MEntity ent) {
        system.addEntity(ent);
        coordinates.put(ent,generateCoordinates());
        colorHashMap.put(ent,generateColor());
        dirty = true;
    }

    public void addEntity(MEntity... ents){
        for (MEntity e : ents){
            addEntity(e);
        }
    }

    //add the link link to the system
    public void addLink(MLink link) {
        system.addLink(link);
        dirty = true;
    }

    public void addLink(MLink... links){system.addLink(links);}

    //returns true if system contains e, false otherwise
    public boolean contains(MEntity e){
        return system.contains(e);
    }

    //returns true if system contains l, false otherwise
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

    //return the entity set of the system as a LinkedList
    public LinkedList<MEntity> getEntities() {
        return system.getEntities();
    }

    //return the link set of the system as a LinkedList
    public LinkedList<MLink> getLinks() {
        return system.getLinks();
    }
}
