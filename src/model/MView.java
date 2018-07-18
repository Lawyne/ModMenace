package model;

import java.util.LinkedList;

public class MView extends MSystem {
    private MSystem system;
    private LinkedList<MEntity> entities;
    private LinkedList<MLink> links;

    public MView(MSystem mSystem) {
        super();
        this.system = mSystem;
        this.entities = new LinkedList<MEntity>();
        this.links = new LinkedList<MLink>();
        addEntities(mSystem.getEntities());
        addLinks(mSystem.getLinks());
    }

}
