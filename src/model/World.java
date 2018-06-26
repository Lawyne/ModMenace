package model;

import java.lang.System;

public class World implements Cloneable {
    protected MEntity[] entities = new MEntity[10];

    public World() {
        this.entities=new MEntity[10];
    }

    public MEntity[] getEntities() {
        return entities;
    }
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch(CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return o;
    }

}
