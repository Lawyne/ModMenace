package fx;

import model.MSetSystem;

import java.util.LinkedList;

public class FXController {

    protected MSetSystem system;
    protected LinkedList<FXSystem> observers;

    public FXController(MSetSystem system){
        this.system = system;
        this.observers = new LinkedList<FXSystem>();
    }

    public void addObserver(FXSystem fxSystem){
        observers.add(fxSystem);
    }

    public void updateAll(){
        for (FXSystem observer : observers) {
            observer.update();
        }
    }

    //TODO
}
