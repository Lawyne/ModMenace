package model.automaton;

import model.MEntity;

import java.util.LinkedList;
import java.util.List;

public class Automaton extends MEntity
{
    protected int size;
    protected boolean [][] transition; /*Relation de transition*/
    protected State initial; /*EI*/
    protected List<State> states; /*Tout*/

    public Automaton(String name, boolean[][] transition, String[] stateList)
    {
        super(name);
        if(stateList.length > 0)
        {
            this.size = stateList.length;
            this.name = name;
            this.transition = transition;
            this.states = new LinkedList<State>();
            for (int i = 0; i < size; i++)
            {
                State s = new State(stateList[i]);
                if(i==0)
                {
                    this.initial = s;
                }
                states.add(s);
            }

        }
        else
            {
                System.out.println("Empty List");
            }
    }


    public Automaton(Automaton A1, Automaton A2)
    {
        super("Async "+ A1.name +" X "+A2.name);
        this.size = A1.size * A2.size;
        this.states = new LinkedList<State>();
        for (int h = 0; h < A1.size; h++)
        {
            for (int i = 0; i < A2.size; i++)
            {
                State s = new State("("+A1.states.get(h)+","+ A2.states.get(i) +")");
                if (h == 0 && i ==0) {
                    this.initial = s;
                }
                states.add(s);
            }
        }


        this.transition = new boolean[size][size];
        for(int i = 0; i < size; i++)
        {
            /**To find (x,y) where x is from A1 and y is from A2
            corresponding to i, we propose the following:
            i = (size of A1) * x + y
            */
            int x1 = i / A1.size;
            int y1 = i % A1.size;

            for(int j = 0; j < size; j++)
            {
                /*Same reasoning*/
                int x2 = j / A1.size;
                int y2 = j % A1.size;
                boolean t1 = A1.transition[x1][x2]; /*does x1->x2 exist*/
                boolean t2 = A2.transition[y1][y2]; /*does y1->y2 exist*/
                transition[i][j]= (x1==x2&&t2)||(y1==y2&&t1); /*does the transition exist, one change at a time*/
            }
        }

    }

    public int getSize()
    {
        return size;
    }

    @Override
    public String toString() {
        String str = name+"\n ->"+initial;
        for (int i=0;i<size;i++)
        {
            for (int j=0;j<size;j++)
            {
                if (transition[i][j])
                {
                    str += "\n" + states.get(i) + "->" + states.get(j) ;
                }
            }
        }
        return(str);
    }

}
