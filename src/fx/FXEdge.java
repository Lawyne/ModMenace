package fx;

import javafx.scene.shape.Line;
import model.MLink;

public class FXEdge extends Line{

    protected MLink link;

    public FXEdge(MLink link) {
        super();
        this.link = link;
    }

}
