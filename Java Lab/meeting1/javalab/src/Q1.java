
import java.util.Scanner;

public class Q1 {
    //Q1: write a program that receives a number N as an argument and prints out N Asterisks in diagonal.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        do {
            System.out.println("Please Enter Number:");
            n = sc.nextInt();
        } while (n<=0);

        printAsteriskInDiag(n);
    }

    public static void printAsteriskInDiag(int n) {
        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
            }
            System.out.print("*\n");
        }
    }
}
