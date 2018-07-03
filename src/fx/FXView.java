package fx;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import model.*;

import java.util.*;
import java.util.Map.Entry;

public class FXView extends Pane {
    private final HashMap<MEntity,FXVertex> entityFXVertexHashMap;
    private final HashMap<MLink,FXEdge> linkFXEdgeHashMap;
    private final Group vertices;
    private final Group edges;

    private MSetSystem system;
    private FXController controller;

    public FXView(MSetSystem system){
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
            FXVertex fxVertex = new FXVertex(entity,system.getCoordinates(entity),system.getColor(entity));
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

    public void addController(FXController controller){
        this.controller = controller;
    }

    //adds entity to the system
    public void addVertex(MEntity entity){
        controller.addEntity(entity);
    }

    //adds a random new vertex when no argument
    public void addVertex(){
        addVertex(new MEntity("" + (int) (Math.random()*99999999)) );
    }

    //adds link to the system
    public void addEdge(MLink link){
        controller.addLink(link);
    }

    //adds a random new edge when no argument
    public void addEdge(){
        //select a random source & a random target
        MEntity source = randomEntity();
        MEntity target = randomEntity();

        MLink link = new MLink(target);
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

    //refreshes the entities
    private void addNewEntities(){
        for (MEntity entity : system.getEntities()){
            if (!entityFXVertexHashMap.containsKey(entity)) { //checks if edge already exists
                FXVertex fxVertex = new FXVertex(entity, system.getCoordinates(entity), system.getColor(entity));
                entityFXVertexHashMap.put(entity, fxVertex);
                this.vertices.getChildren().add(fxVertex);
            }
        }
    }

    //refreshes the links
    private void addNewLinks(){
        for (MLink link : system.getLinks()){ //checks if vertex already exists
            if(!linkFXEdgeHashMap.containsKey(link)){
                FXEdge fxEdge = new FXEdge(link);
                linkFXEdgeHashMap.put(link, fxEdge);
                this.edges.getChildren().add(fxEdge);

                bind(fxEdge);
            }
        }
    }

    //refreshes the system
    public void update(){
        addNewEntities();
        addNewLinks();
    }

    //refreshes the system with a given object
    public void update(MObject stuff){
        if(stuff.isEntity()){addNewEntities();}
        if(stuff.isLink()){addNewLinks();}
    }
}
