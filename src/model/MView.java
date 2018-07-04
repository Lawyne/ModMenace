package model;

public class MView extends MSystem {
    private MSystem system;

    public MView(MSystem mSystem) {
        super();
        this.system = mSystem;
        this.addEntities(mSystem.getEntities());
        this.addLinks(mSystem.getLinks());
    }

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

}
