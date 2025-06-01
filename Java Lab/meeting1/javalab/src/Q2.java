
import java.util.Scanner;

public class Q2 {
    //Q2: create a program that gets as arguments a1,diff and n and prints the arithmetic series a_n=a_{n-1} + d
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Hello! lets print an arithmetic series");
    System.out.println("Lets start with: ");
    int a_0 = sc.nextInt();
    System.out.println("jump with diff: ");
    int diff = sc.nextInt();
    System.out.println("for N steps: ");
    int steps = sc.nextInt();

    PrintArithmeticSeries(a_0,diff,steps);

    }

    public static void PrintArithmeticSeries(int a_0, int diff, int steps) {
        int a_n = a_0;
        for (int i = 0; i < steps; i++) {
            System.out.print(a_n + " ");
            a_n += diff;
        }
    }
}
