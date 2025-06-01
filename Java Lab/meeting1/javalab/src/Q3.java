
import java.util.Random;

public class Q3 {
    // Q3: write a program that generates a random matrix of 1 and 0
    public static void main(String[] args)
    {
        Random rand = new Random();
        int[][] array = new int[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                array[i][j] = rand.nextInt(2);
                System.out.print(array[i][j]+ " ");
            }
            System.out.println();
        }
    }
}