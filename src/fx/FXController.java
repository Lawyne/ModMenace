package fx;

import model.MEntity;
import model.MLink;
import model.MSetSystem;

import java.util.LinkedList;

public class FXController {

    protected MSetSystem system;
    protected LinkedList<FXView> observers;

    public FXController(MSetSystem system){
        this.system = system;
        this.observers = new LinkedList<FXView>();
    }

    //adds observers
    public void addObserver(FXView fxView){
        observers.add(fxView);
        fxView.addController(this);
    }

    //updates all observers
    public void updateAll(){
        for (FXView observer : observers) {
            observer.update();
        }
    }

    //adds an entity to the system
    public void addEntity(MEntity entity){
        system.addEntity(entity);
        updateAll();
    }

    //adds a link to the system
    public void addLink(MLink link){
        system.addLink(link);
        updateAll();
    }

    //TODO
}
