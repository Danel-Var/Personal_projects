import java.util.ArrayList;
import java.util.Random;

public class Main {
    final static int ITERATIONS = 100;
    final static long SEED = 1234; // Sum of last 4 digits of partners' IDs here

    public static void main(String[] args)
    {
        Random random = new Random(SEED);
        ArrayList<Integer> list = new ArrayList<>();

        System.out.println("What are the size and first element of list at the end of the 5th iteration?");
        System.out.println("Submit screenshots of the debugger with the answer.");
        System.out.println("Repeat this experiment with your instructor.");

        for(int i = 0 ; i < ITERATIONS ; i++)
        {
            if(random.nextBoolean() && list.size()>0)
            {
                list.remove(0);
            }
            else
            {
                list.add(random.nextInt());
            }
        }



    }
}
