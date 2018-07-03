package model;

public class MLink extends MObject{
    private MEntity out; //only single target entity
    private String name;

    public MLink(MEntity out, String name){
        this.out=out;
        this.name=name;
    }

    public MLink(MEntity out){
        this.out=out;
        this.name=" ";
    }

    //sets target
    public void setOut(MEntity out) {
        this.out = out;
    }

    //returns target
    public MEntity getOut() {
        return out;
    }

    //returns name
    public String getName() {
        return name;
    }

    //sets name
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String str = " --"+ name + "-> " + out;
        return str;
    }

    @Override
    public boolean isEntity() {
        return false;
    }

    @Override
    public boolean isLink() {
        return true;
    }

}
