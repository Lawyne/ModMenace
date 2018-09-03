package model;

import fx.FXView;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class MManager {
    protected MSystem mSystem;
    protected LinkedList<MView> mViews;
    protected MSetSystem mLocations;

    protected LinkedList<FXView> observers;
    protected ConcurrentHashMap<FXView,MSystem> fxViewMSystemHashMap;
    protected ConcurrentHashMap<FXView,Integer> fxViewRange; //Range is defined as 0 for the golden model and any other integer for others. Views with the same Integer share knowledge.


    public MManager(MSystem system, MView... views){
        this.mSystem = system;
        this.mViews = new LinkedList<MView>();
        for (MView v: views) {
            mViews.add(v);
        }
        this.mLocations = new MSetSystem(system);

        this.observers = new LinkedList<FXView>();
        this.fxViewMSystemHashMap = new ConcurrentHashMap<>();
        this.fxViewRange = new ConcurrentHashMap<>();

    }

    public MSetSystem getLocations() {
        return mLocations;
    }

    //adds View on the system
    public void addMView(MView mView){
        mViews.add(mView);
    }

    //adds observer corresponding to a MSystem
    public void addObserver(FXView fxView,Integer range, MSystem mSystem){
        observers.add(fxView);
        fxView.addController(this);

        fxViewMSystemHashMap.put(fxView,mSystem);
        fxViewRange.put(fxView,range);
    }

    //adds observer corresponding to the index i in mViews
    public void addObserver(FXView fxView, Integer range, int id){
        observers.add(fxView);
        fxView.addController(this);

        fxViewMSystemHashMap.put(fxView,mViews.get(id));
        fxViewRange.put(fxView,range);
    }

    //adds goldenObserver
    public void addObserver(FXView fxView){
        observers.add(fxView);
        fxView.addController(this);

        fxViewMSystemHashMap.put(fxView,mSystem);
        fxViewRange.put(fxView,0);
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
    public void addEntity(MEntity entity, FXView view){
        LinkedList<FXView> views2Change = sharedKnowledge(view);
        mLocations.addEntity(entity);
        for (FXView fxView: views2Change) {
            fxViewMSystemHashMap.get(fxView).addEntity(entity);
        }
        //TODO
        update(views2Change);
        for (FXView fxView: views2Change) {
            fxView.makeClickable(entity);
        }
    }

    //adds a link to the system
    public void addLink(MLink link, FXView view){
        LinkedList<FXView> views2Change = sharedKnowledge(view);
        mLocations.addLink(link);
        for (FXView fxView: views2Change) {
            fxViewMSystemHashMap.get(fxView).addLink(link);
        }
        //TODO
        update(views2Change);
    }

    public LinkedList<FXView> sharedKnowledge(FXView view) {
        LinkedList<FXView> result = new LinkedList<>();
        Integer range = fxViewRange.get(view);

        for (FXView fxView : observers) {
            if (fxViewRange.get(fxView)== range || fxViewRange.get(fxView) == 0) {
                result.add(fxView);
            }
        }

        return result;
    }

    public void update(LinkedList<FXView> views){
        for (FXView view: views) {
            view.update();
        }
    }

    public void removeEntity(MEntity entity, FXView fxView) {
        LinkedList<FXView> views2Change = sharedKnowledge(fxView);
        for (FXView view: views2Change) {
            fxViewMSystemHashMap.get(view).remove(entity);
        }
        update(views2Change);
    }

    public void removeLink(MLink link, FXView fxView) {
        LinkedList<FXView> views2Change = sharedKnowledge(fxView);
        for (FXView view: views2Change) {
            fxViewMSystemHashMap.get(view).remove(link);
        }
        update(views2Change);
    }

    public void synchronize(FXView fxView) {
        LinkedList<FXView> views2Change = sharedKnowledge(fxView);
        for (FXView view : views2Change) {
            fxViewMSystemHashMap.get(view).synchronize(mSystem);
        }
        update(views2Change);
        for (FXView view: views2Change) {
            fxView.makeAllClickable();
        }
    }
}
