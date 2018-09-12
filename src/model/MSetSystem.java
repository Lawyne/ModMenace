package model;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class MSetSystem{

    protected MSystem system;
    protected HashMap<MEntity,Pair<Double,Double>> coordinates; //Hashmap of entities coordinates
    protected HashMap<MEntity,Color> colorHashMap;
    protected boolean dirty; //Observation boolean

    //Default configuration
    public MSetSystem(){
        MEntity A = new MEntity("A");
        MEntity B = new MEntity("B");
        MEntity R = new MEntity("R");
        MEntity I = new MEntity("I");
        MLink AB = new MLink(B);
        MLink AI = new MLink(I);
        MLink AR = new MLink(R);
        A.addLink(AB);
        A.addLink(AI);
        A.addLink(AR);

        MLink BA = new MLink(A);
        MLink BI = new MLink(I);
        MLink BR = new MLink(R);
        B.addLink(BA);
        B.addLink(BI);
        B.addLink(BR);

        MLink IA = new MLink(A);
        MLink IB = new MLink(B);
        I.addLink(IA);
        I.addLink(IB);

        MLink RA = new MLink(A);
        MLink RB = new MLink(B);
        MLink RI = new MLink(I);
        R.addLink(RA);
        R.addLink(RB);
        R.addLink(RI);

        MSystem sys = new MSystem();
        sys.addEntity(A);
        sys.addEntity(B);
        sys.addEntity(R);
        sys.addEntity(I);
        sys.addLink(AR);
        sys.addLink(RA);
        sys.addLink(AI);
        sys.addLink(IA);
        sys.addLink(BR);
        sys.addLink(RB);
        sys.addLink(BI);
        sys.addLink(IB);
        sys.addLink(RI);

        this.system = sys;
        this.coordinates = new HashMap<>();
        this.colorHashMap = new HashMap<>();
        this.dirty=true;

        //coordinates & color assignment
        for (MEntity ent: system.getEntities()) {
            coordinates.put(ent, generateCoordinates());
            colorHashMap.put(ent, generateColor());
        }

    }

    public MSetSystem(MSystem mSystem, HashMap<MEntity,Pair<Double,Double>> coordinates, HashMap<MEntity,Color> colorHashMap, boolean dirty){
        this.system = mSystem;
        this.coordinates = coordinates;
        this.colorHashMap = colorHashMap;
        this.dirty=dirty;
    }

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

    public MSetSystem clone(){
        MSetSystem clone = new MSetSystem(system.clone(),(HashMap)coordinates.clone(),(HashMap)colorHashMap.clone(),dirty);
        return clone;
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

    public void setColor(MEntity ent, Color color){colorHashMap.put(ent,color);}

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

    public void addEntity(){
        MEntity ent = new MEntity("Random entity");
        addEntity(ent);
    }

    //add the link link to the system
    public void addLink(MLink link) {
        system.addLink(link);
        dirty = true;
    }

    public void addLink(MLink... links){system.addLink(links);}

    public void addLink(){
        MEntity source = randomEntity();
        MEntity target = randomEntity();
        MLink link = new MLink(source,target);
        addLink(link);
    }

    //returns true if system contains e, false otherwise
    public boolean contains(MEntity e){
        return system.contains(e);
    }

    //returns true if system contains l, false otherwise
    public boolean contains(MLink l){
        return system.contains(l);
    }

    private MEntity randomEntity() {
        MEntity[] ents = system.getEntities().toArray(new MEntity[0]);
        MEntity mEntity = ents[new Random().nextInt(ents.length)];
        return mEntity;
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

    @Override
    public int hashCode() {
        int hash =0;
        hash+=system.hashCode();
        for (Color color:colorHashMap.values())
               {
            hash+=color.hashCode();
        }
        for (Pair<Double,Double> pair:coordinates.values()){
            hash+=pair.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MSetSystem)) return false;
        MSetSystem other = (MSetSystem) obj;
        if (!(other.hashCode()==this.hashCode())) return false;
        return (this.system.hashCode()==other.system.hashCode() && this.dirty==other.isDirty() && this.colorHashMap==other.colorHashMap && this.coordinates==other.coordinates);
    }
}
