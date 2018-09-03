package fx;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
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
        //this.makeClickable();
    }

    public FXVertex(MEntity e, int x, int y){
        super(  x,
                y,
                FXConstants.RADIUS,
                Color.color(Math.random(),Math.random(),Math.random())  );
        this.entity = e;
        //this.makeClickable();
    }

    public FXVertex(MEntity e, int x, int y, Color color){
        super(  x,
                y,
                FXConstants.RADIUS,
                color);
        this.entity = e;
        //this.makeClickable();
    }

    public FXVertex(MEntity e, Color color){
        super(  (int) (Math.random()* FXConstants.WIDTH +1),
                (int) (Math.random()* FXConstants.HEIGHT +1),
                FXConstants.RADIUS,
                color);
        this.entity = e;
        //this.makeClickable();
    }

    public FXVertex(MEntity e, Pair<Double,Double> coord){
        super(  (int) ( FXConstants.WIDTH * coord.getKey()),
                (int) ( FXConstants.HEIGHT * coord.getValue()),
                FXConstants.RADIUS,
                Color.color(Math.random(),Math.random(),Math.random())  );
        this.entity = e;
        //this.makeClickable();
    }

    public FXVertex(MEntity e, Pair<Double,Double> coord,Color color){
        super(  (int) ( FXConstants.WIDTH * coord.getKey()),
                (int) ( FXConstants.HEIGHT * coord.getValue()),
                FXConstants.RADIUS,
                color  );
        this.entity = e;
        //this.makeClickable();
    }

    /*private void makeClickable(){
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected! "+event.getSource());
                System.out.println("I am :"+entity);
                setColor(Color.AQUA);
            }
        });
    }*/

    public MEntity getEntity() {
        return entity;
    }

    public void setEntity(MEntity entity) {
        this.entity = entity;
    }

    public void setColor(Color color){
        this.setFill(color);
    }

    public void initDraggable(){
        //TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

}
