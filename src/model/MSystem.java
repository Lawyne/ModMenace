package model;

import java.util.LinkedList;

public class MSystem {
    private LinkedList<MEntity> entities;
    private LinkedList<MLink> links;

    public MSystem() {
        entities = new LinkedList<>();
        links = new LinkedList<>();
    }

    //adds entity ent to the system
    public void addEntity(MEntity ent){
        entities.add(ent);
    }

    //adds entities ents to the system
    public void addEntities(LinkedList<MEntity> ents){
        for (MEntity ent: ents) {
            addEntity(ent);
        }
    }

    //adds link link to the system
    public void addLink(MLink link){
        links.add(link);
    }

    //adds links links to the system
    public void addLinks(LinkedList<MLink> links){
        for (MLink link: links) {
            addLink(link);
        }
    }

    //checks if system contains e
    public boolean contains(MEntity e){
        return entities.indexOf(e) != -1;
    }

    //checks if system contains l
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

    //return set of entities as a LinkedList
    public LinkedList<MEntity> getEntities() {
        return entities;
    }

    //return set of links as a LinkedList
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
