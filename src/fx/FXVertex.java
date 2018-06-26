package fx;

import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Circle;
import model.MEntity;
import javafx.scene.paint.Color;

public class FXVertex extends Circle{

    private MEntity entity;

    public FXVertex(MEntity e){
        super(  (int) (Math.random()* FXConstants.WIDTH +1),
                (int) (Math.random()* FXConstants.HEIGHT +1),
                FXConstants.RADIUS,
                Color.color(Math.random(),Math.random(),Math.random())  );
        this.entity = e;
    }

    public FXVertex(MEntity e, int x, int y){
        super(  x,
                y,
                FXConstants.RADIUS,
                Color.color(Math.random(),Math.random(),Math.random())  );
        this.entity = e;
    }

    public FXVertex(MEntity e, int x, int y, Color color){
        super(  x,
                y,
                FXConstants.RADIUS,
                color);
        this.entity = e;
    }

    public FXVertex(MEntity e, Color color){
        super(  (int) (Math.random()* FXConstants.WIDTH +1),
                (int) (Math.random()* FXConstants.HEIGHT +1),
                FXConstants.RADIUS,
                color);
        this.entity = e;
    }

    public MEntity getEntity() {
        return entity;
    }

    public void setEntity(MEntity entity) {
        this.entity = entity;
    }

    public void initDraggable(){
        //TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

}