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
    protected HashMap<FXView,Integer> fxViewRange; //Range is defined as 0 for the golden model and any other integer for others. Views with the same Integer share knowledge.


    public MManager(MSystem system, MView... views){
        this.mSystem = system;
        this.mViews = new LinkedList<MView>();
        for (MView v: views) {
            mViews.add(v);
        }
        this.mLocations = new MSetSystem(system);

        this.observers = new LinkedList<FXView>();
        this.fxViewMSystemHashMap = new HashMap<>();
        this.fxViewRange = new HashMap<>();

    }

    public MSetSystem getLocations() {
        return mLocations;
    }

    //adds observer corresponding to a MSystem
    public void addObserver(FXView fxView,Integer range, MSystem mSystem){
        observers.add(fxView);
        fxView.addController(this);

        fxViewMSystemHashMap.put(fxView,mSystem);
        fxViewRange.put(fxView,range);
    }

    //adds observer corresponding to the index i in mViews
    public void addObserver(FXView fxView, int id, int range){
        observers.add(fxView);
        fxView.addController(this);

        fxViewMSystemHashMap.put(fxView,mViews.get(id));
        fxViewRange.put(fxView,range);
    }

    //returns the nb of mViews in the sys
    public int nbViews(){
        return mViews.size();
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

    public void addEntity(MEntity entity, FXView view){
        mLocations.addEntity(entity);
        //TODO
    }

    //adds a link to the system
    public void addLink(MLink link){
        mLocations.addLink(link);
        updateAll(link);
    }

    public void addLink(MLink link, FXView view){
        mLocations.addLink(link);
        //TODO
    }

    public LinkedList<FXView> sharedKnowledge(FXView view) {
        LinkedList<FXView> result = new LinkedList<>();
        Integer range = fxViewRange.get(view);
        for (FXView fxView : observers) {
            if (fxViewRange.get(fxView)== range) {
                result.add(fxView);
            }
        }

        return result;
    }

    public void update(){
        //TODO
    }

    public void removeEntity(MEntity entity, FXView fxView) {
        if(fxViewRange.get(fxView)==0){
            //TODO must remove from all
        }
        else{

        }
    }

    public void removeLink(MLink link, FXView fxView) {
        if(fxViewRange.get(fxView)==0){
            //TODO must remove from all
        }
        else{

        }
    }
}
