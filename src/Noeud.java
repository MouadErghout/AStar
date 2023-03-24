import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Noeud implements Comparable<Noeud>
{
    List<Noeud> Next = new LinkedList<>();
    String etat;
    int f, cout;

    public Noeud(String e, int c) {etat = e; cout = c;}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return "Etat: "+etat+ " f()= "+f;
    }

    @Override
    public int compareTo(Noeud o)
    {
        if(f>o.f)
        return 1;
        if(f==o.f)
            return 0;
        return -1;
    }
}
