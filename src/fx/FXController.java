package fx;

import model.MEntity;
import model.MLink;
import model.MObject;
import model.MSetSystem;

import java.util.LinkedList;

//Obsolete
public class FXController {

    protected MSetSystem system;
    protected LinkedList<FXView> observers;

    public FXController(MSetSystem system){
        this.system = system;
        this.observers = new LinkedList<FXView>();
    }

    /* Obsolete code
    //adds observers
    public void addObserver(FXView fxView){
        observers.add(fxView);
        fxView.addController(this);
    }
    */


    //updates all observers
    public void updateAll(MObject stuff){
        for (FXView observer : observers) {
            observer.update(stuff);
        }
    }

    //adds an entity to the system
    public void addEntity(MEntity entity){
        system.addEntity(entity);
        updateAll(entity);
    }

    //adds a link to the system
    public void addLink(MLink link){
        system.addLink(link);
        updateAll(link);
    }

    //TODO
}
