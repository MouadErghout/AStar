import java.awt.event.MouseEvent;
import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        HashMap<String, Integer> H = new HashMap<String, Integer>();
        HashMap<String, Integer> COST = new HashMap<String, Integer>();

        H.put("S0",9);
        H.put("S1",8);
        H.put("S2",5);
        H.put("S3",0);
        H.put("S4",7);
        H.put("S5",7);
        H.put("S6",0);

        COST.put("S0S1",1);
        COST.put("S0S3",8);
        COST.put("S1S4",2);
        COST.put("S2S0",2);
        COST.put("S2S1",3);
        COST.put("S2S3",1);
        COST.put("S2S5",5);
        COST.put("S3S6",4);
        COST.put("S4S2",2);
        COST.put("S4S5",3);
        COST.put("S5S6",7);

        HashMap<String,LinkedList<String>> Matrix = new HashMap<String,LinkedList<String>>();
        LinkedList<String> S0 = new LinkedList<String>();
        S0.add("S1"); S0.add("S3");
        LinkedList<String> S1 = new LinkedList<String>();
        S1.add("S4");
        LinkedList<String> S2 = new LinkedList<String>();
        S2.add("S0"); S2.add("S1");S2.add("S3"); S2.add("S5");
        LinkedList<String> S3 = new LinkedList<String>();
        S3.add("S6");
        LinkedList<String> S4 = new LinkedList<String>();
        S4.add("S2"); S4.add("S5");
        LinkedList<String> S5 = new LinkedList<String>();
        S5.add("S6");
        LinkedList<String> S6 = new LinkedList<String>();

        Matrix.put("S0",S0);
        Matrix.put("S1",S1);
        Matrix.put("S2",S2);
        Matrix.put("S3",S3);
        Matrix.put("S4",S4);
        Matrix.put("S5",S5);
        Matrix.put("S6",S6);

        Probleme P = new Probleme("S0","S6",Matrix);

        AStar(P,H,COST);
    }

    public static int AStar(Probleme P, HashMap<String, Integer> H, HashMap<String, Integer> C)
    {
        Noeud noeud = new Noeud(P.Initial,0);
        noeud.f=noeud.cout+H.get(noeud.etat);
        LinkedList<Noeud> frontiere =  new LinkedList<Noeud>();
        frontiere.add(noeud);
        Collections.sort(frontiere);
        LinkedList<Noeud> visites = new LinkedList<Noeud>();
        int i=1;
        while(true)
        {
            if(frontiere.isEmpty()) return 0;
            noeud = frontiere.removeFirst();
            Collections.sort(frontiere);
            if(P.BUT_TEST(noeud.etat))
            {
                System.out.println("\nCout = "+i);
                System.out.println(noeud);
                return 1;
            }else{
                visites.add(noeud);
                i++;
                System.out.println(noeud);
                LinkedList<String> Actions = P.Actions(noeud.etat);
                Noeud fils;
                for(String action : Actions)
                {
                    fils = NOEUD_FILS(noeud,action,C.get(noeud.etat+action));
                    fils.f=fils.cout+H.get(fils.etat);
                    int indexf = frontiere.indexOf(fils);
                    int indexv = visites.indexOf(fils);

                    if(indexf==-1 && indexv==-1) {
                        frontiere.add(fils);
                        Collections.sort(frontiere);
                    }
                    else if(indexf!=-1 && frontiere.get(indexf).f>fils.f)
                    {
                        frontiere.get(indexf).f = fils.f;
                        Collections.sort(frontiere);
                    }
                    else if(indexv!=-1 && visites.get(indexf).f>fils.f)
                    {
                        frontiere.add(visites.remove(indexf));
                        Collections.sort(frontiere);
                    }
                }
            }
        }
    }
    public static Noeud NOEUD_FILS(Noeud n, String e, int c)
    {
        Noeud no = new Noeud(e,n.cout+c);
        n.Next.add(no);
        return no;
    }
}