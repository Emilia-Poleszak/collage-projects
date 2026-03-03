/**
 * Exercise nr 3
 *
 * @author  Anna Czarkowska
 * @author  Emilia Poleszak
 */

import java.util.TreeSet;
import java.util.Scanner;

public class SetOperations {
    public static TreeSet<Integer> union(TreeSet<Integer> A, TreeSet<Integer> B)
    {
        TreeSet<Integer> set = new TreeSet<>(A);
        set.addAll(B);
        return set;
    }

    public static TreeSet<Integer> intersection(TreeSet<Integer> A, TreeSet<Integer> B)
    {
        TreeSet<Integer> set = new TreeSet<>();
        for (Integer i : A)
            if (B.contains(i)) set.add(i);
        return set;
    }

    public static TreeSet<Integer> difference(TreeSet<Integer> A, TreeSet<Integer> B)
    {
        TreeSet<Integer> set = new TreeSet<>(A);
        set.removeIf(B::contains);
        return set;
    }

    private static TreeSet<Integer> inputToSet(String input) throws Exception
    {
        if (!String.valueOf(input.charAt(0)).equals("[") ||
            !String.valueOf(input.charAt(input.length()-1)).equals("]"))
        {
            throw new Exception();
        }

        input = input.replace("[", "").replace("]", "");
        String[] i = input.split(",");
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(String s: i)
            set.add(Integer.parseInt(s));
        return set;
    }

    public static void consoleTest() throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the operation for your sets -> +, * or -");
        System.out.print("Input should look like this: [first set] +/*/- [second set]");
        System.out.println("Separate elements in the brackets only by commas (,)");
        System.out.print("Input your calculation: ");
        String[] input = sc.nextLine().split(" ");

        if ((input.length != 3) ||
            (!input[1].equals("+") && !input[1].equals("*") && !input[1].equals("-")))
        {
            throw new Exception("Invalid input");
        }

        TreeSet<Integer> A;
        TreeSet<Integer> B;
        try {
            A = inputToSet(input[0]);
            B = inputToSet(input[2]);
        } catch (Exception e) {
            throw new Exception("Invalid input");
        }

        System.out.print("Solution: ");
        switch (input[1]) {
            case "+": System.out.print(union(A, B)); break;
            case "*": System.out.print(intersection(A, B)); break;
            case "-": System.out.print(difference(A, B)); break;
        }
    }

    public static void main(String[] args)
    {
        try {
            consoleTest();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
