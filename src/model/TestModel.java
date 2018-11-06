package model;

import java.lang.System;

public class TestModel {

    public static void main(String[] args){
        MEntity A = new MEntity("A");
        MEntity B = new MEntity("B");
        MEntity R = new MEntity("R");
        MEntity I = new MEntity("I");

        System.out.println(R + "\n");

        MLink AB = new MLink(B);
        MLink AI = new MLink(I);
        MLink AR = new MLink(R);
        A.addLink(AB);
        A.addLink(AI);
        A.addLink(AR);

        MLink BA = new MLink(A);
        MLink BI = new MLink(I);
        MLink BR = new MLink(R);
        B.addLink(BA);
        B.addLink(BI);
        B.addLink(BR);

        MLink IA = new MLink(A);
        MLink IB = new MLink(B);
        I.addLink(IA);
        I.addLink(IB);

        MLink RA = new MLink(A);
        MLink RB = new MLink(B);
        MLink RI = new MLink(I);
        R.addLink(RA);
        R.addLink(RB);
        R.addLink(RI);

        System.out.println(AR+ "\n");

        MSystem sys = new MSystem();
        sys.addEntity(A);
        sys.addEntity(B);
        sys.addEntity(R);
        sys.addEntity(I);

        sys.addLink(AB);
        sys.addLink(BA);
        sys.addLink(AR);
        sys.addLink(RA);
        sys.addLink(AI);
        sys.addLink(IA);
        sys.addLink(BR);
        sys.addLink(RB);
        sys.addLink(BI);
        sys.addLink(IB);
        sys.addLink(RI);

        System.out.println(sys);

        System.out.println(sys.contains(A));
        System.out.println(sys.contains(AB));

        sys.remove(AB);

        System.out.println(sys);

        System.out.println(sys.contains(AB));
        System.out.println(sys.contains(B));

        sys.remove(A);

        System.out.println(sys);
    }

}
