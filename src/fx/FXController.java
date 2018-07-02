package fx;

import model.MEntity;
import model.MLink;
import model.MSetSystem;

import java.util.LinkedList;

public class FXController {

    protected MSetSystem system;
    protected LinkedList<FXSystem> observers;

    public FXController(MSetSystem system){
        this.system = system;
        this.observers = new LinkedList<FXSystem>();
    }

    //adds observers
    public void addObserver(FXSystem fxSystem){
        observers.add(fxSystem);
        fxSystem.addController(this);
    }

    //updates all observers
    public void updateAll(){
        for (FXSystem observer : observers) {
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
