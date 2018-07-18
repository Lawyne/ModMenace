package model;

import java.util.LinkedList;

public class MView extends MSystem {
    private MSystem system;
    private LinkedList<MEntity> entities;
    private LinkedList<MLink> links;

    public MView(MSystem mSystem) {
        super();
        this.system = mSystem;/*
        this.entities = new LinkedList<MEntity>();
        this.links = new LinkedList<MLink>();*/
        addEntities(mSystem.getEntities());
        addLinks(mSystem.getLinks());
    }

    /*
    @Override
    public void addLink(MLink link) {
        super.addLink(link);
        if(!system.contains(link)){
            system.addLink(link);
        }
    }

    @Override
    public void addEntity(MEntity ent) {
        super.addEntity(ent);
        if(!system.contains(ent)){
            system.addEntity(ent);
        }
    }

    @Override
    public void remove(MLink l) {
        super.remove(l);
        system.remove(l);
    }

    @Override
    public void remove(MEntity e) {
        super.remove(e);
        system.remove(e);
    }

    public void hide(MLink l){
        super.remove(l);
    }

    public void hide(MEntity e){
        super.remove(e);
    }
    */
/*
    //checks if system contains e
    @Override
    public boolean contains(MEntity e){
        return entities.indexOf(e) != -1;
    }

    //checks if system contains l
    @Override
    public boolean contains(MLink l){
        return links.indexOf(l) != -1;
    }
*/
}
