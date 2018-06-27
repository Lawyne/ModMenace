package model;

import java.util.LinkedList;

public class MSystem {
    private LinkedList<MEntity> entities;
    private LinkedList<MLink> links;

    public MSystem() {
        entities = new LinkedList<>();
        links = new LinkedList<>();
    }

    public void addEntity(MEntity ent){
        entities.add(ent);
    }

    public void addLink(MLink link){
        links.add(link);
    }

    public boolean contains(MEntity e){
        return entities.indexOf(e) != -1;
    }

    public boolean contains(MLink l){
        return links.indexOf(l) != -1;
    }

    //to be modified
    public void remove(MEntity e){
        entities.remove(e);
    }

    //to be modified
    public void remove(MLink l){
        links.remove(l);
    }

    public LinkedList<MEntity> getEntities() {
        return entities;
    }

    public LinkedList<MLink> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        String str = "";
        for (MEntity e : entities){
            for (MLink l : e.outs){
                str = str + e + l + "\n";
            }
        }
        return str;
    }

}
