package fx;

import javafx.util.Pair;
import model.MEntity;

import java.util.HashMap;

public class FXCoordinates {
    public final HashMap<MEntity,Pair<Integer,Integer>> coordinates = new HashMap<MEntity,Pair<Integer,Integer>>();

    public void add(MEntity entity, int x, int y){
        coordinates.put(entity, new Pair<>(x,y));
    }
}