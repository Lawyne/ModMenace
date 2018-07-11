package model;

import java.util.LinkedList;

public class MManager {
    protected MSystem mSystem;
    protected LinkedList<MView> mViews;


    public MManager(MSystem system, MView... views){
        this.mSystem = system;
        this.mViews = new LinkedList<MView>();
        for (MView v: views) {
            mViews.add(v);
        }

    }

    public void update(){
        //TODO
    }

}
