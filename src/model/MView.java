package model;

public class MView extends MSystem {
    private MSystem system;

    public MView(MSystem mSystem) {
        super();
        this.system = mSystem;
        this.addEntities(mSystem.getEntities());
        this.addLinks(mSystem.getLinks());
    }

}
