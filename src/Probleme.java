import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Probleme
{
     String Initial, But;
     HashMap<String,LinkedList<String>> Matrix = new HashMap<String,LinkedList<String>>();

    public Probleme(String init, String but, HashMap<String,LinkedList<String>>M)
    {
        Initial = init;
        But = but;
        Matrix = M;
    }

    public LinkedList<String> Actions(String e)
    {
        return Matrix.get(e);
    }

    public boolean BUT_TEST(String e)
    {
        return e.equals(But);
    }
}
