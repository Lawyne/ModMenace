package model;

import fx.FXView;

import java.util.HashMap;
import java.util.LinkedList;

public class MManager {
    protected MSystem mSystem;
    protected LinkedList<MView> mViews;
    protected MSetSystem mLocations;

    protected LinkedList<FXView> observers;
    protected HashMap<FXView,MSystem> fxViewMSystemHashMap;


    public MManager(MSystem system, MView... views){
        this.mSystem = system;
        this.mViews = new LinkedList<MView>();
        for (MView v: views) {
            mViews.add(v);
        }
        this.mLocations = new MSetSystem(system);

        this.observers = new LinkedList<FXView>();
        this.fxViewMSystemHashMap = new HashMap<>();

    }

    public MSetSystem getLocations() {
        return mLocations;
    }

    //adds observers
    public void addObserver(FXView fxView){
        observers.add(fxView);
        fxView.addController(this);
    }

    //updates all observers
    public void updateAll(MObject stuff){
        for (FXView observer : observers) {
            observer.update(stuff);
        }
    }

    //adds an entity to the system
    public void addEntity(MEntity entity){
        mLocations.addEntity(entity);
        updateAll(entity);
    }

    //adds a link to the system
    public void addLink(MLink link){
        mLocations.addLink(link);
        updateAll(link);
    }

    public void update(){
        //TODO
    }

}
