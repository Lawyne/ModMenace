package model;

import java.util.Iterator;
import java.util.LinkedList;

public class MSystem {
    private LinkedList<MEntity> entities;
    private LinkedList<MLink> links;

    public MSystem() {
        entities = new LinkedList<>();
        links = new LinkedList<>();
    }

    public MSystem clone(){
        MSystem clone = new MSystem();
        clone.addLinks(links);
        clone.addEntities(entities);
        return clone;
    }

    //adds entity ent to the system
    public void addEntity(MEntity ent){
        if (!contains(ent)){
            entities.add(ent);
        }
    }

    public void addEntity(MEntity... ents){
        for(MEntity e : ents){
            addEntity(e);
        }
    }

    //adds entities ents to the system
    public void addEntities(LinkedList<MEntity> ents){
        for (MEntity ent: ents) {
            addEntity(ent);
        }
    }

    //adds link link to the system
    public void addLink(MLink link){
        if(!contains(link)){
            links.add(link);
        }
    }

    public void addLink(MLink... links){
        for(MLink l : links){
            addLink(l);
        }
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
        for (MLink l : e.getOuts()){
            links.remove(l);
        }
        Iterator<MLink> iterator = links.iterator();
        while (iterator.hasNext()){
            MLink l= iterator.next();
            if (l.getOut().equals(e)){
                iterator.remove();
            }
        }
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

    public void synchronize(MSystem mSystem) {
        entities = new LinkedList<>(mSystem.getEntities());
        links = new LinkedList<>(mSystem.getLinks());
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

    @Override
    public int hashCode() {
        int hash =0;
        for (MEntity ent: entities){
            hash+=ent.getId();
        }

        for(MLink link: links){
            hash+=link.hashCode();
        }
        return hash;
    }
}
