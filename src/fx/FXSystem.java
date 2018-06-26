package fx;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import model.MEntity;
import model.MLink;
import model.MSystem;

import java.util.*;
import java.util.Map.Entry;

public class FXSystem extends Pane {
    private final HashMap<MEntity,FXVertex> entityFXVertexHashMap;
    private final HashMap<MLink,FXEdge> linkFXEdgeHashMap;
    private final Group vertices;
    private final Group edges;

    private MSystem system;

    public FXSystem(MSystem system){
        super();
        this.minHeight(FXConstants.HEIGHT);
        this.minWidth(FXConstants.WIDTH);

        this.vertices=new Group();
        this.edges=new Group();
        this.getChildren().add(edges);
        this.getChildren().add(vertices);
        this.system = system;

        this.entityFXVertexHashMap = new HashMap<MEntity, FXVertex>();
        this.linkFXEdgeHashMap = new HashMap<MLink,FXEdge>();

        for (MLink link : system.getLinks()){
            FXEdge fxEdge = new FXEdge(link);
            linkFXEdgeHashMap.put(link, fxEdge);
            this.edges.getChildren().add(fxEdge);
        }
        for (MEntity entity : system.getEntities()){
            FXVertex fxVertex = new FXVertex(entity);
            entityFXVertexHashMap.put(entity, fxVertex);
            this.vertices.getChildren().add(fxVertex);
        }


        //Binding Edges Starts
        Set<Entry<MEntity, FXVertex>> setVertices = entityFXVertexHashMap.entrySet();
        Iterator<Map.Entry<MEntity, FXVertex>> it1 = setVertices.iterator();
        while(it1.hasNext()){
            Entry<MEntity, FXVertex> entityFXVertexEntry = it1.next();

            for(MLink link : entityFXVertexEntry.getKey().getOuts()){
                FXEdge fxEdge = linkFXEdgeHashMap.get(link);
                FXVertex fxVertex = entityFXVertexEntry.getValue();

                bindStart(fxVertex,fxEdge);
            }
        }

        //Binding Edges Ends
        Set<Entry<MLink, FXEdge>> setEdges = linkFXEdgeHashMap.entrySet();
        Iterator<Map.Entry<MLink, FXEdge>> it2 = setEdges.iterator();
        while(it2.hasNext()){
            Entry<MLink, FXEdge> linkFXEdgeEntry = it2.next();

            FXEdge fxEdge = linkFXEdgeEntry.getValue();
            bindEnd(fxEdge);
        }

    }

    //adds entity to the system
    public void addVertex(MEntity entity){
        FXVertex fxVertex = new FXVertex(entity);
        entityFXVertexHashMap.put(entity, fxVertex);
        this.vertices.getChildren().add(fxVertex);
        //vertices.toFront();
        system.addEntity(entity);
    }

    //adds a random new vertex when no argument
    public void addVertex(){
        addVertex(new MEntity("" + (int) (Math.random()*99999999)) );
    }

    //adds link to the system
    public void addEdge(MLink link){
        FXEdge fxEdge = new FXEdge(link);
        linkFXEdgeHashMap.put(link, fxEdge);
        this.edges.getChildren().add(fxEdge);
        bind(fxEdge);
        //edges.toBack();
        system.addLink(link);
    }

    //adds a random new edge when no argument
    public void addEdge(){
        //select a random source & a random target
        MEntity source = randomEntity();
        MEntity target = randomEntity();

        MLink link = new MLink(target,"" + (int) (Math.random()*99999999));
        source.addLink(link);

        addEdge(link);
    }

    //returns a random key in entityFXVertexHashMap
    private MEntity randomEntity() {
        MEntity[] keys = entityFXVertexHashMap.keySet().toArray(new MEntity[0]);
        MEntity mEntity = keys[new Random().nextInt(keys.length)];
        return mEntity;
    }

    //binds fxEdge's start with source location
    private void bindStart(FXVertex source,FXEdge fxEdge){
        fxEdge.startXProperty().bind(source.centerXProperty());
        fxEdge.startYProperty().bind(source.centerYProperty());
    }

    //binds fxEdge's start with source location when source is unknown
    private void bindStart(MLink link){
        //finding source
        for (MEntity entity : entityFXVertexHashMap.keySet()){
            if(entity.getOuts().contains(link)){
                bindStart(entityFXVertexHashMap.get(entity),linkFXEdgeHashMap.get(link));
            }
        }
    }

    //binds fxEdge's start with source location when source is unknown
    private void bindStart(FXEdge fxEdge){
        bindStart(fxEdge.link);
    }

    //binds fxEdge's end with target location
    private void bindEnd(FXVertex target,FXEdge fxEdge){
        fxEdge.endXProperty().bind(target.centerXProperty());
        fxEdge.endYProperty().bind(target.centerYProperty());
    }

    //binds fxEdge's end with target location when target is unknown
    private void bindEnd(FXEdge fxEdge){
        bindEnd(fxEdge.link);
    }

    //binds fxEdge's end with target location when target is unknown
    private void bindEnd(MLink link){
        FXVertex target = entityFXVertexHashMap.get(link.getOut());
        bindEnd(target,linkFXEdgeHashMap.get(link));
    }

    //binds fxEdge's start & end
    private void bind(FXEdge fxEdge){
        bindEnd(fxEdge);
        bindStart(fxEdge);
    }

}