/**
 * @author Anna Czarkowska
 * @author Emilia Poleszak
 */

import java.util.*;
import java.lang.Integer;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumbersGenerator {
    public static void generateRandomNumbers(int MAX, int N, int S) {
        ArrayList<Integer> list = new ArrayList<>();
        TreeSet<Integer> set = new TreeSet<>();
        while(true){
            Integer number = ThreadLocalRandom.current().nextInt(MAX);
            list.add(number);
            set.add(number);
            if(number == S) break;
        }

        System.out.println("The first " + N + " generated numbers:");
        for(int i = 0; i < N; i++){
            System.out.print(list.get(i) + " ");
        }

        System.out.println("\nThe last " + N + " generated numbers:");
        for(int i = 0; i < N; i++){
            System.out.print(list.get(list.size()-1-i) + " ");
        }

        System.out.println("\nValues of generated numbers, in ascending order:");
        System.out.println(set);
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int MAX, N, S;
        System.out.println("Enter parameters\nMAX");
        MAX = s.nextInt();
        System.out.println("S");
        S = s.nextInt();
        System.out.println("N");
        N = s.nextInt();
        generateRandomNumbers(MAX, N, S);
    }
}