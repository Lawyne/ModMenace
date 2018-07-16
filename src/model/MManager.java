package model;

import java.util.LinkedList;

public class MManager {
    protected MSystem mSystem;
    protected LinkedList<MView> mViews;
    protected MSetSystem mLocations;


    public MManager(MSystem system, MView... views){
        this.mSystem = system;
        this.mViews = new LinkedList<MView>();
        for (MView v: views) {
            mViews.add(v);
        }
        this.mLocations = new MSetSystem(system);

    }

    public void update(){
        //TODO
    }

}
