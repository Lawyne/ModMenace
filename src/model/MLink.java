package model;

public class MLink extends MObject{
    private MEntity out;
    private String name;

    public MLink(MEntity out, String name){
        this.out=out;
        this.name=name;
    }

    public MLink(MEntity out){
        this.out=out;
        this.name=" ";
    }

    public void setOut(MEntity out) {
        this.out = out;
    }

    public MEntity getOut() {
        return out;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String str = " --"+ name + "-> " + out;
        return str;
    }

}
