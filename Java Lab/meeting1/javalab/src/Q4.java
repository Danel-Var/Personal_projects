
import java.util.*;
import java.util.stream.Collectors;

public class Q4 {
    public static void main(String[] args){

        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<Integer>numbers = Arrays.asList(1,2,8,9,10);

        long count = strings.stream().filter(string -> !string.isEmpty()).count();
        System.out.println("4.a :"+count);
        String str= strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("4.b :"+str);
        System.out.println("4.c :"+GetSquareSeries(numbers));
    }
    private static int GetSquareSeries(List<Integer> list)
    {
        return list.stream().mapToInt(i -> i*i).sum();
    }
}