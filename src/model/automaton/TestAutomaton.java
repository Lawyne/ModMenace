package model.automaton;


public class TestAutomaton {
    public static void main(String[] args){
        boolean[][] transition1 = {{false,true},{true,false}};
        String[] states1 = {"1","2"};
        Automaton A1 = new Automaton("Automaton1", transition1, states1);

        boolean[][] transition2 = {{false,true},{false,false}};
        String[] states2 = {"3","4"};
        Automaton A2 = new Automaton("Automaton2", transition2, states2);

        System.out.println(A1);
        System.out.println(A2);

        Automaton Async = new Automaton(A1,A2);
        System.out.println(Async);

    }

}
